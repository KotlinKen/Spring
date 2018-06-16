package com.kh.spring.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/**
 * 
 * 
 * @Component 의 자식 어 노테이션인 @Controller로 등록
 * component-scan에 의해서 빈으로 등록됨.
 * 빈이름은 demoController가 됨.
 * @author aquar
 *
 */

@Controller
public class DemoController {
	//DI Dependency Injection
	//스프링컨테이너가 DemoService타입의 빈을 자동으로 주입해줌.
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		System.out.println("/demo/demo.do가 호출됨! test2 이건 내가 쓴거");
		return "demo/demo";
	}
	
   /* Controller 클래스의 메소드가 가질수 있는 파라미터 타입
	* HttpServletReqeust
	* HttpServletResponse
	* HttpSession
	* 불편했지용 
	* java.util.Locdale : 요청에 대한 Locale
	* InputStream/Reader : 요청에 대한 입력
	* OutputStream/Writer : 응답에 대한 출력
	* @PathVariable
	* @RequestParam
	* @requestHeader
	* @CookieValue
	* @RequestBody
	* Map, Model, ModelMap : 뷰에 전달할 모델데이터 설정
	* Command객체 : http: 요청파라미터를 커맨드 객체에 저장한 VO객체
	* Error, BindingResult : Command객체저장결과
	* SessionStatus 
	*/
	
	
	
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest request) {
		System.out.println("/demo/demo1.do가 호출됨! 이건 내가 쓴거");
		String devName = request.getParameter("devName");
		int devAge = Integer.parseInt(request.getParameter("devAge"));
		String devEmail = request.getParameter("devEmail");
		String[] devLang = request.getParameterValues("devLang");
		
		Dev dev = new Dev(0, devName, devAge, devLang, devEmail);
		request.setAttribute("dev", dev);
		
		
		return "demo/demoResult";
	}
	
	@RequestMapping("/demo/demo2.do")
	public String demo2(@RequestParam(value="devName") String devName, 
						@RequestParam(value="devAge", required=false, defaultValue="20") int devAge,	
						@RequestParam(value="devEmail") String devEmail,	
						@RequestParam(value="devLang", required=false) String[] devLang, 
						Model model){ 
		Dev dev = new Dev(0, devName, devAge, devLang, devEmail);
		System.out.println("dev@demo2.do = "+ dev);
		model.addAttribute("dev", dev);
		
		return "demo/demoResult";
	}
	
	
	@RequestMapping("/demo/demo3.do")
	public String demo2(Model model, Dev dev) {
		System.out.println("dev@demo3.do = "+ dev);
		model.addAttribute("dev", dev);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDev.do", method=RequestMethod.POST)
	public String insertDev(Model model, Dev dev) {
		int result = demoService.insertDev(dev);
		System.out.println("result@insertDev.do = "+ result);
		model.addAttribute("result", result);
		List<Map<String, String>> list = demoService.selectDevList();
		model.addAttribute("list", list);
		return "redirect:/demo/selectDevList.do";
	}
	
	@RequestMapping(value="/demo/selectDevList.do")
	public String selectDevList(Model model, Dev dev) {
		List<Map<String, String>> list = demoService.selectDevList();
		model.addAttribute("list", list);
		return "demo/selectDevList";
	}
}
