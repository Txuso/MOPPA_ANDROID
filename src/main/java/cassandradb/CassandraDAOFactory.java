package cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

import cassandradb.CassandraTaskDAO;
import cassandradb.DAOFactory;

/**
 * 
 * @author Mario Measic-Gavran
 * This class serves as a provider for connection details
 * to CassandraDB and creating instances of DAO objects.
 */

public class CassandraDAOFactory extends DAOFactory {
  
  private String CLUSTERNAME = "moppa";
  private String USERNAME = "cassandra";
  private String PASSWORD = "cassandrapassword";
  private String CONTACTPOINT = "127.0.0.1";
  private int PORT = 9042;
  private Cluster CLUSTER;
  private Session SESSION;
  private MappingManager MANAGER;
  
  public CassandraDAOFactory() {
      this.CLUSTER = Cluster.builder().addContactPoint(CONTACTPOINT).withPort(PORT).withCredentials(USERNAME, PASSWORD).build();
      this.SESSION = CLUSTER.connect(CLUSTERNAME);
      this.MANAGER = new MappingManager(SESSION);
  }

  public Session getConnection() {
    return SESSION;
  }
  
  public Cluster getCluster() {
    return CLUSTER;
  }
  
  public MappingManager getMappingManager() {
    return MANAGER;
  }
  
  public void closeConnection() {
    if (!SESSION.isClosed()) {
      
      SESSION.close();
    }
    else {
      // Do nothing, needs review
    }
  }
  
  @Override
  public final CassandraTaskDAO getTaskDAO() {
    
    return new CassandraTaskDAO(SESSION, MANAGER);
  }
  
  public final CassandraUserDAO getUserDAO(){
    
    return new CassandraUserDAO(SESSION, MANAGER);
  }
}