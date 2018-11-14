package com.yedam.dailycomma.management;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.dailycomma.lodgment.LodgmentDTO;
import com.yedam.dailycomma.management.StatsService;
import com.yedam.dailycomma.member.MemberDTO;
import com.yedam.dailycomma.reservation.ReservationDTO;
import com.yedam.dailycomma.reservation.ReservationSearchDTO;
import com.yedam.dailycomma.room.RoomDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ManagementController {

		
		//사장님 예약자 리스트 managementList.do 관련 부분 컨트롤러!!
		@Autowired
		private ManagementService service;
		private String[] reserveNo;

		@RequestMapping("/managementList.do")
		public String management(Model model) {

			//logger.info("home");
			String lodgmentNo = "LOD1";
			List<ManagementDTO> memberList = service.selectReservation(lodgmentNo);
			model.addAttribute("memberList", memberList);
			return "management/managementList";
		}
		
		
		//체크박스 선택 뒤 취소버튼 예약자 삭제 시험중 
		@RequestMapping("/deleteReserve.do") 
		public String deleteReserve(@RequestParam(value="reserveNo", defaultValue="empty") String[] reserveNo, 
									HttpSession session){
			
			if(!reserveNo[0].equals("empty")) {
				service.deleteReserve(reserveNo);
			}		
			return "redirect:/managementList.do";
		}
		
	
		//통계 뷰 페이지
		@RequestMapping("/stats.do") 
		public String stats(Locale locale, Model model, ManagementDTO dto) {
			List<ManagementDTO> statsList = statsService.selectRoomList(dto);
			List<ManagementDTO> totalList = statsService.selectTotalList(dto);
			model.addAttribute("statsList", statsList);
			model.addAttribute("totalList", totalList);
			return "/management/stats";

		}
		
		//구글차트 stats.do 관련 부분 컨트롤러!!
		//월별수익금액
		@Autowired StatsService statsService;
		@RequestMapping("/getStatsChart.do") //차트 데이터
		@ResponseBody
		public List<Map<String, Object>> getStatsChart() {
			return statsService.getStatsChart();
		}
		
		//월별객실예약건수
		@RequestMapping("/getReserveChart.do")
		@ResponseBody
		public List<Map<String, Object>> getReserveChart() {
			return statsService.getReserveChart(); 
		}

}
