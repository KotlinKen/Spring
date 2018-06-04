package com.kh.spring.member.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes({"memberLoggedIn"})
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private ShaPasswordEncoder sharPasswordEncoder;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncode;
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}
	
	@RequestMapping(value = "/member/memberEnrollEnd.do", method=RequestMethod.POST)
	public String memberEnrollEnd(Model model, Member m) {
		System.out.println(m);
		System.out.println(m.getAge());
		String rawPassword = m.getPassword();
		System.out.println("암호화 전 : " + rawPassword);
		/*********************** password 암호화 시작 **********************/
		//salt값을 사용하지 않은 버젼
		//String encodedPassword = sharPasswordEncoder.encodePassword(rawPassword, null);
		//salt값을 사용한 버젼
		//String encodedPassword = sharPasswordEncoder.encodePassword(rawPassword, m.getUserId()+rawPassword);
		//$2a$10$TODK6Z450o9LYBFZ62csUuqmjQbyxR09YsHnjTl.pVpo41pMvfKSe > 1234
		String encodedPassword = bcryptPasswordEncode.encode(rawPassword);
		
		m.setPassword(encodedPassword);
		
		
		/*********************** password 암호화 시작 **********************/
		
		System.out.println("암호화 후 : " + m.getPassword());
		String msg = "";
		int result = memberService.insertMember(m);
		model.addAttribute("result", result);
		if(result > 0 ) {
			msg="성공";
			model.addAttribute("msg",msg);
		}else {
			msg="실패";
			model.addAttribute("msg",msg);
		}
		return "common/msg";
	}
	
	

/*	@RequestMapping("/member/memberLogin.do")
	public String memberLogin(@RequestParam String userId, @RequestParam String password, Model model, HttpSession session) {
		Member m = memberService.selectMemberOne(userId);
		
		String msg = "";
		String loc = "/";
		
		if(m==null) {
				msg="존재하지 않는 아이디 입니다.";
		}else {
			if(bcryptPasswordEncode.matches(password, m.getPassword())) {
				msg = m.getUserId()+"님 환영합니다.";
				//기존 세션처리
				//session.setAttribute("memberLoggedIn", m);
				model.addAttribute("memberLoggedIn", m);
			}else {
				msg = "비밀번호가 틀렸습니다."; 
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
	*/
	
	
	@RequestMapping("/member/memberLogin.do")
	public ModelAndView memberLogin(@RequestParam String userId, @RequestParam String password) {
		
		ModelAndView mav = new ModelAndView();
		
		
		
		Member m = memberService.selectMemberOne(userId);
		
		String msg = "";
		String loc = "/";
		
		if(m==null) {
				msg="존재하지 않는 아이디 입니다.";
		}else {
			if(bcryptPasswordEncode.matches(password, m.getPassword())) {
				msg = m.getUserId()+"님 환영합니다.";
				//기존 세션처리
				//session.setAttribute("memberLoggedIn", m);
				mav.addObject("memberLoggedIn", m);
			}else {
				msg = "비밀번호가 틀렸습니다."; 
			}
		}
		
		mav.addObject("msg", msg);
		mav.addObject("loc", loc);
		//뷰단 지정
		//redirect:/
		mav.setViewName("common/msg");
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/member/memberLogout.do")
	public String memberLogout(Model model, SessionStatus sessionStatus) {
		
		String msg = "";
		String loc = "/";
		
		//현재 session상태를 끝났다고 마킹함.
		//이 세션 끝난거니? false일때
		if(!sessionStatus.isComplete())
		sessionStatus.setComplete();
		/*return "redirect:/";*/
		
		msg="로그아웃 합니다.";
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		
		return "common/msg";
	}
	
	
	@RequestMapping("/member/memberView.do")
	public ModelAndView memberView(@RequestParam String userId) {
		ModelAndView mav = new ModelAndView();
		Member m = memberService.selectMemberOne(userId);
		mav.addObject("m", m);
		mav.setViewName("member/memberView");
		return mav;
	}
	
	
	@RequestMapping(value = "/member/memberUpdate.do", method=RequestMethod.POST)
	public ModelAndView memberUpdate(Member m) {
		System.out.println(m);
		ModelAndView mav = new ModelAndView();
		String msg = "";
		int result = memberService.updateMember(m);
		mav.addObject("result", result);
		if(result > 0 ) {
			msg="성공";
			mav.addObject("msg",msg);
		}else {
			msg="실패";
			mav.addObject("msg",msg);
		}
		mav.setViewName("common/msg");
		return mav;
	}
	
	
}
