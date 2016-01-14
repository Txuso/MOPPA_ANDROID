package cassandradb;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import classes.MoppaUser;

/**
 * 
 * @author Mario Measic-Gavran
 * This class is used for creating a new User 
 * and inserting a record into CassandraDB.
 */

public class CassandraUserDAO implements UserDAO {
  
  Session session;
  MappingManager manager;
  
  public CassandraUserDAO(Session session, MappingManager manager) {
    this.session = session;
    this.manager = manager;
  }

	public final int insertUser(final String username, final String password) {
				
		try {
		  MoppaUser user = new MoppaUser(username, password);
			
			Mapper<MoppaUser> mapper = manager
			                           .mapper(MoppaUser.class);
			mapper.save(user);
		} catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);		
		  }
		
		return 1; //Check
	}

}
