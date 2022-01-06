/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
package com.gov.nha.bis.server.config.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gov.nha.bis.server.rest.service.SaveKycDataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NhBisMcLoginFilter implements Filter, WebMvcConfigurer{

	
	private static final Logger logger = LogManager.getLogger(SaveKycDataService.class);
	
	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig)
	{
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		 
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletresponse = (HttpServletResponse)response;
		
		httpServletresponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletresponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpServletresponse.setHeader("Access-Control-Allow-Methods", "RDSERVICE, DEVICEINFO, CAPTURE, POSTAUTH,GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD");
		httpServletresponse.setHeader("Access-Control-Allow-Headers", "*");

		
		
		
		
		
		String requestURI = httpServletRequest.getRequestURI();
		
		boolean isStaticResource = httpServletRequest.getRequestURI().contains("dist/");
		boolean isStaticResource1 = httpServletRequest.getRequestURI().contains("plugins/");
		boolean isStaticResource2 = httpServletRequest.getRequestURI().contains("image/");
		boolean isStaticResource3 = httpServletRequest.getRequestURI().contains("images/");
		boolean isStaticResourceJs = httpServletRequest.getRequestURI().contains("js/");
		boolean isStaticResourceCss = httpServletRequest.getRequestURI().contains("css/");
		boolean isStaticResourceSass = httpServletRequest.getRequestURI().contains("sass/");
		HttpSession session = httpServletRequest.getSession(false);
		
			

		if (session == null) {
			session = httpServletRequest.getSession(true);
		}

		String login = (String) session.getAttribute("Bis_Login");
		//logger.info("login"+login);
		if(login==null)
		{	
			if(isStaticResource || isStaticResource1||isStaticResource2 || isStaticResource3 || isStaticResourceJs || isStaticResourceCss || isStaticResourceSass) {
				chain.doFilter(request, response);
			}else if(requestURI.contains("/cscoplogin")){
				RequestDispatcher rd = request.getRequestDispatcher("cscoplogin");
				rd.forward(request, response);
			}else if(requestURI.contains("/usrAuthResponse")){
				RequestDispatcher rd = request.getRequestDispatcher("usrAuthResponse");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/captach")){
				RequestDispatcher rd = request.getRequestDispatcher("/captach");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/index")){
				RequestDispatcher rd = request.getRequestDispatcher("index");
				rd.forward(request, response);
			}else if(requestURI.contains("/login")){
				RequestDispatcher rd = request.getRequestDispatcher("login");
				rd.forward(request, response);
			}else if(requestURI.contains("/ophome")){
				RequestDispatcher rd = request.getRequestDispatcher("ophome");
				rd.forward(request, response);
			}else if(requestURI.contains("/usrHome")){
				RequestDispatcher rd = request.getRequestDispatcher("usrHome");
				rd.forward(request, response);
			}else if(requestURI.contains("/cscHome")){
				RequestDispatcher rd = request.getRequestDispatcher("cscHome");
				rd.forward(request, response);
			}else if(requestURI.contains("/home")){
				RequestDispatcher rd = request.getRequestDispatcher("home");
				rd.forward(request, response);
			}
			else if(requestURI.contains("/genMobileOtp")){
				RequestDispatcher rd = request.getRequestDispatcher("genMobileOtp");
				rd.forward(request, response);
			}else if(requestURI.contains("/smsOtpSender")){
				RequestDispatcher rd = request.getRequestDispatcher("smsOtpSender");
				rd.forward(request, response);
			}else if(requestURI.contains("/logout")){
				RequestDispatcher rd = request.getRequestDispatcher("logout");
				rd.forward(request, response);
			}else if(requestURI.contains("/sessionExpire")){
				RequestDispatcher rd = request.getRequestDispatcher("sessionExpire");
				rd.forward(request, response);
			}else if(requestURI.contains("/beneficiaryAddFamilyMemeber")){
				RequestDispatcher rd = request.getRequestDispatcher("beneficiaryAddFamilyMemeber");
				rd.forward(request, response);
			}else if(requestURI.contains("/faceId")){
				RequestDispatcher rd = request.getRequestDispatcher("faceId");
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("bis");
				rd.forward(request, response);
			}
		}
		else{
			chain.doFilter(request, response);
		} 
	}

	public void destroy()    
	{
	}

}
