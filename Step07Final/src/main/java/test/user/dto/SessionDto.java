package test.user.dto;

public class SessionDto {
	private long num;
	private String userName;
	private String role;
	
	public SessionDto(){}
	
	public SessionDto(long num, String userName, String role) {
		super(); //부모클래스의 생성자
		this.num = num;
		this.userName = userName;
		this.role = role;
	}

	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
