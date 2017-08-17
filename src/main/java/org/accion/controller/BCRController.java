package org.accion.controller;

import java.time.LocalDate;
import java.util.List;

import org.accion.entity.BCR;
import org.accion.service.IBCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("bcr")
@CrossOrigin(origins = { "http://localhost:3000" })
public class BCRController {
	@Autowired
	private IBCRService bcrService;

	@GetMapping("all-bookings")
	public ResponseEntity<List<BCR>> getAllBCRs() {
		List<BCR> list = bcrService.getAllBCR();
		return new ResponseEntity<List<BCR>>(list, HttpStatus.OK);
	}

	@GetMapping("booking")
	public ResponseEntity<BCR> getBCRByBookingId(@RequestParam("id") String id) {
		BCR bcr = bcrService.getBCRByBookingId(Integer.parseInt(id));
		return new ResponseEntity<BCR>(bcr, HttpStatus.OK);
	}

	@PostMapping("booking")
	public ResponseEntity<Void> createArticle(@RequestBody BCR bcr, UriComponentsBuilder builder) {
		boolean flag = bcrService.createBCR(bcr);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/bcr?id={id}").buildAndExpand(bcr.getBookingId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("booking")
	public ResponseEntity<BCR> updateArticle(@RequestBody BCR bcr) {
		bcrService.updateBCR(bcr);
		return new ResponseEntity<BCR>(bcr, HttpStatus.OK);
	}

	@DeleteMapping("booking")
	public ResponseEntity<Void> deleteBCR(@RequestParam("id") String id) {
		bcrService.deleteBCR(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("booking")
	public ResponseEntity<List<BCR>> getBCRByRoomNameAndDate(@RequestParam("roomName") String roomName, @RequestParam("date") LocalDate date){
	List<BCR> bcr = bcrService.getBCRByRoomNameAndDate(roomName, date);
	return new ResponseEntity<List<BCR>>(bcr, HttpStatus.OK);
	}

}
