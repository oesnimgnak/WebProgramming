package com.wp.memberjoin;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet({ "/MemberJoinServlet", "/join.do" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
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
		//모듈과 모듈사이에 파라멘터 값을 다 넘겨주기엔 코딩양이 많아져서 데이터를 클래스를 만들어 객체를 저장하고 객체를 넘겨줌(객체를 통해 데이터를 넘겨줌 도메인 객체, 데이터 객체, DTO)
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String inotice = request.getParameter("inotice");
		String cnotice = request.getParameter("cnotice");
		String dnotice = request.getParameter("dnotice");
		String job = request.getParameter("job");
		
		// step #2. process budiness logic
		//process member-foin logic
		//-> store member record to DB
		
		String errorMsg =null;
		
		if (id.equalsIgnoreCase("admin")) {
			errorMsg = "사용할 수 없는 아이디 입니다.";
		}
		
		// step #3. output results to client
		response.setContentType("Text/html;charset=UTF-8");
		
		
		PrintWriter out = response.getWriter();
		
		if (errorMsg != null) {	//입력 화면으로 돌아감.
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>회원 가입</title>");
			out.println("</head>");
			out.println("<body style = \"padding-left: 100px;\">");
			out.println("<script>");
			out.println("window.onload = () => {");
			out.println(" alert('"+ errorMsg +"');");
			out.println("window.history.back();");
			out.println("}");
			out.println("</script>");
			out.println("</body>");
			out.println("</html>");
			
			
		} else {	
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>회원 가입</title>");
		out.println("</head>");
		out.println("<body style = \"padding-left: 100px;\">");
		out.println("<h1>회원 가입 결과</h1>");
		out.println("<p>이름 :" + name + "</p>");
		out.println("<p>아이디 :" + id + "</p>");
		out.println("<p>비밀번호 :" + password + "</p>");
		out.println("<p>성별 :" + (gender.equals("male")? "남성" : "여성") + "</p>");
		out.println("<p>공지 메일 :" + checkMailReception(inotice) + "</p>");
		out.println("<p>광고 메일 :" + checkMailReception(cnotice) + "</p>");
		out.println("<p>배송확인 메일 :" + checkMailReception(dnotice) + "</p>");
		out.println("<p>직업 :" + job + "</p>");
		out.println("<p>위의 내용으로 회원 가입이 완료되었습니다!");
		out.println("</body>");
		out.println("</html>");
		
		}
		
		out.close();
		
	}
	private String checkMailReception(String notice) {
		String result = "수신 안함";
		if(notice != null&& notice.equals("on")) {
			result = "수신함";
		}
		return result;
	}

}
