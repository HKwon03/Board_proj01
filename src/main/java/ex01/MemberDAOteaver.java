package ex01;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;

public class MemberDAOteaver extends DBConnPool {
		//DB 를 Connection 객체를 상속 해서 쓰면 중복 코드를 방지할 수 있다. 
	
	public MemberDAOteaver() {     
		super(); 		//부모 클래스의 기본 생성자 호출  (con 객체가 생성 )
	}
		
	//List.jsp 출력을 위한 select : 출력 레코드가 많다. 
		//레코드 1개를 DTO 에 저장 
	    //DTO를 List : Vector (멀티쓰레드 지원) , ArrayList  (싱글쓰레드)
	
	public List<MemberDTOteaver> listMember () {
		
		//List<MemberDTO> 
		List<MemberDTOteaver> listMember = new ArrayList<MemberDTOteaver>(); 
		MemberDTOteaver dto = new MemberDTOteaver(); 
			
		String query = "select * from t_member" ;
		
		
		try { 

		psmt = con.prepareStatement(query); 
		rs = psmt.executeQuery(); 
			//rs 는 DB에서 Select 한 결과 값 (레코드셋)을 저장 
		
		//rs에 저장된 레코드 셋을 DTO에 저장후 List 에 ADD 해준다. 
		
		while (rs.next()) {
			//rs의 값을 변수에 담은후 DTO에 추가 
			MemberDTOteaver dto2 = new MemberDTOteaver();
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd"); 
			String name = rs.getString(3); 
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate"); 
			
			//DTO에 Setter 주입 
			dto2.setId(id);
			dto2.setPwd(pwd);
			dto2.setName(name);
			dto2.setEmail(email);
			dto2.setJoinDate(joinDate);
			
			System.out.println("ID 출력 : " + id);
			
			listMember.add(dto2); 			
		}
				
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Member  테이블에서 Select 중 오류 발생 ");
		}
		
		return listMember; 
	}
	
	//Insert.jsp db에 insert
	// 매개 변수로 dto 를 받는다. 
	
	public int addMember (MemberDTOteaver dto) {
		
		int result = 0 ;    //Insert  성공 여부를 확인 하는 변수 
		
		String query = "insert into t_member" ; 
				query += " (id, pwd, name, email)" ; 
				query += " values (? , ? , ? , ?)" ; 
				
				System.out.println( "Insert :" + query );
				
		//DTO의 넘어오는 변수의 값들을 getter 로 호출해서 변수에 할당. 
		String id = dto.getId(); 
		String pwd = dto.getPwd();
		String name = dto.getName(); 
		String email = dto.getEmail();
					
		try {
			//psmt 객체 생성 
			psmt = con.prepareStatement(query); 
			//? 에 값을 할당 
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			psmt.setString(3, name);
			psmt.setString(4, email);
			
			//psmt 실행 
			result = psmt.executeUpdate();    //inset, update, delete 일경우 
			
				//result : 1 <== Insert 성공
				//result : 0 <== Insert 실패
			
			System.out.println("Insert  성공 ");
					
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert 시 예외가 발생되었습니다. ");
			
		}	
		
		return result; 
		
	}
	
	
	//delet.jsp db에서 delete
		//매개변수로 Primary Key 컬럼의 변수값을 받아서 처리 
	public int delMember (String id) {
		
		int result = 0;
		
		String query ="delete t_member where id = ? "; 
		
		try {
			psmt = con.prepareStatement(query); 
			psmt.setString(1, id);
			result = psmt.executeUpdate(); 			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 삭제시 예외 발생 ");
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	//proj01.war 파일로 내보내기 해서 제출
		// p.jangwoo@gmail, 팀장님에게도 제출 

}
