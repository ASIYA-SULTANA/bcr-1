package org.accion.service;

import java.time.LocalDate;
import java.util.List;

import org.accion.dao.IBCRDao;
import org.accion.entity.BCR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BCRService implements IBCRService {
	@Autowired
	private IBCRDao bcrDao;

	@Override
	public List<BCR> getAllBCR() {
		return bcrDao.getAllBCRs();
	}

	@Override
	public BCR getBCRByBookingId(int bookingId) {
		BCR obj = bcrDao.getBCRByBookingId(bookingId);
		return obj;
	}

	@Override
	public synchronized boolean createBCR(BCR bcr) {
		if (bcrDao.bcrExists(bcr.getBookingId())) {
			return false;
		} else {
			bcrDao.createBCR(bcr);
			return true;
		}
	}

	@Override
	public void updateBCR(BCR bcr) {
		bcrDao.updateBCR(bcr);

	}

	@Override
	public void deleteBCR(int bookingId) {
		bcrDao.deleteBCR(bookingId);

	}

	@Override
	public List<BCR> getBCRByRoomNameAndDate(String roomName, LocalDate date) {
		return bcrDao.getBCRBYRoomNameAndDate(roomName, date);
	}

}
