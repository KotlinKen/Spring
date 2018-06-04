package com.kh.spring.member.model.dao;

import com.kh.spring.member.model.vo.Member;

public interface MemberDAO {

	int insertMember(Member m);

	Member selectMemberOne(String userId);

	int updateMember(Member m);

}
