package ex01;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	
	public MemberDAO () {
		super(); 			//DBConnPool��ü�� �⺻ ������ ȣ�� , DBCP���� con ��ü Ȱ��ȭ
	}
	
	// List.jsp ����� ���� select
	public List<MemberDTO> selectListPage(Map<String, Object> map) {
		List<MemberDTO> board = new Vector<MemberDTO>();
		String query = " "
					 + "select * from t_member"
					 + " order by joinDate desc" ;
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setJoindate(rs.getDate(5));
				
				board.add(dto);
			}
			
		} catch(Exception e) {
			System.out.println("�Խù� ��ȸ�� ���� �߻�");
			e.printStackTrace();
		}
		return board;
	}
	
	
	// Insert.jsp db�� insert
	
	
	// delete.jsp db���� delete
	
	
	
	//proj01.war ���Ϸ� �������� �ؼ� ����
		
	
}
