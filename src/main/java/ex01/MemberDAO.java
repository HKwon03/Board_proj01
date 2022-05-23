package ex01;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	
	public MemberDAO () {
		super(); 			//DBConnPool객체의 기본 생성자 호출 , DBCP에서 con 객체 활성화
	}
	
	// List.jsp 출력을 위한 select
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
                System.out.println("DB에서 가져온 값(id) : " + rs.getString(1));
                System.out.println("DB에서 가져온 값(name) : " + rs.getString(2));
         
                System.out.println("================================");
                
                System.out.println("DTO에서 가져온 값(id) : " + dto.getId());
                System.out.println("DTO에서 가져온 값(name) : " + dto.getName());
                */
				
				
				board.add(dto);
				// System.out.println(dto);
			}
			
		} catch(Exception e) {
			System.out.println("게시물 조회중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	
	// Insert.jsp db에 insert
	
	public int insertWrite (MemberDTO dto) {
		
		int result = 0 ; 
		try {
			String query = "INSERT INTO t_member ( "
					+ " id, pwd, name, email, joindate) "
					+ " VALUES ("
					+ " ?, ?, ?, ?, ?)"; 
			
		psmt = con.prepareStatement(query);  //PareparedStatement 객체 생성 
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPwd());
		psmt.setString(3, dto.getName());
		psmt.setString(4, dto.getEmail());
		psmt.setString(5, dto.getJoindate());
		
		result = psmt.executeUpdate();  //insert가 성공일때 result > 0   //DB 에 값을 저장 
				//result : 0 일때 <== Insert  실패, result : 1일때 insert 성공
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result; 		// result : Insert 성공시 > 0 , 실패시 : 0 
	}
	
	
	
	// delete.jsp db에서 delete
	
			
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
	    		System.out.println("게시물 상세정보 출력중 예외 발생");
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
			System.out.println("delete 시 예외 발생");
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
			System.out.println("비밀번호 확인시 예외 발생");
		}
		return isCorr;
	}
	
	
	//proj01.war 파일로 내보내기 해서 제출
		
	
}
