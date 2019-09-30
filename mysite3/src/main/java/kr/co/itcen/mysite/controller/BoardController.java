package kr.co.itcen.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String index(Model model) {
		
		List<BoardVo> list = boardService.getList(0);
		model.addAttribute("list", list);

		return "board/list";
	}
	
	@RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
	public String index(@PathVariable("page") String page, Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		int countPage = 5;
		int curPage;
		int lastPage;

		if (page == null) {
			curPage = 1;
		} else {
			curPage = Integer.parseInt(page);
		}

		int startPage = ((curPage - 1) / countPage) * countPage + 1;
		int endPage = startPage + countPage - 1;

		int blockNum = 0;

		blockNum = (int) Math.floor((curPage - 1) / countPage);
		int blockStartNum = (countPage * blockNum) + 1;
		int blockLastNum = blockStartNum + (countPage - 1);

		int total = boardService.getCount();

		if (total % countPage == 0) {
			lastPage = (int) Math.floor(total / countPage);
		} else {
			lastPage = (int) Math.floor(total / countPage) + 1;
		}
		
		List<BoardVo> list = boardService.getList((curPage - 1) * 5);

		model.addAttribute("authUser", authUser);
		model.addAttribute("list", list);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("blockStartNum", blockStartNum);
		model.addAttribute("blockLastNum", blockLastNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("curPage", curPage);

		return "board/list";
	}

	@RequestMapping(value = "/view/{no}/{curPage}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no,@PathVariable("curPage") int curPage, HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		boardService.update(no);
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("curPage", curPage);

		return "board/view";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo) {
		if (vo.getgNo() != "" && vo.getoNo() != "" && vo.getDepth() != "") {
			boardService.update(vo.getgNo());
			boardService.commentInsert(vo);
		} else {
			boardService.insert(vo);
		}
		return "redirect:/board/list/1";
	}

	@RequestMapping(value = "/write/{gNo}/{oNo}/{depth}", method = RequestMethod.GET)
	public String commentWrite(HttpSession session, Model model, @PathVariable("gNo") String gNo,
			@PathVariable("oNo") String oNo, @PathVariable("depth") String depth) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		model.addAttribute("gNo", gNo);
		model.addAttribute("oNo", oNo);
		model.addAttribute("depth", depth);
		return "board/write";
	}

	@RequestMapping(value = "/delete/{no}/{gNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute BoardVo vo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		vo.setUserNo(authUser.getNo());
		int count = 0;
		List<BoardVo> list = boardService.getList();
		
		for (int i = 0; i < list.size(); i++) {
			if (vo.getgNo().equals(list.get(i).getgNo())) {
				count++;
			}
		}
		if (count > 1) {
			boardService.deleteUpdate(vo);
		} else {
			boardService.delete(vo);
		}
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {
		boardService.update(vo);
		return "redirect:/board/list/1";
	}
}
