package com.adarsh.udaan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.udaan.dao.SeatsDao;
import com.adarsh.udaan.utils.BusinessException;
import com.adarsh.udaan.utils.CommonUtils;
import com.adarsh.udaan.vo.AvailableSeatsAtGivenPositionResponseVO;
import com.adarsh.udaan.vo.SeatRequestVO;
import com.adarsh.udaan.vo.SeatVO;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatsDao seatDao;

	@Override
	public boolean updateSeats(List<SeatVO> seats) {
		if (seats != null && !seats.isEmpty()) {
			for (SeatVO seat : seats) {
				seatDao.updateSeat(seat.convertToEntity());
			}
		}
		return true;
	}

	@Override
	public boolean saveSeats(List<SeatVO> vos) {
		if (vos != null && !vos.isEmpty()) {
			for (SeatVO seatVo : vos) {
				seatDao.saveSeat(seatVo.convertToEntity());
			}
		}
		return true;
	}

	@Override
	public boolean reserveSeats(String screenName, SeatRequestVO requestVo) {
		if (requestVo != null && requestVo.getSeats() != null) {
			String rowName = null;
			SeatVO seatVo = null;
			List<SeatVO> seatsToBook = new ArrayList<>(); 
			for (Map.Entry<String, Set<Integer>> seatInfo :  requestVo.getSeats().entrySet()) {
				rowName = seatInfo.getKey();
				if (seatInfo.getValue() != null && seatInfo.getValue() != null && !seatInfo.getValue().isEmpty()) {
					for (Integer seatNum : seatInfo.getValue()) {
						seatVo = new SeatVO();
						seatVo.setBooked(true);
						seatVo.setScreenName(screenName);
						seatVo.setRowName(rowName);
						seatVo.setRowNumber(seatNum);
						seatsToBook.add(seatVo);
					}
				} else {
					throw new BusinessException("Bad request");
				}
			}
			updateSeats(seatsToBook);
			seatsToBook = null;
		} else {
			throw new BusinessException("Bad request");
		}
		return true;
	}

	@Override
	public SeatRequestVO getAvailableSeats(String screenName, String status) {
		SeatRequestVO seatResponseVo = new SeatRequestVO();
		Map<String, Set<Integer>> seatMap = new HashMap<>();
		Set<Integer> seatNumbers = null;
		if (CommonUtils.isEmpty(screenName)) {
			throw new BusinessException("Screen Name is Mandatory");
		}
		if (CommonUtils.isEmpty(status)) {
			throw new BusinessException("Status is Mandatory");
		} else if (!"unreserved".equalsIgnoreCase(status) && !"reserved".equalsIgnoreCase(status)) {
			throw new BusinessException("Status valid values are Reserved/Unreserved");
		}
		List<SeatVO> seatVoList = seatDao.getAvailableSeats(screenName, status);
		if (seatVoList == null || seatVoList.isEmpty()) {
			throw new BusinessException("No available seats for screen " + screenName);
		}

		for (SeatVO vo : seatVoList) {
			if (seatMap.containsKey(vo.getRowName())) {
				seatMap.get(vo.getRowName()).add(vo.getRowNumber());
			} else {
				seatNumbers = new HashSet<>();
				seatNumbers.add(vo.getRowNumber());
				seatMap.put(vo.getRowName(), seatNumbers);
			}
		}
		seatResponseVo.setSeats(seatMap);
		return seatResponseVo;
	}

	@Override
	public List<AvailableSeatsAtGivenPositionResponseVO> getAvailableSeatsAtGivenPosition(String screenName,
			String numberOfSeats,
			String choice) {
		List<AvailableSeatsAtGivenPositionResponseVO> responseList = null;
		Map<String, Set<Integer>> availableSeatsMap = null;
		int numOfSeats;
		String rowName = null;
		int rowNumber;
		int minRange;
		int maxRange;
		AvailableSeatsAtGivenPositionResponseVO response = null;
		if (CommonUtils.isEmpty(numberOfSeats)) {
			throw new BusinessException("Number of seats is Mandatory");
		} else {
			try {
				numOfSeats = Integer.parseInt(numberOfSeats);
			} catch (NumberFormatException ex) {
				throw new BusinessException("Numeric value required for number of seats");
			}
			if (numOfSeats <= 0) {
				throw new BusinessException("Number of seats should be greater than 0.");
			}
		}
		if (CommonUtils.isEmpty(choice)) {
			throw new BusinessException("Choice is Mandatory");
		} else {
			rowName = String.valueOf(choice.charAt(0));
			try {
				rowNumber = Integer.parseInt(choice.substring(1, choice.length()));
			} catch (NumberFormatException ex) {
				throw new BusinessException("Numeric value required for number of seats");
			} catch (Exception ex) {
				throw new BusinessException("Bad Request");
			}
		}
		List<SeatVO> seatList = seatDao.getAvailableSeatsAtGivenPosition(screenName, numOfSeats, rowName, rowNumber);
		if (seatList != null && !seatList.isEmpty()) {
			responseList = new ArrayList<AvailableSeatsAtGivenPositionResponseVO>();
			Set<Integer> availableSeatNumbers = new HashSet<Integer>();
			Set<Integer> aisleSeatNumbers = new HashSet<Integer>();
			for (SeatVO seat : seatList) {
				if (!seat.isBooked()) {
					availableSeatNumbers.add(seat.getRowNumber());
				}
				if (seat.isAisle()) {
					aisleSeatNumbers.add(seat.getRowNumber());
				}
			}
			maxRange = rowNumber + numOfSeats -1;
			minRange = rowNumber - numOfSeats + 1;
			if (minRange < 0) {
				minRange = 0;
			}
			Set<Integer> seatTemp = null;
			if (aisleSeatNumbers.contains(rowNumber)) {
				if (aisleSeatNumbers.contains(rowNumber + 1)) {
					maxRange = rowNumber;//choice is an ending aisle.
				} else if (aisleSeatNumbers.contains(rowNumber - 1)) {
					minRange = rowNumber;//choice is a starting aisle.
				} else if (numOfSeats != 1) {
					return null; //no contiguous seats available
				}
			} 
			loop : for (int i = minRange; i <= rowNumber; i++) {
				seatTemp = new HashSet<Integer>();
				for (int j = 0; j < numOfSeats; j++) {
					if (i+j > maxRange) {
						break loop;
					}
					if (availableSeatNumbers.contains(i+j)) {
						if (seatTemp.size() == 0 || seatTemp.size() == numOfSeats-1  || !aisleSeatNumbers.contains(i+j)) {
							seatTemp.add(i+j);
						}
					} else {
						i = i+j;
					}
				}
				if (seatTemp.size() == numOfSeats) {
					response = new AvailableSeatsAtGivenPositionResponseVO();
					availableSeatsMap = new HashMap<String, Set<Integer>>();
					availableSeatsMap.put(rowName, seatTemp);
					response.setAvailableSeats(availableSeatsMap);
					responseList.add(response);
				}
			}
		} else {
			throw new BusinessException("No available seats.");
		}
		return responseList;
	}



}
