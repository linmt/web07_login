package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import entity.User;
public class UserDAOImpl implements UserDAO {
	//保存
	public void save(User user) throws Exception{
		Connection conn = null;
		try {
			conn=DBUtil.getConnection();
			PreparedStatement stat=conn.prepareStatement(
				"INSERT INTO t_user (username,pwd,name,gender) VALUES(?,?,?,?)");
			stat.setString(1,user.getUserName());
			stat.setString(2,user.getPwd());
			stat.setString(3,user.getName());
			stat.setString(4,user.getGender());
			stat.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			DBUtil.close(conn);
		}
	}
	//查找
	public User findByUserName(String userName) throws Exception{
		System.out.print("进入findByUserName");
		User user = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stat=
				conn.prepareStatement("SELECT * FROM t_user WHERE username=?");
			stat.setString(1,userName);
			ResultSet rst = stat.executeQuery();
			if(rst.next()){
				user=new User();
				user.setId(rst.getInt("id"));
				user.setUserName(rst.getString("username"));
				user.setName(rst.getString("name"));
				user.setPwd(rst.getString("pwd"));
				user.setGender(rst.getString("gender"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			DBUtil.close(conn);
		}
		return user;
	}
	//查找全部
	public List<User> findAll() throws Exception{
		List<User> users=new ArrayList<User>();
		Connection conn = null;
		try {
			conn=DBUtil.getConnection();
			PreparedStatement stat=conn.prepareStatement(
				"SELECT * FROM t_user");
			ResultSet rst=stat.executeQuery();
			while(rst.next()){
				User user = new User();
				user.setId(rst.getInt("id"));
				user.setUserName(rst.getString("username"));
				user.setPwd(rst.getString("pwd"));
				user.setName(rst.getString("name"));
				user.setGender(rst.getString("gender"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
		return users;
	}
}
