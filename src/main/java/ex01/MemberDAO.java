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
                 + "SELECT * FROM t_member"
		 		 + " ORDER BY joinDate DESC ";
		 
		 // System.out.println(query);
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setJoindate(rs.getString(5));
				
				
			    /*
                System.out.println("DB���� ������ ��(id) : " + rs.getString(1));
                System.out.println("DB���� ������ ��(name) : " + rs.getString(2));
         
                System.out.println("================================");
                
                System.out.println("DTO���� ������ ��(id) : " + dto.getId());
                System.out.println("DTO���� ������ ��(name) : " + dto.getName());
                */
				
				
				board.add(dto);
				// System.out.println(dto);
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
					+ " id, pwd, name, email, joindate) "
					+ " VALUES ("
					+ " ?, ?, ?, ?, ?)"; 
			
		psmt = con.prepareStatement(query);  //PareparedStatement ��ü ���� 
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPwd());
		psmt.setString(3, dto.getName());
		psmt.setString(4, dto.getEmail());
		psmt.setString(5, dto.getJoindate());
		
		result = psmt.executeUpdate();  //insert�� �����϶� result > 0   //DB �� ���� ���� 
				//result : 0 �϶� <== Insert  ����, result : 1�϶� insert ����
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result; 		// result : Insert ������ > 0 , ���н� : 0 
	}
	
	
	
	// delete.jsp db���� delete
	
			
	 public MemberDTO selectView(String id) {
	    	MemberDTO dto = new MemberDTO();
	    	String query = "SELECT * FROM t_member WHERE id = ?";
	    	
	    	try {
	    		psmt = con.prepareStatement(query);
	    		psmt.setString(1, id);
	    		rs = psmt.executeQuery();
	    		
	    		if(rs.next()) {
	    			dto.setId(rs.getString(1));
	    			dto.setPwd(rs.getString(2));
	    			dto.setName(rs.getString(3));
	    			dto.setEmail(rs.getString(4));
	    			dto.setJoindate(rs.getString(5));
	    			
	    		}
	    		
	    	} catch(Exception e) {
	    		System.out.println("�Խù� ������ ����� ���� �߻�");
	    		e.printStackTrace();
	    	}
	    	
	    	return dto;
	    }
	
	
	
	public int deletePost(String id) {
		int result = 0;
		
		try {
			String query = "DELETE t_member WHERE id = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			result = psmt.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete �� ���� �߻�");
		}
		
		return result;
	}
	
	public boolean confirmPassword(String pwd, String id) {
		boolean isCorr = true;
		try {
			String query = "SELECT COUNT(*) FROM t_member WHERE pwd = ? and id = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, pwd);
			psmt.setString(2, id);
			rs = psmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1) == 0) {
				isCorr = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��й�ȣ Ȯ�ν� ���� �߻�");
		}
		return isCorr;
	}
	
	
	//proj01.war ���Ϸ� �������� �ؼ� ����
		
	
}
