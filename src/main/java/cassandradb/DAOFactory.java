package cassandradb;

/**
 * 
 * @author Mario Measic-Gavran
 * This abstract class defines methods that should
 * be implemented if extended. Also, it returns
 * CassandraDAOFactory object for communication
 * with CassandraDB. It is possible to add more 
 * data sources in the future.
 */

public abstract class DAOFactory {
  
  public DAOFactory() {
    
  }
	
	public abstract TaskDAO getTaskDAO();
	
	//public abstract UserDAO getUserDAO();

	public static DAOFactory getDAOFactory() {
		
		return new CassandraDAOFactory();
	}
	


}
