package com.yedam.dailycomma.user.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yedam.dailycomma.user.UserDTO;
import com.yedam.dailycomma.user.UserSearchDTO;

@Repository
public class UserDAOMybatis {
	@Autowired
	SqlSessionTemplate mybatis;

	//전체 조회
	public List<UserDTO> getUsers(UserSearchDTO searchDto) {
		System.out.println("user mybatis 목록 조회 ======");
		return mybatis.selectList("user.getUsers", searchDto);
	}
	//건수 조회
	public int getCnt(UserSearchDTO searchDto) {
		return mybatis.selectOne("user.getCnt", searchDto);
	}
	//단건 조회
	public UserDTO getUser(UserDTO dto) {
		return mybatis.selectOne("user.getUser", dto);
	}
	//등록
	public int insertUser(UserDTO dto) {
		System.out.println("mybatis 사용자 등록");
		return mybatis.insert("user.insertUser", dto);
	}
	//수정
	public int updateUser(UserDTO dto) {
		System.out.println("mybatis 사용자 수정");
		return mybatis.update("user.updateUser", dto);
	}
	//삭제
	public int deleteUser(UserDTO dto) {
		System.out.println("mybatis 사용자 삭제");
		return mybatis.delete("user.deleteUser", dto);
	}
}
