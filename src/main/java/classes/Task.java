package classes;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * 
 * @author Txuso
 *	The class related to the tasks
 * @author Mario Measic-Gavran
 *  Modified 18.11.2015 - Mapping
 */

@Table(keyspace = "moppa", name = "tasks",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class Task {
  
  @PartitionKey
  public UUID taskid;

  private int problem;

  private String result;

  private String state;

  private String username;
  
  public Task() {
    
  }
  
  public Task(final UUID uuid) {
    this.taskid = uuid;
  }
  
  public Task(final UUID uuid, final String newCreatorUsername, final int newTaskValue,
  final String newTaskResult, final String newTaskState
  ) {
    super();
    this.taskid = uuid;
    this.problem = newTaskValue;
    this.result = newTaskResult;
    this.state = newTaskState;
    this.username = newCreatorUsername;
  }

  public UUID getTaskid() {
    return taskid;
  }

  public void setTaskid(UUID taskid) {
    this.taskid = taskid;
  }

  public int getProblem() {
    return problem;
  }

  public void setProblem(int problem) {
    this.problem = problem;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  
  
}