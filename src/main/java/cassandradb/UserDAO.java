package cassandradb;

/**
 * 
 * @author Mario Measic-Gavran
 * This interface defines methods for User.
 */

public interface UserDAO {
	
	int insertUser(String username, String password);
}
