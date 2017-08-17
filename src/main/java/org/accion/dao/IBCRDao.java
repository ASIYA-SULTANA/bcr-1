package org.accion.dao;
import java.util.List;
import org.accion.entity.BCR;
import java.time.*;
public interface IBCRDao {
	List<BCR> getAllBCRs();
    BCR getBCRByBookingId(int bookingId);
    void createBCR(BCR bcr);
    void updateBCR(BCR bcr);
    void deleteBCR(int bookingId);
    boolean bcrExists(int bookingId);
    List<BCR> getBCRBYRoomNameAndDate(String roomName, LocalDate date);
}
