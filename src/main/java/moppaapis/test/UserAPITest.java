package moppaapis.test;

import static org.junit.Assert.assertNotNull;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import moppaapis.UserAPI;

/**
 * The class <code>UserAPITest</code>
 * contains tests for the class <code>{@link UserAPI}</code>.
 *
 * @ generatedBy CodePro at 11/19/15 12:20 PM
 * @ author Txuso
 * @ version $Revision: 1.0 $
 */
public class UserAPITest {
  /**
   * Run the UserAPI() constructor test.
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testUserAPICreateUserAPI() {
    UserAPI result = new UserAPI();
    assertNotNull(result);
    // add additional test code here
  }

  /**
   * Run the Response createUserInJSON(String) method test.
   *
   * @throws Exception is not thrown because it gives the correct result
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateUserInJSONCorrectValues()
    throws Exception {
    UserAPI fixture = new UserAPI();
    String input = "{\"userName\":\"Rodolfo\", \"password\": \"213\"}";
    Response result = fixture.createUserInJSON(input);
    assertNotNull(result);
  }

  /**
   * Run the Response createUserInJSON(String) method test.
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateUserInJSONNullUsername() {
	  try {
	        String input = "{\"userName\":\"\", \"password\": \"123\"}";
	    	UserAPI fixture = new UserAPI();
	    	fixture.createUserInJSON(input);
	    } catch (WebApplicationException e) {
	    	assertNotNull(Response.Status.NOT_ACCEPTABLE.getStatusCode());
	    }
  }

  /**
   * Run the Response createUserInJSON(String) method test.
   *
   * @throws Exception It is launched to inform that the password is null
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateUserInJSONNullPassword() {
    try {
        String input = "{\"userName\":\"Rodolfo\", \"password\": \"\"}";
    	UserAPI fixture = new UserAPI();
    	fixture.createUserInJSON(input);
    } catch (WebApplicationException e) {
    	assertNotNull(Response.Status.NOT_ACCEPTABLE.getStatusCode());
    }
  }

  /**
   * Run the Response createUserInJSON(String) method test.
   *
   * @throws Exception is launched because the user already exists
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateUserInJSONUserAlreadyExists() {
    
    try {
    	UserAPI fixture = new UserAPI();
        String input = "{\"userName\":\"Txuso\", \"password\": \"213\"}";
    	fixture.createUserInJSON(input);
    } catch (WebApplicationException e) {
    	assertNotNull(Response.Status.NOT_ACCEPTABLE.getStatusCode());
    }
  }

  /**
   * Perform pre-test initialization.
   *
   * @throws Exception
   *         if the initialization fails for some reason
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Before
  public void setUp()
    throws Exception {
    // add additional set up code here
  }

  /**
   * Perform post-test clean-up.
   *
   * @throws Exception
   *         if the clean-up fails for some reason
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @After
  public void tearDown()
    throws Exception {
    // Add additional tear down code here
  }

  /**
   * Launch the test.
   *
   * @param args the command line arguments
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  public static void main(final String[] args) {
    new org.junit.runner.JUnitCore().run(UserAPITest.class);
  }
}
