package moppaapis.test;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidData;
import exceptions.TaskNotFound;
import moppaapis.TaskAPI;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>TaskAPITest</code> 
 * contains tests for the class <code>{@link TaskAPI}</code>.
 *
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class TaskAPITest {
  /**
   * Run the TaskAPI() constructor test.
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testTaskAPICreation() {
    TaskAPI result = new TaskAPI();
    assertNotNull(result);
    // add additional test code here
  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception if the taskValue is wrong
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONCorrectInput()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "\"taskValue\": 10,\"userName\": "
    + "\"Mario\"}";

    Response result = fixture.createTaskInJSON(input);

    assertNotNull(result);
  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception launched because the taskValue is negative
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONWithNegativeValue()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "\"taskValue\": -10,\"creatorUsername\": "
    	    + "\"Mario\"}";
    try {
        Response result = fixture.createTaskInJSON(input);
        assertNotNull(result);

    } catch (Exception e) {
    	 throw new InvalidData("The value cannot be "
    		        + "negative or greater than 100").except();
    }

  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception is launched because the taskValue is > 100
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONWithValueHigherThan100()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "\"taskValue\": 1000,\"creatorUsername\": "
    	    + "\"Mario\"}";

    try {
        Response result = fixture.createTaskInJSON(input);
        assertNotNull(result);

    } catch (Exception e) {
    	 throw new InvalidData("The value cannot be "
    		        + "negative or greater than 100").except();
    }

  }

  /**
   * Run the Response findTaskByStateInJSON(String) method test.
   *
   * 
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByStateInJSONCorrect() {
    TaskAPI fixture = new TaskAPI();
    String input = "{\"userName\": \"Mario\", \"taskState\": \"Waiting\"}";
    Response result = fixture.findTaskByStateInJSON(input);
    assertNotNull(result);
  }

  /**
   * Run the Response findTaskByStateInJSON(String) method test.
   *
   * @throws Exception it is launched because there aren't tasks 
   * with the input state
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByStateInJSONNoTaskWithThatState()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "{\"userName\": \"Mario\", \"taskState\": \"Done\"}";
    try {
        Response result = fixture.findTaskByStateInJSON(input);
        assertNotNull(result);

    } catch (Exception e) {
    	throw new InvalidData("There are no tasks assigned"
    	+ " to username with that state").except();
    }

  }


  /**
   * Run the Response findTaskByUsernameInJSON(String) method test.
   *
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByUsernameInJSONCorrectInput() {
    TaskAPI fixture = new TaskAPI();
    String username = "{Rodolfo}";
    Response result = fixture.findTaskByUsernameInJSON(username);
    assertNotNull(result);
  }

  /**
   * Run the Response findTaskByUsernameInJSON(String) method test.
   *
   * @throws Exception it is launched because there are not results
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByUsernameInJSONNotResults()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String username = "{Rodolfo}";
    try {
    	Response result = fixture.findTaskByUsernameInJSON(username);
        assertNotNull(result);
    } catch (Exception e) {
    	throw new TaskNotFound("There are no tasks assigned "
        + "to this username.").except();
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
    new org.junit.runner.JUnitCore().run(TaskAPITest.class);
  }
}
