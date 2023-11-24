package karthik;
import java.sql.*;


class Student{
	
	String name;
	int roll;
	
}


class Dao{
	
	Connection con = null;
	PreparedStatement pst= null;
	Statement  st = null;
	
	public void connect() {
		
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root" , "Corani@2001");
		
		}
		catch(Exception e) {
			System.out.print("exp-1");
		}
		
		
	}
	
	
	
	public Student getStudent(int roll) {
		
		try {
			
		
		
		Student s = new Student();
		s.roll = roll;
		
		st = con.createStatement();
		
		ResultSet rs = st.executeQuery("select name from student where roll =" + roll);
		
		rs.next();
		
		s.name = rs.getString(1);
		return s;
				
		}
		catch(Exception e) {
			
			System.out.print("ex 2 ");
		}
		
		return null;
	}
	
	public void removeStudent(int roll) {
		
		try {
			
			  st = con.createStatement();
			  
			  int count = pst.executeUpdate("delete from student where roll = " + roll);
			  System.out.println(count + "row/s deleted");
			
			
			
		}
		catch(Exception e) {
			
			System.out.print("exp4");
		}
		
	}
	
	public void addStudent(int roll,String name) {
		
		try {
			
			pst = con.prepareStatement("insert into student values (?,?)");
			
			pst.setInt(1,roll);
			pst.setString(2,name);
			
			int count = pst.executeUpdate();
			
			System.out.println(count + "row/s affected");
			
			
			
		}
		catch(Exception e) {
			
			System.out.print("ex-3");
		}
		
		
	}
	
	
	
}


public class Main {

	public static void main(String[] args) {
		 
		Dao d= new Dao();
		d.connect();
		Student s1 =  d.getStudent(1);
		System.out.println(s1.name);
		
		
		
		 Student s2 = new Student();
		 s2.name = "tar";
		 s2.roll = 4;
		 d.addStudent(s2.roll, s2.name);
		 
		 d.removeStudent(4);

	}

}
