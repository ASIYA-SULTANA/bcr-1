package org.accion.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.accion.entity.BCR;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public class BCRDao implements IBCRDao {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<BCR> getAllBCRs() {
		String hql = "From bookings as book ORDER BY book.bookingId DESC";
		return (List<BCR>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public BCR getBCRByBookingId(int bookingId) {
		return entityManager.find(BCR.class, bookingId);
	}

	@Override
	public void createBCR(BCR bcr) {
		entityManager.persist(bcr);
	}

	@Override
	public void updateBCR(BCR bcr) {
		BCR book = getBCRByBookingId(bcr.getBookingId());
		book.setUser(bcr.getUser());
		entityManager.flush();
	}

	@Override
	public void deleteBCR(int bookingId) {
		entityManager.remove(getBCRByBookingId(bookingId));
	}

	@Override
	public boolean bcrExists(int bookingId) {
		String hql = "FROM bookings as book WHERE book.bookingId = ?";
		int count = entityManager.createQuery(hql).setParameter(1, bookingId).getResultList().size();
		return count > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCR> getBCRBYRoomNameAndDate(String roomName, LocalDate date) {
		String hql = "From bookings as book where book.roomName=? and book.date=?";
		return(List<BCR>)entityManager.createQuery(hql).setParameter(1, roomName)
				.setParameter(2, date).getResultList();
	}

}
