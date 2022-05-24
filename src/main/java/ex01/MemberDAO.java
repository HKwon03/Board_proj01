package ex01;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	//DB를 Connection 객체를 상속해서 쓰면 중복 코드를 방지할 수 있다.
	
	public MemberDAO () {
		super(); 			//DBConnPool객체의 기본 생성자 호출 , DBCP에서 con 객체 활성화
	}
	
	// List.jsp 출력을 위한 select
	public List<MemberDTO> selectListPage(Map<String, Object> map) {
						// 강사님 : listMember = new ArrayList<MemberDTO>();
		List<MemberDTO> board = new Vector<MemberDTO>();
		 String query = " "
                 + "SELECT * FROM t_member"
		 		 + " ORDER BY joinDate DESC ";
		 
		 // System.out.println(query);
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
				//rs는 DB에서 Select한 결과 값(레코드셋)을 저장
			
			//rs에 저장된 레코드셋을 DTO에 저장후 list에 add 해준다
			
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
					+ " id, pwd, name, email) "
					+ " VALUES ("
					+ " ?, ?, ?, ?)"; 
			
		psmt = con.prepareStatement(query);  //PareparedStatement 객체 생성 
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getPwd());
		psmt.setString(3, dto.getName());
		psmt.setString(4, dto.getEmail());
		
		result = psmt.executeUpdate();  //insert가 성공일때 result > 0   //DB 에 값을 저장 
				//result : 0 일때 <== Insert  실패, result : 1일때 insert 성공
		
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return result; 		// result : Insert 성공시 > 0 , 실패시 : 0 
	}
	
	
	
	// delete.jsp db에서 delete
		//매개변수로 Primary Key 컬럼의 변수값을 받아서 처리
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
			System.out.println("delete 시 예외 발생");
		}
		return result;
	}
	
	
	//proj01.war 파일로 내보내기 해서 제출
		
	
}
