package kr.co.itcen.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.vo.UserVo;

@Controller
public class MainController {

	@RequestMapping({ "", "/main" })
	public String index(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "ㅎㅇ";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setName("ㅇㅇㅇ");
		vo.setEmail("gdsgdsg");
		
		return vo;
	}
}
