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
		
		//��ü ���� �޼ҵ� ȣ��(rs, stmt, psmt, con ��� ����)
		dao.close();
		
		//�۾��� ������ �� �̵��� ������
		if(result == 1) {	//�۾��� ������ �� list �������� �̵�
			resp.sendRedirect("../member/list.do");
		}
		
		//�۾��� ������ �� �̵��� ������
		if(result == 0) {	//�۾��� ������ �� �ٽ� �۾��� �������� �̵�
			resp.sendRedirect("../member/insert.do");
		}
		
	}
	
	 

}
