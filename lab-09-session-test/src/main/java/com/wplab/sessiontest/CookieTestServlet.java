package com.wplab.sessiontest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CookieTestServlet
 */
@WebServlet("/visit.do")
public class CookieTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int visitCount = 0;
    
	private Cookie findCookie(Cookie[] cookies, String name) {
		Cookie cookie = null;
		
		if (cookies != null && name != null) {
			for (int i=0; i<cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					cookie = cookies[i];
					break;
				}
			}
		}
		
		return cookie;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		
		// step #2. process business logic
		Cookie[] cookies = request.getCookies();
		Cookie visitCookie = 
			findCookie(cookies, "visit_count");
		
		int visitCnt = 0;
		if (visitCookie != null) {
			visitCnt = Integer.parseInt(visitCookie.getValue());
		}
		else {
			visitCookie = new Cookie("visit_count", "0");
		}
		
		visitCnt++;
		
		// step #3. output results to client
		response.setContentType("text/html;charset=UTF-8");
		
		visitCookie.setValue(String.valueOf(visitCnt));
		visitCookie.setMaxAge(0);
		response.addCookie(visitCookie);
		
		PrintWriter out = response.getWriter();
		
		out.printf("<h1>Visit Count = %d</h1>", visitCnt);
		
		out.close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
