package org.accion.service;
import org.accion.entity.BCR;

import java.time.LocalDate;
import java.util.List;
public interface IBCRService {
	List<BCR> getAllBCR();
    BCR getBCRByBookingId(int articleId);
    boolean createBCR(BCR bcr);
    void updateBCR(BCR bcr);
    void deleteBCR(int bookingId);
    List<BCR> getBCRByRoomNameAndDate(String roomName, LocalDate date);
}
