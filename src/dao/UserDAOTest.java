package dao;
import entity.User;
public class UserDAOTest {
	public static void main(String[] args) throws Exception {
		testFindByUsername();
	}
	public static void testFindByUsername() throws Exception {
		UserDAO dao = new UserDAOImpl();
		User user=dao.findByUserName("cat");
		System.out.println(user);
	}
}
