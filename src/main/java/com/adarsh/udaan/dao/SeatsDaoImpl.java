package com.adarsh.udaan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.adarsh.udaan.entity.Seats;
import com.adarsh.udaan.utils.BusinessException;
import com.adarsh.udaan.vo.SeatVO;

@Repository
public class SeatsDaoImpl implements SeatsDao{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public boolean saveSeat(Seats seat) {
		try {
			em.persist(seat);
			em.flush();
		} catch (PersistenceException ex) {
			throw new BusinessException("Seat Already Exists.");
		} catch(Exception ex) {
			throw new BusinessException("System Exception.");
		}
		return true;
	}

	@Override
	public void updateSeat(Seats seat) {
		Seats oldSeatInfo = em.find(Seats.class, seat.getSeatsPk(), LockModeType.PESSIMISTIC_WRITE);
		if (oldSeatInfo == null) {
			throw new BusinessException("Wrong Seat Info");
		} else if (oldSeatInfo.isBooked()) {
			throw new BusinessException("Seat Already Booked");
		} else {
			oldSeatInfo.setBooked(true);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SeatVO> getAvailableSeats(String screenName, String status) {
		List<SeatVO> seatsVoList = null;
		Query query = em.createQuery("from Seats as s where s.seatsPk.screenName=:name and s.booked=:status");
		query.setParameter("name", screenName);
		query.setParameter("status", "unreserved".equalsIgnoreCase(status) ? false : true);
		query.setLockMode(LockModeType.PESSIMISTIC_READ);
		List<Seats> seats = query.getResultList();
		if (seats != null && !seats.isEmpty()) {
			seatsVoList = new ArrayList<>();
			for (Seats seat : seats) {
				seatsVoList.add(seat.convertToVO());
			}
		}
		return seatsVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeatVO> getAvailableSeatsAtGivenPosition(String screenName,
			int numberOfSeats,
			String rowName,
			int rowNumber) {
		int maxRange = rowNumber + numberOfSeats -1;
		int minRange = rowNumber - numberOfSeats + 1;
		if (minRange < 0) {
			minRange = 0;
		}
		List<SeatVO> seatsVoList = null;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("from Seats as s where s.seatsPk.screenName=:name and s.seatsPk.rowName=:rowName");
		queryStr.append(" and s.seatsPk.rowNumber >=:minRange and s.seatsPk.rowNumber <=:maxRange order by s.seatsPk.rowNumber");
		Query query = em.createQuery(queryStr.toString());
		query.setParameter("name", screenName);
		query.setParameter("rowName", rowName);
		query.setParameter("minRange", minRange);
		query.setParameter("maxRange", maxRange);
		query.setLockMode(LockModeType.PESSIMISTIC_READ);
		List<Seats> seats = query.getResultList();
		if (seats != null && !seats.isEmpty()) {
			seatsVoList = new ArrayList<>();
			for (Seats seat : seats) {
				seatsVoList.add(seat.convertToVO());
			}
		}
		return seatsVoList;
	}

}
