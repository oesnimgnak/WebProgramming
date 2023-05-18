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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScoreInputServlet
 */
@WebServlet("/score-input3.do")
public class ScoreInputServletBySession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreInputServletBySession() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		
		// step #2. process business logic
		HttpSession session = request.getSession();
		if (session.isNew()) {
			String scores = "";
			int count = 0;
			session.setAttribute("scores", scores);
			session.setAttribute("count", count);
		}
			
		int count = (int)session.getAttribute("count");
		
		// step #3. output results to client
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>");
		out.println("<title>성적 입력</title>");
		out.println("<style>");
		out.println("td { padding: 5px }");
		out.println("</style></head>");
		out.println("<body><h1>성적 처리</h1><hr><br>");
		out.println("<form action='score-input3.do' method='POST'>");
		out.println("<table><tbody>");
			out.println("<tr><td>성적 : </td>");
			out.println("<td><input type='number' size='10' min='0' max='100' name='score' /></td></tr>");
			out.printf("<tr><td colspan='2'>현재 성적입력 학생 수 : %d </td></tr>", count);
			out.println("<tr><td colspan='2' align='center'>");
			out.println("<input type='submit' value='입력' name='action' />");
			out.println("<input type='submit' value='출력' name='action' />");
			out.println("</td></tr>");
		out.println("</tbody></table>");
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
		HttpSession session = request.getSession(false);
		if (session == null) {
			// incorrect access, and redirect to the first page
			response.sendRedirect("score-input3.do");
			return;
		}
		
		String scores = (String)session.getAttribute("scores");	
		String scores2 = scores;
		scores = scores + "test";
		System.out.println(scores);
		System.out.println((scores == scores2) ? "EQUAL" : "NOT EQUAL");
		int count = (int)session.getAttribute("count");
				
		// step #3. output results to client
		if (action.equals("입력")) {
			scores = scores + "+" + score;
			session.setAttribute("scores", scores);
			count += 1;
			session.setAttribute("count", count);
			
			response.sendRedirect(request.getHeader("Referer"));;			
		}
		else if (action.equals("출력")) {
			String[] sa = scores.split("\\+");
			
			ScoresDO scoresDo = new ScoresDO();
			scoresDo.setScores(sa);
			
			ScoreProcessingService service =
					new ScoreProcessingService();
			service.processScores(scoresDo);
			
			// step #3. output results to client(presentation logic)
			request.setAttribute("scores", scoresDo);
			
			session.invalidate();
			
			RequestDispatcher view = 
				request.getRequestDispatcher("/output.do");
			view.forward(request, response);	
		}
	}

}
