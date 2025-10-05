package com.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TokenFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Token Filter called....");
		// public

		// private

		HttpServletRequest req = (HttpServletRequest) request;

		String url = req.getRequestURL().toString();

		System.out.println("url => " + url);

		boolean flag = true;
		if (!url.contains("/public/")) {
			// private
			// check for login --> token --> valid token
			String token = req.getHeader("token");

			if (token == null || !token.equals("111")) {
				flag = false;
			}

		}

		if (flag == true) {
			// go ahead
			chain.doFilter(request, response);
		} else {
			// error msg
			System.out.println("UNAUTHENTICATED USER FOUND");
			
		}
	}
}
