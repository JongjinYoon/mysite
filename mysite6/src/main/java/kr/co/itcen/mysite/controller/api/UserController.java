package kr.co.itcen.mysite.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.dto.JSONResult;
import kr.co.itcen.mysite.service.UserService;

@Controller("userApiController")//같은 이름의 컨트롤러가 2개가 있기 때문에 에러가 발생을 하므로 따로 이름을 부여함
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email", required = true, defaultValue = "") String email) {		
		Boolean exist = userService.existUser(email);
		System.out.println(exist);
		return JSONResult.success(exist);
	}
}
