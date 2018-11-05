package com.yedam.dailycomma.management;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.dailycomma.management.EmpService;
import com.yedam.dailycomma.reservation.ReservationDTO;
import com.yedam.dailycomma.reservation.ReservationSearchDTO;
import com.yedam.dailycomma.room.RoomDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ManagementController {

		//구글차트 
		@Autowired EmpService empService;
		@RequestMapping("/getEmpChart.do")
		@ResponseBody
		public List<Map<String, Object>> getEmpChart() {
			
			return empService.getEmpChart();

		}
		
		
		
		//사장님 예약자 리스트 
		@Autowired
		private ManagementService service;
	
		@RequestMapping("/managementList.do")
		public String management(Locale locale, Model model, ManagementDTO dto) throws Exception{

			//logger.info("home");
			
			List<ManagementDTO> memberList = service.selectReservation(dto);
			
			model.addAttribute("memberList", memberList);

			return "management/managementList";
		}
	

		/*	@RequestMapping("/stats.do")
		public String stats(Model model ReservationDTO dto) {
		
			return "noTiles/management/stats";
//			return "user/main";
		}*/
		
	/*	@RequestMapping("/managementList.do")
		public String managementList(Model model, ReservationDTO dto) {
		
			return "management/managementList";
//			return "user/main";
		}*/

}
