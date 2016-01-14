package moppaapis;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cassandradb.CassandraDAOFactory;
import cassandradb.UserDAO;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author Txuso
 *
 */
@Path("/moppa/v1/user")
@Api(value = "/user", description = "I am an User!")
public class UserAPI {
		
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
    @POST
    @Path("/createUser")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response createUserInJSON(final String input) {
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
   	 	JsonObject object = jsonReader.readObject();
   	 	jsonReader.close();
   	 	
   	 	if (object.getString("userName").isEmpty()
   	 			|| object.getString("password").isEmpty()) {
            return Response.status(Status.NOT_FOUND)
                .entity("User has not been created. You have to provide data."
                + "Please contact the administrator.")
                .build();
   	 	}
   	 	CassandraDAOFactory factory = new CassandraDAOFactory();
   	 	
   	 	try {
   	 	  UserDAO user = factory.getUserDAO();
   	 	  int success = user.insertUser(object
   	 	                .getString("userName"), object
   	 	                .getString("password"));
   	 	  if (success != 1) {
   	 	    return Response.status(Status.NOT_FOUND)
              .entity("User has not been created. "
              + "Please contact the administrator.")
              .build();
   	 	  } else {
     	 	  return Response.status(C200)
              .entity("User " + object
              .getString("userName") + " has been created.")
              .build();
   	 	  }  
   	 	
   	 	} catch (Exception e) {
   	 	  
   	 	  
   	 	} finally {
   	 	  factory.closeConnection();
   	 	}
   	 	
      return Response.status(Status.NOT_FOUND)
          .entity(" User has not been created, contact the administrator.")
          .build();

    }    
}