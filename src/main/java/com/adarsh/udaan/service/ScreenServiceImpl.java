package com.adarsh.udaan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adarsh.udaan.dao.ScreenDao;
import com.adarsh.udaan.entity.Screen;
import com.adarsh.udaan.utils.BusinessException;
import com.adarsh.udaan.utils.CommonUtils;
import com.adarsh.udaan.vo.ScreenRequestVO;
import com.adarsh.udaan.vo.SeatRowInfoRequestVO;
import com.adarsh.udaan.vo.SeatVO;

@Service
@Transactional
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenDao screenDao;
	
	@Autowired
	private SeatService seatService;
	

	@Override
	public boolean saveScreen(ScreenRequestVO requestVo) {
		if (requestVo != null) {
			if (!CommonUtils.isEmpty(requestVo.getName())) {
				int totalSeats = 0;
				Screen screen = null;
				String screenName = null;
				SeatRowInfoRequestVO rowInfo = null;
				String rowName = null;
				SeatVO seatVo = null;
				List<SeatVO> seatsVOList = new ArrayList<SeatVO>();
				screenName = requestVo.getName();

				if (requestVo.getSeatInfo() != null && requestVo.getSeatInfo() != null) {
					for (Map.Entry<String, SeatRowInfoRequestVO> seatInfo :  requestVo.getSeatInfo().entrySet() ) {
						rowName = seatInfo.getKey();
						rowInfo = seatInfo.getValue();
						if (CommonUtils.isEmpty(rowName)) {
							throw new BusinessException("Row Name cannot be empty");
						}
						if (rowInfo.getNumberOfSeats() <= 0) {
							throw new BusinessException("Number of seats must be greater than 0");
						}
						
						totalSeats += rowInfo.getNumberOfSeats();
						if (rowInfo.getAisleSeats() != null && rowInfo.getAisleSeats().size() > rowInfo.getNumberOfSeats()) {
							throw new BusinessException("Aisle info cannot be greater than total seats");
						}
						for (int i = 0; i < rowInfo.getNumberOfSeats(); i++) {
							seatVo = new SeatVO();
							seatVo.setScreenName(screenName);
							seatVo.setRowName(rowName);
							seatVo.setRowNumber(i);
							if (rowInfo.getAisleSeats() != null && rowInfo.getAisleSeats().contains(i)) {
								seatVo.setAisle(true);
							}
							seatVo.setBooked(false);
							seatsVOList.add(seatVo);
						}
					}
				} else {
					throw new BusinessException("Seat Info cannot be empty.");
				}
				screen = new Screen();
				screen.setName(screenName);
				screen.setTotalSeats(totalSeats);
				screenDao.saveScreen(screen);
				seatService.saveSeats(seatsVOList);
			} else {
				throw new BusinessException("Screen Name cannot be empty.");
			}
		} else {
			throw new BusinessException("Screen Request cannot be empty.");
		}
		return true;
	}
	
	@Override
	public List<String> getScreens(){
		return screenDao.getScreens();
	}

}
