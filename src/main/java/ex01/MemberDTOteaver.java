package ex01;

import java.sql.Date;

public class MemberDTOteaver {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public MemberDTOteaver() {
		System.out.println("MemverDTO 가 객체 잘 생성 되었습니다.");
	}
	
	
	//Getter / Setter 생성 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	

}
