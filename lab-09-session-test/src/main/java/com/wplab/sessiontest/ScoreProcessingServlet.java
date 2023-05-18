package com.wplab.sessiontest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreProcessingServlet
 */
@WebServlet("/process.do")
public class ScoreProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreProcessingServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String[] scores = request.getParameterValues("scores");
				
		// step #2. process business logic
		ScoresDO scoresDo = new ScoresDO();
		scoresDo.setScores(scores);
		
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
