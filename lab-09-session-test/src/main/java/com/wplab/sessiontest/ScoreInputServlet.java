package com.wplab.sessiontest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreInputServlet
 */
@WebServlet("/score-input.do")
public class ScoreInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// step #1. get request parameters
		
		// step #2. process business logic
		int count = 0;
		
		// step #3. output results to client
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head>");
		out.println("<title>성적 입력</title>");
		out.println("<style>");
		out.println("td { padding: 5px }");
		out.println("</style></head>");
		out.println("<body><h1>성적 처리</h1><hr><br>");
		out.println("<form action='score-input.do' method='POST'>");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
