package com.ticket.t1.controller;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ticket.t1.dto.KakaoProfile;
import com.ticket.t1.dto.KakaoProfile.KakaoAccount;
import com.ticket.t1.dto.MemberVO;
import com.ticket.t1.dto.OAuthToken;
import com.ticket.t1.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;


	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
		 
	
	
	// dto 라는 이름으로 객체를 받겠다 , 밸리데이션 membervo 진행하겠다 제약조건이름은 result
	// jsp파일의 form action = login 을 타고 이리 옴  
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(
			@ModelAttribute("dto") @Valid MemberVO membervo,	BindingResult result,
			HttpServletRequest request,	Model model	) {
		
		String url="member/login.jsp";
		if( result.getFieldError("id") != null )
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
		else if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", membervo.getId() );
			paramMap.put("ref_cursor", null);
			ms.getMember(paramMap);
			
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if( list==null || list.size() == 0 ) {
				model.addAttribute("message" , "아이디가 없습니다");
				return "member/login";
			}
			System.out.println("존재하는 회원의 아이디 갯수는 " + list.size() + "개 입니다"); // 여기까진 탐
			HashMap<String, Object> mvo = list.get(0);
			if( mvo.get("PWD") == null ) {
				model.addAttribute("message" , "관리자에게 문의하세요");
				System.out.println("받은 비밀번호 : @@@@@@@@ "+ membervo.getPwd());
			} else if(!mvo.get("PWD").equals( membervo.getPwd() )) {
				model.addAttribute("message" , "비밀번호가 틀립니다");
				System.out.println("틀린 비밀번와 비교 : @@@@@@@@ "+ membervo.getPwd());
			} else if( mvo.get("PWD").equals( membervo.getPwd() ) ) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				url = "redirect:/";
			}
		}
		return url;
	}
	

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	
	// 카카오톡으로 로그인
	@RequestMapping("/kakaostart") // jsp 파일의 href kakaostart 타고 옴
	public @ResponseBody String kakaostart() { // 비동기식 통신
		// 카카오 로그인 rest api key 를 사용
		String a = "<script type='text/javascript'>" + "location.href='https://kauth.kakao.com/oauth/authorize?"
				+ "client_id=bab305fbb8a02d4bd91ccc64d508aabe&" + "redirect_uri=http://localhost:8070/kakaoLogin&"
				+ "response_type=code';" + "</script>";
		return a; // 아무거나 리턴시켜도 되나?
	}
	
	
//	@RequestMapping("/kakaoLogin")
//	public String loginKakao(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
//
//		String code = request.getParameter("code");
//		String endpoint = "https://kauth.kakao.com/oauth/token";
//		URL url = new URL(endpoint); // import java.net.URL;
//		String bodyData = "grant_type=authorization_code&";
//		bodyData += "client_id=bab305fbb8a02d4bd91ccc64d508aabe&";
//		bodyData += "redirect_uri=http://localhost:8070/kakaoLogin&";
//		bodyData += "code=" + code;
//		
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // import java.net.HttpURLConnection;
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//		conn.setDoOutput(true);
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//		bw.write(bodyData);
//		bw.flush();
//		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//		String input = "";
//		StringBuilder sb = new StringBuilder(); // 조각난 String 을 조립하기위한 객체
//		while ((input = br.readLine()) != null) {
//			sb.append(input);
//			System.out.println(input); // 수신된 토큰을 콘솔에 출력합니다
//		}
//		Gson gson = new Gson();
//		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);
//		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
//		URL url2 = new URL(endpoint2);
//		
//		HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
//		conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
//		conn2.setDoOutput(true);
//		BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
//		String input2 = "";
//		StringBuilder sb2 = new StringBuilder();
//		while ((input2 = br2.readLine()) != null) {
//			sb2.append(input2);
//			System.out.println(input2);
//		}
//		
//		Gson gson2 = new Gson();
//		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);
//		KakaoAccount ac = kakaoProfile.getAccount();
//		//Profile pf = ac.getProfile();
//		Profile pf = ac.getProfile(); // 객체를 가져오는 캐스팅이 안된다? 
//		// 강제형변환을 강제로하면 작동은 함
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", kakaoProfile.getId() );
//		paramMap.put("ref_cursor", null );
//		ms.getMember( paramMap );
//		ArrayList< HashMap<String, Object> > list 
//				= (ArrayList< HashMap<String, Object> >) paramMap.get("ref_cursor" );
//		if ( list == null || list.size() == 0 ) {
//			paramMap.put("id", kakaoProfile.getId() );
//			paramMap.put("email" , ac.getEmail());
//			paramMap.put("name" , ((MemberVO) pf).getNickname());
//			paramMap.put("provider" , "kakao");
//			ms.joinKakao( paramMap );
//			
//			paramMap.put("ref_cursor", null );
//			ms.getMember( paramMap );
//			list = (ArrayList< HashMap<String, Object> >) paramMap.get("ref_cursor" );
//		}
//		HashMap<String , Object> mvo = list.get(0);
//		HttpSession session = request.getSession();
//		session.setAttribute("loginUser", mvo);
//		return "redirect:/";
//	}
//	
	
	
	
	
	@RequestMapping("/kakaoLogin")
	public String loginKakao(HttpServletRequest request) throws UnsupportedEncodingException, IOException {

		String code = request.getParameter("code");
		String endpoint = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(endpoint); // import java.net.URL;
		String bodyData = "grant_type=authorization_code&";
		bodyData += "client_id=bab305fbb8a02d4bd91ccc64d508aabe&";
		bodyData += "redirect_uri=http://localhost:8070/kakaoLogin&";
		bodyData += "code=" + code;
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // import java.net.HttpURLConnection;
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(bodyData);
		bw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String input = "";
		StringBuilder sb = new StringBuilder(); // 조각난 String 을 조립하기위한 객체
		while ((input = br.readLine()) != null) {
			sb.append(input);
			System.out.println(input); // 수신된 토큰을 콘솔에 출력합니다
		}
		Gson gson = new Gson();
		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);
		String endpoint2 = "https://kapi.kakao.com/v2/user/me";
		URL url2 = new URL(endpoint2);
		
		HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
		conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
		conn2.setDoOutput(true);
		BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
		String input2 = "";
		StringBuilder sb2 = new StringBuilder();
		while ((input2 = br2.readLine()) != null) {
			sb2.append(input2);
			System.out.println(input2);
		}
		
		Gson gson2 = new Gson();
		KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);
		KakaoAccount ac = kakaoProfile.getAccount();
		com.ticket.t1.dto.KakaoProfile.KakaoAccount.Profile pf = ac.getProfile();
		
				
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", kakaoProfile.getId() );
		paramMap.put("ref_cursor", null );
		ms.getMember( paramMap );
		ArrayList< HashMap<String, Object> > list = (ArrayList< HashMap<String, Object> >) paramMap.get("ref_cursor" );
		if ( list == null || list.size() == 0 ) {
			
			paramMap.put("id", kakaoProfile.getId() );
			paramMap.put("email" , ac.getEmail());
			paramMap.put("name" , pf.getNickname());
			paramMap.put("provider" , "kakao");
			ms.joinKakao( paramMap );
			
			paramMap.put("ref_cursor", null );
			ms.getMember( paramMap );
			list = (ArrayList< HashMap<String, Object> >) paramMap.get("ref_cursor" );
		}
		HashMap<String , Object> mvo = list.get(0);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", mvo);
		return "redirect:/";
	}
	
	
	// 회원가입 창 ㄱ
		@RequestMapping("/contract")
		public String contract(Model model, HttpServletRequest request) {
			return "member/contract";
		}
	
	
		// 회원가입 창 ㄱ
		@RequestMapping("/joinForm")
		public String join_form() {
			
			return "member/joinForm";
		}
	
		
		@RequestMapping("/idCheckForm")
		public ModelAndView id_check_form( @RequestParam("id") String id ) {
			
			ModelAndView mav = new ModelAndView();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			paramMap.put("ref_cursor", null);
			ms.getMember(paramMap);
			ArrayList<HashMap<String, Object>> list =(ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			if( list==null || list.size() == 0) {
				mav.addObject("result" , -1);
			} else {
				mav.addObject("result", 1);
			} 
			// id , result 값을 리턴받아서 넣어주고 idcheck.jsp 로 리턴
			mav.addObject("id" , id);
			mav.setViewName("member/idcheck");
			return mav;
		}
		
		
		@RequestMapping(value="/join", method=RequestMethod.POST)
		public String join(
				@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
				@RequestParam(value="reid", required=false) String reid,
				@RequestParam(value="pwdCheck", required=false) String pwdCheck,
				HttpServletRequest request, Model model	) {
			String url = "member/joinForm";
			if( result.getFieldError("id")!=null)
				model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
			else if( result.getFieldError("pwd")!=null)
				model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
			else if( result.getFieldError("name")!=null)
				model.addAttribute("message", result.getFieldError("name").getDefaultMessage() );
			else if( result.getFieldError("email")!=null)
				model.addAttribute("message", result.getFieldError("email").getDefaultMessage() );
			else if( reid == null || (   reid != null && !reid.equals(membervo.getId() ) ) )
					model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다");
			else if( pwdCheck == null || (  pwdCheck != null && !pwdCheck.equals(membervo.getPwd() ) ) ) 
				model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
			else {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", membervo.getId() );
				paramMap.put("pwd", membervo.getPwd() );
				paramMap.put("name", membervo.getName() );
				paramMap.put("email", membervo.getEmail() );
				paramMap.put("phone", membervo.getPhone() );
				paramMap.put("nickname", membervo.getNickname() );
				paramMap.put("gender", membervo.getGender() );
				paramMap.put("birth", membervo.getBirth() );
				paramMap.put("zip_num", membervo.getZip_num() );
				paramMap.put("address1", membervo.getAddress1() );
				paramMap.put("address2", membervo.getAddress2() );
				paramMap.put("address3", membervo.getAddress3() );
				paramMap.put("grade", membervo.getGrade() );
				paramMap.put("success", membervo.getSuccess() );
				paramMap.put("useyn", membervo.getUseyn() );
				ms.insertMember( paramMap );
				model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
				url = "member/login";
			}
			return url;
		}
		
		
	
		
		
	
}
