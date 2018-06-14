package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertMember(Member m) {
		System.out.println("RYULMIN DAO + " + m);
		return sqlSession.insert("member.insertMember", m);
	}

	@Override
	public Member selectMemberOne(String userId) {
		System.out.println("RYULMIN DAO + " + userId);
		return sqlSession.selectOne("member.selectMemberOne", userId);
	}

	@Override
	public int updateMember(Member m) {
		System.out.println("RYULMIN DAO + " + m);
		return sqlSession.update("member.updateMember", m);
	}

	@Override
	public int checkIdDuplicate(String userId) {
		return sqlSession.selectOne("member.checkIdDuplicate", userId);
	}

}
