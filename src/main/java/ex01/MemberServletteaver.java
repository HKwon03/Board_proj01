package ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/member.do")
public class MemberServletteaver extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get ��û�� doHandle �� ������. 
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//POST ��û�� doHandle �� ������. 
		doHandle(req, resp);
	}
	
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get �� post ����� ��ûó�� 
		
		System.out.println("MemberServlet Controller �� �۵���.  ");
		
		req.setCharacterEncoding("UTF-8");    //�ѱ��� ���� ����ó�� 
		resp.setContentType("text/html;charset=UTF-8");  //�������� ����Ҷ� html ���
		
		//DAO ��ü ���� : DAO �� �޼ҵ� ȣ���� ���� ��ü ����
		MemberDAOteaver dao = new MemberDAOteaver(); 
		MemberDTOteaver dto = new MemberDTOteaver(); 
		
		//��� ������ ���� �ٷ� HTML �� ��� ( out ��ü�� �����ؾ��� ) 
		//Ŭ���̾�Ʈ�� ���������� HTML�� ����� ������. 
		
		PrintWriter out = resp.getWriter();
		
		//Client �� Form ���� �ѱ� ���� ���� �޴´�. 
			//command = "addMember" �� ��� : DB�� ���� Insert �Ѵ�. 
			//command = "delMember" �� ��� : DB���� ���� �����Ѵ�. 
		
		String command = req.getParameter("command");   //form���� Post ��� hidden���� 
														//��ũ�� Get ������� �Ѿ�ü��� �ִ�. 
		
		if (command != null && command.equals("addMember")) {
			//DB�� ���� Insert
			
			//������ Post ������� �Ѿ���� ���� �޾ ������ �Ҵ�. 
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			
			System.out.println(id + " " + pwd + " " + name + " " + email);
			
			//DTO Setter �� ���� ���� 
			dto.setId(id);
			dto.setPwd(pwd);
			dto.setName(name);
			dto.setEmail(email); 
			
			int result = dao.addMember(dto);    //DB�� ���� Insert 
			dao.close();		// ��ü ���� (�ڿ� �ݳ�, rs, psmt, smmt, con)
			
			if (result == 1) {
				System.out.println("insert ������ ");
				resp.sendRedirect("/Proj01/member.do");
			} else if (result == 0) {
				System.out.println("insert ������");
			}
			
			
			
			
		}else if (command != null && command.equals("delMember") ) {
			//DB���� ���� Delete
			String id = req.getParameter("id");		// Get �������
			
			int result = dao.delMember(id);
			dao.close();	// ��ü ��� ����
			
			if(result == 1) {
				System.out.println("���� ������");
				resp.sendRedirect("/Proj01/member.do");
			}else if(result == 0) {
				System.out.println("���� ������");
			}
		}
		
		// DB�� t_member ���̺��� ���� ��� �����ͼ� ���
		// listMember�� DB�� select�� ����� DTO(���ڵ�)�� ��� listMember�� dto ����
		
		List<MemberDTOteaver> listMember = dao.listMember();
		
		out.print("<html><body>");
		out.print("<table border = 1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>���̵�</td> <td> ��й�ȣ </td><td> �̸� </td> <td> �̸��� </td> <td> ������</td> <td>����</td></tr>");
		
		// �� ��° tr���� ���� �����鼭 DB�� listMember���� DTO�� ������ getter�� ���
		
		for(int i=0; i<listMember.size();i++) {
			MemberDTOteaver dto2 = new MemberDTOteaver();
			dto2 = (MemberDTOteaver)listMember.get(i); 	// listMember�� ����� �� ���� dto ��ü�� ������ ����.
			// dto�� ����� �ʵ��� ���� getter�� ����ؼ� ������ �Ҵ�.
			String id = dto2.getId();
			String pwd = dto2.getPwd();
			String name = dto2.getName();
			String email = dto2.getEmail();
			Date joinDate = dto2.getJoinDate();
			
			out.print("<tr><td>" + id + " </td><td>" + pwd + " </td><td>"
					+ name + "</td><td>" + email + "</td><td>" + joinDate + "</td><td>"
					+ "<a href='/Proj01/member.do?command=delMember&id=" + id + "'> ���� </td> </tr>");
		}
		
		out.print("</table>");
		out.print("</body></html>");
		out.print("<a href = '/Proj01/memberForm.jsp'> �� ȸ����� �ϱ� </a>");
		
		
	}

	
	
	
}