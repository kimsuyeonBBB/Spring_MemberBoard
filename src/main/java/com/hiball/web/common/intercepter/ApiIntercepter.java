package com.hiball.web.common.intercepter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ApiIntercepter extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(ApiIntercepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("ApiIntercepter preHandle");
		String body = parsingRequestBody(request.getInputStream());
		logger.debug("***************** Request JSON FORM {}", body);
		logger.debug("***************** Header Info {}", request.getHeaderNames());

		System.out.println("$$$$$$" + body + "!!!!!!!!!!!!!!");

		JSONParser parser = new JSONParser();
		Object paramObj = parser.parse(body);

		request.setAttribute("apiRequestParam", paramObj);

		return true;
	}

	private String parsingRequestBody(InputStream is) throws Exception {
		String body = "";
		StringBuilder sb = new StringBuilder();

		BufferedReader br = null;

		try {

			if (is != null) {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;

				while ((bytesRead = br.read(charBuffer)) > 0) {
					sb.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ie) {
			throw ie;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ie) {
					throw ie;
				}
			}
		}

		body = sb.toString();

		return body;
	}
}
