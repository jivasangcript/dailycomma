package com.yedam.dailycomma;

import com.yedam.dailycomma.lodgment.LodgmentDTO;
import com.yedam.dailycomma.lodgment.LodgmentSearchDTO;
import com.yedam.dailycomma.postscript.PostscriptDAOMybatis;
import com.yedam.dailycomma.postscript.PostscriptDTO;
import com.yedam.dailycomma.reservation.ReservationDAOMybatis;
import com.yedam.dailycomma.reservation.ReservationDTO;
import com.yedam.dailycomma.room.RoomDAOMybatis;
import com.yedam.dailycomma.room.RoomDTO;
import com.yedam.dailycomma.room.RoomPostDTO;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:servlet-context-test.xml")
public class ReservationDAOClient {

	@Autowired
	ReservationDAOMybatis dao;
	
	@Ignore @Test
	public void reserveTest() {
		Map<String, Object> map = dao.getReserveInfo("RO1");
		
		for(String key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}
	
	@Ignore @Test
	public void locationTest() {
		ReservationDTO dto = new ReservationDTO();
		dto.setLodgmentNo("LOD1");
		String location = dao.getLocation(dto);
		System.out.println(location);
	}
	
	@Ignore @Test
	public void testInsertReservation() {
		ReservationDTO dto = new ReservationDTO();
		dto.setReservePrice("100000");
		dto.setReservePeople("3");
		dto.setCheckin("2018-08-01");
		dto.setCheckout("2018-12-30");
		dto.setLodgmentNo("LOD1");
		dto.setRoomNo("RO1");
		dto.setMemberNo("MEM2");
		dto.setReservePoints("100");
		dto.setDeductionPoint("3000");
		dao.insertReservation(dto);
	}
	
	@Test
	public void testCancelReserve() {
		
	}
}
