package com.wplab.sessiontest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreInputServlet
 */
@WebServlet("/score-input2.do")
public class ScoreInputServletByCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreInputServletByCookie() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		
		// step #2. process business logic
		String countStr = request.getParameter("count");
		int count = (countStr == null || countStr.isEmpty()) ? 
				0 : Integer.parseInt(countStr);
		
		// step #3. output results to client
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>");
		out.println("<title>성적 입력</title>");
		out.println("<style>");
		out.println("td { padding: 5px }");
		out.println("</style></head>");
		out.println("<body><h1>성적 처리</h1><hr><br>");
		out.println("<form action='score-input1.do' method='POST'>");
		out.println("<table><tbody>");
			out.println("<tr><td>성적 : </td>");
			out.println("<td><input type='number' size='10' min='0' max='100' name='score' /></td></tr>");
			out.printf("<tr><td colspan='2'>현재 성적입력 학생 수 : %d </td></tr>", count);
			out.println("<tr><td colspan='2' align='center'>");
			out.println("<input type='submit' value='입력' name='action' />");
			out.println("<input type='submit' value='출력' name='action' />");
			out.println("</td></tr>");
		out.println("</tbody></table>");
		out.printf("<input type='hidden' name='count' value='%d' />\n", count);
		out.println("</form></body></html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String score = request.getParameter("score");
		
		// step #2. process business logic
		Cookie[] cookies = request.getCookies();
		Cookie scores = findCookie(cookies, "scores");
		if (scores == null)
			scores = new Cookie("scores", "");
		Cookie countCookie = findCookie(cookies, "count");
		if (countCookie == null)
			countCookie = new Cookie("count", "0");
		int count = Integer.parseInt(countCookie.getValue());
				
		// step #3. output results to client
		if (action.equals("입력")) {
			scores.setValue(scores.getValue() + "+" + score);
			countCookie.setValue(String.valueOf(count+1));
			
			response.addCookie(scores);
			response.addCookie(countCookie);
			
			response.sendRedirect(request.getHeader("Referer"));;			
		}
		else if (action.equals("출력")) {
			String[] sa = scores.getValue().split("\\+");
			
			ScoresDO scoresDo = new ScoresDO();
			scoresDo.setScores(sa);
			
			ScoreProcessingService service =
					new ScoreProcessingService();
			service.processScores(scoresDo);
			
			// step #3. output results to client(presentation logic)
			request.setAttribute("scores", scoresDo);
			
			RequestDispatcher view = 
				request.getRequestDispatcher("/output.do");
			view.forward(request, response);	
		}
	}

}
