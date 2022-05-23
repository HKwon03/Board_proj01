package ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/insert.do")
public class InsertController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get
		
		req.getRequestDispatcher("/member/Insert.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post
		
		MemberDTO dto = new MemberDTO ();
		dto.setId(req.getParameter("id"));
		dto.setPwd(req.getParameter("pwd"));
		dto.setName(req.getParameter("name"));
		dto.setEmail(req.getParameter("email"));
		dto.setJoindate(req.getParameter("joindate"));
	    
		
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.insertWrite(dto);
		
		//객체 종료 메소드 호출(rs, stmt, psmt, con 모두 종료)
		dao.close();
		
		//글쓰기 성공일 때 이동할 페이지
		if(result == 1) {	//글쓰기 성공일 때 list 페이지로 이동
			resp.sendRedirect("../member/list.do");
		}
		
		//글쓰기 실패일 때 이동할 페이지
		if(result == 0) {	//글쓰기 실패일 때 다시 글쓰기 페이지로 이동
			resp.sendRedirect("../member/insert.do");
		}
		
	}
	
	 

}
