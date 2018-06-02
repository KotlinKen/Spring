package com.kh.spring.demo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.demo.model.vo.Dev;

@Repository
public class DemoDAOImpl implements DemoDAO {
	/*SqlSession의 구현클라스인 SqlSessionTemplate타입으로 의존객체를 주입받음.*/

	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public int insertDev(Dev dev) {
		System.out.println("RYULMIN DAO + " + dev);
		return sqlSession.insert("demo.insertDev", dev);
	
	}

	@Override
	public List<Map<String, String>> selectDevList() {
		return sqlSession.selectList("demo.selectDevList");
	}

}
