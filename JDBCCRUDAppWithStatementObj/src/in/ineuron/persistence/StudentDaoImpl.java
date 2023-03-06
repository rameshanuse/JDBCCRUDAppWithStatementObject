package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		
		String sqlInsertQuery = String.format("insert into student(`name`,`age`,`address`)values('%s', %d, '%s')",
																		sname, sage, saddress );
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
				statement = connection.createStatement();
			
			if(statement != null) {
				int rowAffected = statement.executeUpdate(sqlInsertQuery);
				
				if(rowAffected == 1) {
					return "success";
				}	
			}			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlSelectQuery = String.format("select id, name, age, address from student where id=%d", sid);
		Student student = null;
		 
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
				statement = connection.createStatement();
			if(statement != null) {
				resultSet = statement.executeQuery(sqlSelectQuery);
			}
			
			if(resultSet != null) {
				if(resultSet.next()) {
					student = new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));
					 
					return student;
				}
			}		
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updadeStudent(Integer sid, String sname, Integer sage, String saddress) {
		//String sqlUpdatetQuery = "update student set name='"+sname+"', age="+sage+", address='"+saddress+"' where id= "+sid;
		String sqlUpdatetQuery = String.format("update student set name= '%s', age= %d, address='%s' where id=%d ", sname, sage, saddress, sid);

		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) 
				statement = connection.createStatement();
			if(statement != null) {
				int rowAffected = statement.executeUpdate(sqlUpdatetQuery);
				
				if(rowAffected == 1) {
					return "success";
				}	
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = String.format("delete from student where id = %d", sid);
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null)
				statement = connection.createStatement();
			if(statement != null) {
				int rowAffected = statement.executeUpdate(sqlDeleteQuery);
				
				if(rowAffected == 1) {
					return "success";
				} 
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

}
