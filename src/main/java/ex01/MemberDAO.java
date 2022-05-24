package ex01;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	//DB�� Connection ��ü�� ����ؼ� ���� �ߺ� �ڵ带 ������ �� �ִ�.
	
	public MemberDAO () {
		super(); 			//DBConnPool��ü�� �⺻ ������ ȣ�� , DBCP���� con ��ü Ȱ��ȭ
	}
	
	// List.jsp ����� ���� select
	public List<MemberDTO> selectListPage(Map<String, Object> map) {
						// ����� : listMember = new ArrayList<MemberDTO>();
		List<MemberDTO> board = new Vector<MemberDTO>();
		 String query = " "
                 + "SELECT * FROM t_member"
		 		 + " ORDER BY joinDate DESC ";
		 
		 // System.out.println(query);
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
				//rs�� DB���� Select�� ��� ��(���ڵ��)�� ����
			
			//rs�� ����� ���ڵ���� DTO�� ������ list�� add ���ش�
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				String id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				Date joinDate = rs.getDate(5);
				
				dto.setId(id);
				dto.setPwd(pwd);
				dto.setName(name);
				dto.setEmail(email);
				dto.setJoindate(joinDate);
				
				board.add(dto);
			}
			
		} catch(Exception e) {
			System.out.println("�Խù� ��ȸ�� ���� �߻�");
			e.printStackTrace();
		}
		return board;
	}
	
	
	// Insert.jsp db�� insert
	
	public int insertWrite (MemberDTO dto) {
		
		int result = 0 ; 
		try {
			String query = "INSERT INTO t_member ( "
					+ " id, pwd, name, email) "
					+ " VALUES ("
					+ " ?, ?, ?, ?)"; 
			
		psmt = con.prepareStatement(query);  //PareparedStatement ��ü ���� 
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPwd());
		psmt.setString(3, dto.getName());
		psmt.setString(4, dto.getEmail());
		
		result = psmt.executeUpdate();  //insert�� �����϶� result > 0   //DB �� ���� ���� 
				//result : 0 �϶� <== Insert  ����, result : 1�϶� insert ����
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result; 		// result : Insert ������ > 0 , ���н� : 0 
	}
	
	
	
	// delete.jsp db���� delete
		//�Ű������� Primary Key �÷��� �������� �޾Ƽ� ó��
	public int deletePost(String id) {
		
		int result = 0;
		
		String query = "DELETE t_member WHERE id = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			result = psmt.executeUpdate();	
			
			// System.out.println(query);
			// System.out.println(id);
			// System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete �� ���� �߻�");
		}
		return result;
	}
	
	
	//proj01.war ���Ϸ� �������� �ؼ� ����
		
	
}
