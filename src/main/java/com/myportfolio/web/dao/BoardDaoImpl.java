package com.myportfolio.web.dao;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//BoardDao 인터페이스로 만들기, 다른것에 영향 덜주기 위함
public class BoardDaoImpl implements BoardDao {
//    세션 받아와서 세션주입
    @Autowired
    SqlSession session;

    String namespace="com.myportfolio.web.dao.BoardMapper.";

    @Override
    public BoardDto select(int bno) throws Exception{
      return  session.selectOne(namespace+"select",bno); // sql 세션을 이용해서 메서드인 selectOne을 이용해 데이터를 가져옴
    }

    @Override
    public List<BoardDto> selectAll() throws Exception{
        return  session.selectList(namespace+"selectAll");
    }

    @Override
    public int insert(BoardDto dto) throws Exception{
        return session.insert(namespace+"insert",dto);
    }

    @Override
    public int update(BoardDto dto) throws Exception{
        return session.update(namespace+"update",dto);
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception{
        Map map=new HashMap();
        map.put("bno",bno);
        map.put("writer",writer);
        return session.delete(namespace+"delete",map);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace + "increaseViewCnt", bno);
    } // int update(String statement, Object parameter)


    @Override
    public int updateCommentCnt(Integer bno, int cnt) {
        Map map = new HashMap();
        map.put("cnt", cnt);
        map.put("bno", bno);
        return session.update(namespace + "updateCommentCnt", map);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }





}
