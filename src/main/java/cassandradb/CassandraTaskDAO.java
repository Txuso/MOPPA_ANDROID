package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import classes.Task;

import java.util.UUID;

/**
 * 
 * @author Mario Measic-Gavran
 * This class is used for creating a new Task 
 * and inserting a record into CassandraDB.
 */

public class CassandraTaskDAO implements TaskDAO {
  
  Session session;
  MappingManager manager;
	
	public CassandraTaskDAO(Session session, MappingManager manager) {
	  this.session = session;
	  this.manager = manager;
	}

  public CassandraTaskDAO() {
    
  }

  public final UUID insertTask(final String username, final int problem) {
		
		UUID uuid = UUID.randomUUID(); //can we use it directly in method call?
		try {
			Task task = new Task(
			uuid, username, problem, "0", "Waiting");
			
			Mapper<Task> mapper = manager.mapper(Task.class);
			mapper.save(task);
		} catch (Exception e) {
			e.printStackTrace();
		  }
		return uuid; // Returns newly created TaskID
	}
	
	public final Result<Task> findTasksbyUsername(final String username) {
		
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = session.prepare("SELECT * "
          + "FROM tasks WHERE username = ? ALLOW FILTERING;");
			BoundStatement boundStmt = new BoundStatement(stmt);
			
			ResultSet results   = session.execute(boundStmt.bind(username));
			Mapper<Task> mapper = manager
			                      .mapper(Task.class);
			tasks = mapper.map(results);
		} catch (Exception e) {
	      //LoggingHandler.writeErrorToLog(e);
			}
		return tasks;
	}
	
	public final Result<Task> findTasksbyState(final String username, 
	                                           final String state) {
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = session.prepare("SELECT * "
          + "FROM tasks WHERE username = ? AND state = ? ALLOW FILTERING;");
			BoundStatement boundStmt = new BoundStatement(stmt);
			ResultSet results = session
			                    .execute(boundStmt.bind(username, state));
			Mapper<Task> mapper = manager.mapper(Task.class);
			tasks = mapper.map(results);
		} catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);
		  System.out.println(e.toString());
		  }
		return tasks;
	  }
	
	public final boolean updateTask(final UUID taskid,
	                            final String taskResult) {
	  String taskState = "Done";
	  try {
	    
	     Mapper<Task> mapper = manager.mapper(Task.class);
	     Task task = mapper.get(taskid);
	     
	     task.setResult(taskResult);
	     task.setState(taskState);
	     
	     mapper.save(task);
	    
	  } catch (Exception e) {
    e.printStackTrace();
    return false;
    }
	  return true;
	}
	
	public final Result<Task> checkIfTaskExists (final int taskValue) {
	  
    Result<Task> tasks = null;
	  try {
      PreparedStatement stmt = session.prepare("SELECT * "
          + "FROM tasks WHERE problem = ? ALLOW FILTERING;");
      BoundStatement boundStmt = new BoundStatement(stmt);
      ResultSet results = session
                          .execute(boundStmt.bind(taskValue));
      Mapper<Task> mapper = manager.mapper(Task.class);
      tasks = mapper.map(results);

    } catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);
      System.out.println(e.toString());
    }
    return tasks;
	  
	}
}
