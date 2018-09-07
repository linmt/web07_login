package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	//保存
	public void save(User user) throws Exception;
	//查找
	public User findByUserName(String userName) throws Exception;
	//查找全部
	public List<User> findAll() throws Exception;
}
