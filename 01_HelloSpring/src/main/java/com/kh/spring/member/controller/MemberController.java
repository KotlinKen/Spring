package com.kh.spring.member.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes({"memberLoggedIn"})
@Controller
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private ShaPasswordEncoder sharPasswordEncoder;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncode;
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		if(logger.isDebugEnabled()) {
		logger.debug("회원등록 페이지");
		}
		return "member/memberEnroll";
	}
	
	@RequestMapping(value = "/member/memberEnrollEnd.do", method=RequestMethod.POST)
	public String memberEnrollEnd(Model model, Member m) {
		if(logger.isDebugEnabled()) {
			/*logger.debug("회원등록 처리 페이지");*/
		}
		logger.debug(m.toString());
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
	public ModelAndView memberLogin(@RequestParam String userId, @RequestParam String password, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		if(logger.isDebugEnabled()) {
			//logger.debug("회원로그인 요청 페이지");
		}
		

		
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
				session.setAttribute("memberLoggedIn", m);
			}else {
				msg = "비밀번호가 틀렸습니다."; 
			}
		}
		
		mav.addObject("msg", msg);
		mav.addObject("loc", loc);
		//뷰단 지정
		//redirect:/
		mav.setViewName("common/msg");
		MDC.put("USERID", m.getUserId());
		//logger.debug(m.getUserId()+"로그인 함."); 
		return mav;
	}
	
	
	
	@RequestMapping(value="/member/memberLogout.do")
	public String memberLogout(Model model, SessionStatus sessionStatus) {
		if(logger.isDebugEnabled()) {
			logger.debug("로그아웃 페이지");
		}
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
		
		if(logger.isDebugEnabled()) {
			logger.debug("[" + userId +"]"+"회원 정보 보기 페이지");
		}
		ModelAndView mav = new ModelAndView();
		System.out.println("test");
		System.out.println("44444444");
		Member m = memberService.selectMemberOne(userId);
		mav.addObject("m", m);
		mav.setViewName("member/memberView");
		return mav;
	}
	
	
	@RequestMapping(value = "/member/memberUpdate.do", method=RequestMethod.POST)
	public ModelAndView memberUpdate(Member m) {
		
		if(logger.isDebugEnabled()) {
			logger.debug(m.toString()+"회원 업데이트 처리");
		}
		
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
	
	/******************* Spring Ajax 시작 ******************/
	   /**
	    * 1. Stream을 이용한 ajax처리
	    */
/*	   @RequestMapping("/member/checkIdDuplicate.do")
	   public void checkIdDuplicate(@RequestParam String userId, HttpServletResponse response) throws IOException {
	      logger.debug("stream ajax : "+userId);
	      int count = memberService.checkIdDuplicate(userId);
	      boolean isUsable = count==0?true:false;
	      
	      response.getWriter().print(isUsable);
	      
	      
	   }*/
	   
	   
	   
	   
	   /******************* Spring Ajax 끝 ******************/
	
	
	/**
	 * 2.jsonView
	 */
	
	
/*	   @RequestMapping("/member/checkIdDuplicate.do")
	   public ModelAndView checkIdDuplicate(@RequestParam String userId) throws IOException {
	      logger.debug("jsonView ajax : "+userId);
	      ModelAndView mav = new ModelAndView();
	      Map<String, Object> map = new HashMap<>();
	      //업무로직
	      int count = memberService.checkIdDuplicate(userId);
	      boolean isUsable = count==0?true:false;
	      
	      map.put("isUsable", isUsable);
	      mav.addAllObjects(map);
	      //(중요) jsonView빈으로 연결됨
	      mav.setViewName("jsonView");
	      
	      return mav; 
	   }
	
	*/
	
	
	
/*	   @RequestMapping("/member/checkIdDuplicate.do")
	   @ResponseBody
	   public String checkIdDuplicate(@RequestParam(value="userId") String userId) throws JsonProcessingException {
	      logger.debug("@ResponseBody-jsonString ajax : "+userId);

	      Map<String, Object> map = new HashMap<>();
	      
	      //jackson 라이브러리에서 사용하는 바인더 
	      ObjectMapper mapper = new ObjectMapper();
	      String jsonStr = null;
	      
	      
	      //업무로직
	      int count = memberService.checkIdDuplicate(userId);
	      boolean isUsable = count==0?true:false;
	      
	      //jsonString 변환 
	      map.put("isUsable", isUsable);
	      jsonStr = mapper.writeValueAsString(map);
	      return jsonStr; 
	   }*/
	   
	/**
	 *  4.@ResponseBody를 이용해서 일반 자바객체 리턴하기
	 * 
	 * 
	 */
	   
	
	
	
	   @RequestMapping("/member/checkIdDuplicate.do")
	   @ResponseBody
	   public Map<String, Object> checkIdDuplicate(@RequestParam(value="userId") String userId) throws JsonProcessingException {
	      logger.debug("@ResponseBody-java Object ajax : "+userId);
	      Map<String, Object> map = new HashMap<>();
	      
	      //업무로직
	      int count = memberService.checkIdDuplicate(userId);
	      boolean isUsable = count==0?true:false;
	      
	      
	      //jsonString 변환 
	      map.put("isUsable", isUsable);
	      
	      
	      return map; 
	   }
	   
	
	
}
