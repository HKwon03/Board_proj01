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
			System.out.println("게시물 조회중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	
	// Insert.jsp db에 insert
	
	
	// delete.jsp db에서 delete
	
	
	
	//proj01.war 파일로 내보내기 해서 제출
		
	
}
