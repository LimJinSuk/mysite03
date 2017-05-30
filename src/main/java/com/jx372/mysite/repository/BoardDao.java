package com.jx372.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int updateHit( Long no ) {
		return sqlSession.update( "board.updateHit", no );
	}
	
	public int getTotalCount( String keyword ) {
		return sqlSession.selectOne( "board.getTotalCount", keyword );
	}
	
	public List<BoardVo> getList( String keyword, Integer page, Integer size ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "keyword", keyword );
		map.put( "startIndex", (page-1)*size );
		map.put( "size", size );
		
		return sqlSession.selectList( "board.getList", map );
	}

	public int delete( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.delete( "board.delete", map );
	}

	public BoardVo get( Long no ) {
		return sqlSession.selectOne( "board.getByNo", no );
	}	
}