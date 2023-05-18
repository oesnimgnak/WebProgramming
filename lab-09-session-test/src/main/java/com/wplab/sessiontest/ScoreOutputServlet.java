package com.wplab.sessiontest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreOutputServlet
 */
@WebServlet("/output.do")
public class ScoreOutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreOutputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
				
		// step #2. process business logic
		ScoresDO scoresDo = 
			(ScoresDO) request.getAttribute("scores");
		
		// step #3. output results to client(presentation logic)
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>성적 입력</title>");
		out.println("</head><body>");
		out.println("<h1>성적 처리</h1><hr><br>");
		out.println("<p>1. 입력 성적 : </p>");
		out.println("<p>");
		int[] scoreArray = scoresDo.getScores();
		for (int i=0; i<scoreArray.length; i++) {
			out.printf("%d, ", scoreArray[i]);
		}
		out.println("</p><br>");
		out.println("<p>2. 성적 평균: " + scoresDo.getMean() + "</p><br>");
		out.println("<p>3. 표준 편차: " + scoresDo.getSd() + "</p><br>");
		out.println("</body></html>");
		
		out.close();
	}

}
