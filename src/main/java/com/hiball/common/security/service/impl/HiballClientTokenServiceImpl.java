package com.hiball.common.security.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiball.common.security.dao.TokenServiceDao;
import com.hiball.common.security.domain.User;
import com.hiball.common.security.service.HiballClientTokenService;
import com.hiball.common.security.service.UserService;


@Service("tokenService")
public class HiballClientTokenServiceImpl implements HiballClientTokenService{
	private static Logger logger = LoggerFactory.getLogger(HiballClientTokenServiceImpl.class);
	private final String authTerminateUrl = "http://localhost:8081/s_auth/token/terminate";
	/*private final String authTerminateUrl = "http://220.68.46.42:8080/s_auth/token/terminate";*/
	
	@Autowired
	TokenServiceDao tokenServiceDao;
	@Autowired
	UserService userService;

	@Override
	public void confirmUserLogin(HttpServletRequest req, Map<String, String> tokenInfoMap) {
		try {
			User user = userService.loadUserByUsername(tokenInfoMap.get("accessUserId"));
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = req.getSession(true);
			
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		}  catch (UsernameNotFoundException e) {
			logger.debug(e.toString());
			throw new UsernameNotFoundException(e.getMessage());
		} 
	}
	
	@Override
	public Map<String, Object> terminateToken(HttpServletRequest req, Map<String, Object> parameterMap) throws ServletException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			// Post방식으로 전송 하기
			url = new URL(authTerminateUrl);
			con = (HttpURLConnection)url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestMethod("POST");
			
			printByOutputStream(con.getOutputStream(), parameterMap);
			resultMap.put("result", printByInputStream(con.getInputStream()));
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		return resultMap;
	}
	
	// 웹 서버로 파라미터명과 값의 쌍을 전송하는 메소드
	public static void printByOutputStream(OutputStream os, Map<String, Object> parameterMap) throws IOException {
		String strJSON = "";
		Iterator<String> keySet = parameterMap.keySet().iterator();
		try {
			JSONObject paramJSON = new JSONObject();
			strJSON = JSONObject.toJSONString(parameterMap);
			
			os.write(strJSON.getBytes("UTF-8"));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	
	// 웹 서버로 부터 받은 웹 페이지 결과를 콘솔에 출력하는 메소드
	public static String printByInputStream(InputStream is) {
		byte[] buf = new byte[1024];
		int len = -1;
		StringBuilder strResult = new StringBuilder();
		try {
			while ((len = is.read(buf, 0, buf.length)) != -1) {
				strResult.append(len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strResult.toString();
	}

	@Override
	public Token allocateToken(String extendedInformation) {
		return null;
	}

	@Override
	public Token verifyToken(String key) {
		return null;
	}
	
}
