package com.yedam.dailycomma.lodgment;

import java.util.List;

public interface LodgmentService {
	//숙소메인검색
	public List<LodgmentDTO> getMainSearch(LodgmentDTO dto);
	
	//숙박업체 등록
	public int setLodgment(LodgmentDTO dto);
	//삭제
	
}
