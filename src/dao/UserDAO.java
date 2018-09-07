package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	//����
	public void save(User user) throws Exception;
	//����
	public User findByUserName(String userName) throws Exception;
	//����ȫ��
	public List<User> findAll() throws Exception;
}
