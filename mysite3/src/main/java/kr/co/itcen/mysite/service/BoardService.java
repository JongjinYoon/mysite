package kr.co.itcen.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.GuestbookVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList() {
		return boardDao.getList();
	}
	
	public List<BoardVo> getList(int page) {
		return boardDao.getList(page);
		
	}
	
	public void insert(BoardVo vo) {
		boardDao.insert(vo);
		
	}

	public void update(Long no) {
		boardDao.update(no);
		
	}
	
	public void update(BoardVo vo) {
		boardDao.update(vo);
		
	}
	
	public void deleteUpdate(BoardVo vo) {
		boardDao.deleteUpdate(vo);
		
	}

	public void commentInsert(BoardVo vo) {
		boardDao.commentInsert(vo);
		
	}

	public void update(String gNo) {
		boardDao.update(Integer.parseInt(gNo));
	}

	public void delete(BoardVo vo) {
		
		boardDao.delete(vo);
	}

	public int getCount() {
		return boardDao.getCount();
	}
}
