package ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/member/delete.do")
public class DeleteController extends HttpServlet {
	
	
	MemberDAO dao = new MemberDAO();
	
	public static void alertLocation (HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			String script = ""
						  + "<script>"
						  + "    alert('" + msg + "');"
						  + "    location.href = '" + url + "';"
						  + "</script>";
			
			writer.print(script);
			
		} catch(Exception e) {
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get
		req.getRequestDispatcher("/member/Delete.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post
		String id = req.getParameter("id");
		
		int result = dao.deletePost(id);
		dao.close();
		
		if(result == 1) {
			System.out.println("삭제 성공공");
			dao.deletePost(id);	//게시물 삭제
		}else if (result == 0) {
			System.out.println("삭제 실패패");
		}
		
		alertLocation(resp, " ", "../member/list.do");
		
	}
	
}
