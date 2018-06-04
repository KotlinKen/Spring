package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;
	@Override
	public int insertMember(Member m) {
		return memberDAO.insertMember(m);
	}
	@Override
	public Member selectMemberOne(String userId) {
		return memberDAO.selectMemberOne(userId);
	}
	@Override
	public int updateMember(Member m) {
		return memberDAO.updateMember(m);
	}

}
