package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.vo.UserVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public void delete(BoardVo vo) {
		sqlSession.delete("board.delete", vo);

	}

	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	public Boolean commentInsert(BoardVo vo) {
		int count = sqlSession.insert("board.commentinsert", vo);
		return count == 1;
	}

	public List<BoardVo> getList() {
		List<BoardVo> result = sqlSession.selectList("board.getlist");
		return result;
	}

	public List<BoardVo> getList(int page) {
		List<BoardVo> result = sqlSession.selectList("board.getList",page);
		return result;
	}

	public Boolean update(BoardVo vo) {
		int count = sqlSession.update("board.contentsupdate", vo);
		return count == 1;
	}

	public Boolean deleteUpdate(BoardVo vo) {
		int count = sqlSession.update("board.deleteupdate", vo);
		return count == 1;
	}

	public Boolean update(Long no) {
		int count = sqlSession.update("board.hitupdate", no);
		return count == 1;
	}

	public Boolean update(int gNo) {
		int count = sqlSession.update("board.commentupdate", gNo);
		return count == 1;
	}

	public int getCount() {
		List<BoardVo> result = sqlSession.selectList("board.getcount");
		return result.size();
	}

}
