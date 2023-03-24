package com.wp.caleulator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculate.do")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
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
		
		Double num1 = Double.parseDouble(request.getParameter("num1"));
		Double num2 = Double.parseDouble(request.getParameter("num2"));	//완성도 있지는 않음 왜? 사용자가 값을 입력하지않고 서브밋햇을 경우: 엔프티스트링
		String operator = request.getParameter("operator");
		
		// step #2. process business logic
		Double result =0.0;
		
		if(operator.equals("+")) result = num1+num2;
		else if(operator.equals("-")) result = num1-num2;
		else if(operator.equals("*")) result = num1*num2;
		else if(operator.equals("/")) result = num1/num2;
		
		// step #3. output results to client
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>사칙 연산 계산기</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Calculator</h1>");
		out.println(num1 + " " + operator + " " +num2 + "=" + result);
		out.println("<hr>");
		out.println("<p><input type='button' value='<< Go Back' onclick='window.location.href=Calculator.html;' /></p>");
		//out.println("<p><input type='button' value='<< Go Back' onclick='window.history.go(-1);' /></p>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
