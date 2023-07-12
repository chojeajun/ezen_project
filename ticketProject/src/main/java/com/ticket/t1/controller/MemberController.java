package com.ticket.t1.controller;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
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

		String url="member/login";
		if( result.getFieldError("id") != null )
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage() );
		else if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else {

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", membervo.getId() );
			paramMap.put("ref_cursor", null);
			
			ms.getMember(paramMap);

			ArrayList< HashMap<String,Object> > list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

			if( list==null || list.size() == 0 ) {
				model.addAttribute("message" , "아이디가 없습니다");
				return "member/login";
			}
			System.out.println("존재하는 회원의 아이디 갯수는 " + list.size() + "개 입니다"); // 여기까진 탐
			HashMap<String, Object> mvo = list.get(0);
			if(( mvo.get("PWD") == null || mvo.get("PWD").equals("")) && !mvo.get("PROVIDER").equals("kakao")) {
				model.addAttribute("message" , "관리자에게 문의하세요");
				System.out.println("받은 비밀번호 : @@@@@@@@ "+ membervo.getPwd());
			} else if(!mvo.get("PWD").equals( membervo.getPwd() )) {
				model.addAttribute("message" , "비밀번호가 틀립니다");
				System.out.println("틀린 비밀번호 비교 : @@@@@@@@ "+ membervo.getPwd());
			} else if(mvo.get("USEYN").equals("N")) {
				model.addAttribute("message", "탈퇴 이력이 있습니다. 관리자에게 문의하세요");
			} else if( mvo.get("PWD").equals( membervo.getPwd() ) ) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);
				System.out.println("mvo =========" + mvo);
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
		ArrayList< HashMap<String, Object> > list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor" );
		if ( list == null || list.size() == 0 ) {

			paramMap.put("id", kakaoProfile.getId() );
			if(ac.getEmail() == null) {
				paramMap.put("email", "");
			}else {
				paramMap.put("email" , ac.getEmail());
			}
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
			@RequestParam(value="birth") String birth,
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
		else if( reid == null || (   reid != null && !reid.equals(membervo.getId() ) ) ) {
			model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다");
		} else if( pwdCheck == null || (  pwdCheck != null && !pwdCheck.equals(membervo.getPwd() ) ) ) {
			model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", membervo.getId() );
			paramMap.put("pwd", membervo.getPwd() );
			paramMap.put("name", membervo.getName() );
			paramMap.put("nickname", membervo.getNickname() );
			paramMap.put("gender", membervo.getGender() );
			paramMap.put("email", membervo.getEmail() );
			paramMap.put("phone", membervo.getPhone() );
			//paramMap.put("birth", membervo.getBirth() );
			
			membervo.setBirth(java.sql.Timestamp.valueOf(birth + " 00:00:00"));
			paramMap.put("birth", membervo.getBirth());
			paramMap.put("zip_num", membervo.getZip_num() );
			paramMap.put("address1", membervo.getAddress1() );
			paramMap.put("address2", membervo.getAddress2() );
			paramMap.put("address3", membervo.getAddress3() );
			//				paramMap.put("grade", membervo.getGrade() );
			//				paramMap.put("success", membervo.getSuccess() );
			//				paramMap.put("useyn", membervo.getUseyn() );
			ms.insertMember( paramMap );
			model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
			url = "member/login";
		}
		return url;
	}


	// 회원정보 수정 form ㄱ
	@RequestMapping("memberEditForm")
	public ModelAndView member_Edit_form(HttpServletRequest request ) {

		ModelAndView mav = new ModelAndView();
		MemberVO dto = new MemberVO();
		HttpSession session = request.getSession();
		// 로그인 됐는지 확인 세션에서 가져와서 loginUser 를 가져온다
		HashMap<String , Object> loginUser = (HashMap<String , Object>) session.getAttribute("loginUser");
		if(loginUser == null) {
			mav.setViewName("member/login");
		} else {
			
			//dto.setMseq(Integer.parseInt(loginUser.get("MSEQ").toString()));
			dto.setId((String)loginUser.get("ID"));
			dto.setName((String)loginUser.get("NAME"));
			dto.setNickname((String)loginUser.get("NICKNAME"));
			
			// dto.setGender(Integer.parseInt(loginUser.get("GENDER").toString()));
			// bit int 로 처리되서 tostring 로 해야함
			// System.out.println("GENDER" + dto.getGender());
			dto.setEmail((String)loginUser.get("EMAIL"));
			dto.setPhone((String)loginUser.get("PHONE"));
			dto.setBirth((Timestamp)loginUser.get("BIRTH"));
			System.out.println("수정폼으로 가져오는 생일 값" +  loginUser.get("BIRTH"));
			dto.setZip_num((String)loginUser.get("ZIP_NUM"));
			dto.setAddress1((String)loginUser.get("ADDRESS1"));
			dto.setAddress2((String)loginUser.get("ADDRESS2"));
			dto.setAddress3((String)loginUser.get("ADDRESS3"));
			dto.setProvider((String)loginUser.get("PROVIDER"));
			mav.addObject("dto", dto); // 로그인유세션을 dto 라는 이름으로
			mav.setViewName("member/updateForm");
		}
			
		return mav;
	}
	
	
	@RequestMapping(value = "/memberUpdate", method=RequestMethod.POST)
	public String memberUpdate( 
			@ModelAttribute("dto") @Valid MemberVO membervo, 	BindingResult result,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck,
			HttpServletRequest request, Model model, @RequestParam(value="birth") String birth) {
		
		String url = "member/updateForm";
		if( result.getFieldError("pwd") != null )
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage() );
		else if( result.getFieldError("name") != null )
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage() );
		else if( result.getFieldError("email") != null )
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage() );
		else if( result.getFieldError("phone") != null )
			model.addAttribute("message", result.getFieldError("phone").getDefaultMessage() );
		else if( pwdCheck == null || (  pwdCheck != null && !pwdCheck.equals(membervo.getPwd() ) ) ) 
			model.addAttribute("message", "비밀번호 확인 일치하지 않습니다");
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			// 세션(loginUser)에 대문자 키값의 HashMap 이 저장될 예정이므로 키값을 대문자로 지정
			paramMap.put("ID", membervo.getId());   
			paramMap.put("PWD", membervo.getPwd());
			paramMap.put("NAME", membervo.getName());
			paramMap.put("NICKNAME", membervo.getNickname());
			paramMap.put("EMAIL", membervo.getEmail());
			paramMap.put("PHONE", membervo.getPhone());
			paramMap.put("BIRTH", membervo.getBirth());
			paramMap.put("ZIP_NUM", membervo.getZip_num());
			paramMap.put("ADDRESS1", membervo.getAddress1());
			paramMap.put("ADDRESS2", membervo.getAddress2());
			paramMap.put("ADDRESS3", membervo.getAddress3());		
			
			System.out.println("수정할내용1 " + membervo.getId() );
			System.out.println("수정할내용2 " + membervo.getPwd() );
			System.out.println("수정할내용3 " + membervo.getName() );
			System.out.println("수정할내용4 " + membervo.getNickname() );
			System.out.println("수정할내용5 " + membervo.getEmail() );
			System.out.println("수정할내용6 " + membervo.getPhone() );
			//System.out.println("수정할내용7 birth:" + membervo.getBirth() );
			System.out.println("파라미터로 전달받은 생일" + birth);
			System.out.println("수정할내용8 " + membervo.getZip_num() );
			System.out.println("수정할내용9 " + membervo.getAddress1() );
			System.out.println("수정할내용10 " + membervo.getAddress2() );
			System.out.println("수정할내용11 " + membervo.getAddress3() );
			System.out.println( "업데이트 폼에서의 membervo :" +  membervo);
			// ms.updateMember( paramMap );
			membervo.setBirth(java.sql.Timestamp.valueOf(birth + " 00:00:00"));
			// mvo 를 보냈기때문에 sql 에서 소문자로 부를것
			ms.updateMember( membervo );
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", paramMap);
			url = "redirect:/";
		}
		return url;
	}
	
	 //자바스크립트 분기처리 중 kakao 로그인 유저라면 이쪽으로 와서 카카오 로그인 회원정보 수정으로
	@RequestMapping(value="/memberUpdateKakao", method=RequestMethod.POST)
	public String memberUpdateKakao(
		@ModelAttribute("dto") @Valid MemberVO membervo , BindingResult result,
		HttpServletRequest request , Model model , @RequestParam(value="birth") String birth
		) {
		String url = "member/updateForm";
		
		if(result.getFieldError("name") != null) {
			model.addAttribute("message" , result.getFieldError("name").getDefaultMessage());
		} else if(result.getFieldError("email") != null) {
			model.addAttribute("message" , result.getFieldError("email").getDefaultMessage());
		} else {
			HashMap<String , Object> paramMap = new HashMap<String , Object>();
			
			paramMap.put("ID", membervo.getId());
			
			paramMap.put("PWD" , "" ); // PWD 는 빈 값으로 수정
			//paramMap.put("PWD", membervo.getPwd());
			paramMap.put("NAME", membervo.getName());
			paramMap.put("NICKNAME", membervo.getNickname());
			paramMap.put("EMAIL", membervo.getEmail());
			paramMap.put("PHONE", membervo.getPhone());
			paramMap.put("BIRTH", membervo.getBirth());
			paramMap.put("ZIP_NUM", membervo.getZip_num());
			paramMap.put("ADDRESS1", membervo.getAddress1());
			paramMap.put("ADDRESS2", membervo.getAddress2());
			paramMap.put("ADDRESS3", membervo.getAddress3());
			paramMap.put("PROVIDER", "kakao"); //PROVIDER 카카오 로그인 시 kakao 라고  // 카카오 로그인이니까 
		
			System.out.println("카카오 수정 id" + membervo.getId());
			//System.out.println("카카오 수정 pwd" + membervo.getPwd()); // 카카오 로그인은 pwd 가 없으므로 null 이 나옴
			System.out.println("카카오 수정 name" + membervo.getName());
			System.out.println("카카오 수정 nickname" + membervo.getNickname());
			System.out.println("카카오 수정 email" + membervo.getEmail());
			System.out.println("카카오 수정 phone" + membervo.getPhone());
			//System.out.println("카카오 수정 birth" + membervo.getBirth());
			System.out.println("파라미터로 전달받은 카카오로그인 생일" + birth);
			System.out.println("카카오 수정 zip_num" + membervo.getZip_num());
			System.out.println("카카오 수정 address1" + membervo.getAddress1());
			System.out.println("카카오 수정 address2" + membervo.getAddress2());
			System.out.println("카카오 수정 address3" + membervo.getAddress3());
			
			membervo.setBirth(java.sql.Timestamp.valueOf(birth + " 00:00:00")); // Timestamp 형태로 변형 후 오라클 데이터포멧에맞는
			membervo.setPwd("");  // 비밀번호공백값 (null) 이긴하지만 실제로 파라미터는 null 이 아닌 빈 공백값을 보냄
			// Timestamp 형태로 변형 후 오라클 데이터포멧에맞는
			System.out.println("카카오톡회원수정폼 에서의 mvo" + membervo);
			// 형태로 변형해서 넣어준다
			
			// jsp 파일에서 넘겨주는 데이터가 string 형태여야 날짜 데이터를 받을 수 있는데 
			// membervo 에 birth 는 timestamp 형태이다 그래서 @rEQUESTpARAM 으로 받은 이후에 
			// sql.date 를 사용해서 데이터타입 형태를 변형해서 membervo 에 set 해줌
			
			//ms.updateMember(paramMap);
			ms.updateMember(membervo);
			HttpSession session = request.getSession();
			// 변경 사항을 session 에 paramMap 이라는 객체를 넣어서 갱신
			
			session.setAttribute("loginUser", paramMap);
			url = "redirect:/";
		}
		return url;
	}
	@RequestMapping("/myPage")
	public String my_page( HttpServletRequest request ) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser == null) 
			return "member/login";
		else 
			return "mypage/mypage";
		
		
	}
	
	@RequestMapping("/myRegister")
	public ModelAndView my_register( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", loginUser.get("MSEQ"));
			paramMap.put("ref_cursor", null);
			
			ms.getMyRegister(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("myRegister", list);
			mav.addObject("member", loginUser.get("NICKNAME"));
			mav.setViewName("mypage/myregister");
		}
		
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping("/myRegistered")
	public ModelAndView my_registered( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", loginUser.get("MSEQ"));
			paramMap.put("ref_cursor", null);
			
			ms.getMyRegister(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("registered", list);
			mav.addObject("member", loginUser.get("NICKNAME"));
			mav.setViewName("mypage/myregistered");
		}
		
		return mav;
	}
	
	@RequestMapping("/myAllRegister")
	public ModelAndView my_all_register( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if( loginUser == null ) {
			mav.setViewName("member/login");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mseq", loginUser.get("MSEQ"));
			paramMap.put("ref_cursor", null);
			
			ms.getMyRegister(paramMap);
			
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("myAllRegister", list);
			mav.addObject("member", loginUser.get("NICKNAME"));
			mav.setViewName("mypage/myallregister");
		}
		
		return mav;
	}


}
