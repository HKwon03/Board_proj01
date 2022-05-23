package ex01;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get
		MemberDAO dao = new MemberDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MemberDTO> boardLists = dao.selectListPage(map);
		dao.close();
		
		req.setAttribute("boardLists", boardLists);	//DataBase에서 Select한 결과 값
	    req.setAttribute("map", map);
	    req.getRequestDispatcher("/member/List.jsp").forward(req, resp);
		
	    
	   
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post
	}
	
}
