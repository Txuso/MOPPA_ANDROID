package moppaapis;

import com.datastax.driver.mapping.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import cassandradb.CassandraDAOFactory;
import cassandradb.CassandraTaskDAO;
import classes.Task;
import redis.clients.jedis.Jedis;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;


/**
 * 
 * @author Mario Measic-Gavran
 *
 */
@Path("/moppa/v1/mobiletask")
@Api(value = "/mobiletask", description = "I am a Mobile Task!")
public class MobileTaskAPI {
  
  /**
   * code for OK api response.
   */
  private static final int C200 = 200;
  
  /**
   * code for WRONG api response.
   */
  private static final int C500 = 500;
    
  /**
   * 
   * @param input the information in order to create an user
   * @return it returns a message warning that 
   * the user has been created correctly
   * @throws MoppaException
   */
  @SuppressWarnings("resource")
	@POST
  @Path("/getTask")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Get task for Mobile", notes = "Anything Else?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
  public final Response getTask(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();
      
      if (object.getString("phoneName").isEmpty()) {
            return Response.status(Status.NOT_FOUND)
                .entity("You have to provide phone data."
                + "Please contact the administrator.")
                .build();
      }
      
      try {
        
        Jedis jedis = new Jedis();
        String taskRedis = jedis.lpop("tasks");
        
        JsonReader readTask = Json
                              .createReader(new StringReader(taskRedis));
        JsonObject taskRedisJSON = readTask.readObject();
        jsonReader.close();
        
        return Response.status(C200)
            .entity("Task " + taskRedisJSON
            .getString("taskId") 
            + " has been taken from the Redis queue"
            + " and sent for processing.")
            .build();
        
      } catch (Exception e) {
        
        
      }
      
      return Response.status(Status.NOT_FOUND)
          .entity("There are no tasks to process. "
          + "Please wait few seconds and try again.")
          .build();
  
    }   
    
  @POST
  @Path("/returnTask")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Return calculation for Mobile", notes = "Anything Else?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
  public final Response returnTask(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();
      
      if (object.getString("phoneName").isEmpty()
          || object.getString("taskID").isEmpty()
          || object.getString("taskResult").isEmpty()) {
            return Response.status(Status.NOT_FOUND)
                .entity("Some of the important data is missing."
                + "Please authenticate yourself, provide the calculation"
                + " and the taskID.")
                .build();
      }
      CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        UUID taskID = java.util.UUID.fromString(object.getString("taskID"));
        String taskResult = object.getString("taskResult");
        
        boolean success = task.updateTask(taskID, taskResult);
        
        if (success) {
          JsonObject value = Json.createObjectBuilder()
              .add("taskID has been updated - ", taskID.toString())
              .build();
          return Response.status(C200).entity(value).build();
        } else
          return Response.status(Status.NOT_ACCEPTABLE)
            .entity(" Task has not been updated, contact the administrator.")
            .build();
      } catch (Exception e) {
        //Log
    
      } finally {
       factory.closeConnection();
      }
      return Response.status(Status.NOT_FOUND)
          .entity(" Task has not been updated, contact the administrator.")
          .build();
    }
}