package moppaapis.test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.junit.*;

import exceptions.InvalidData;
import exceptions.TaskNotFound;
import moppaapis.MobileTaskAPI;
import static org.junit.Assert.*;

/**
 * The class <code>MobileTaskAPITest</code> 
 * contains tests for the class <code>{@link MobileTaskAPI}</code>.
 *
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class MobileTaskAPITest {
	/**
	 * Run the MobileTaskAPI() constructor test.
	 *
	 * @generatedBy CodePro at 12/4/15 11:17 AM
	 */
	@Test
	public final void testMobileTaskAPI1() {
		MobileTaskAPI result = new MobileTaskAPI();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Response getTask(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 12/4/15 11:17 AM
	 */
	@Test
	public final void testGetTaskCorrect() {
		MobileTaskAPI fixture = new MobileTaskAPI();
    String input = "\"phoneName\": \"\"Samsung S3\"}";
	    Response result = fixture.getTask(input);
	    assertNotNull(result);
	}

	/**
	 * Run the Response getTask(String) method test.
	 *
	 * @throws Exception is launched because the name is empty
	 *
	 * @generatedBy CodePro at 12/4/15 11:17 AM
	 */
	@Test
	public final void testGetTaskEmptyName()
		throws Exception {
		MobileTaskAPI fixture = new MobileTaskAPI();
    String input = "\"phoneName\": \"\"\"}";
		try {
			
			Response result = fixture.getTask(input);
			
		} catch (WebApplicationException e) {
      assertEquals(e.getResponse().getStatus(), Response.Status.NOT_ACCEPTABLE.getStatusCode());

		}
		
		
	}
	
	/**
	 * Run the Response getTask(String) method test.
	 *
	 * @throws Exception is launched because the name is empty
	 *
	 * @generatedBy CodePro at 12/4/15 11:17 AM
	 */
	@Test
	public final void testGetTaskNoTasksFound()
		throws Exception {
		MobileTaskAPI fixture = new MobileTaskAPI();
    String input = "\"phoneName\": \"\" Samsung S3\"}";
		try {
			
			Response result = fixture.getTask(input);
			assertNotNull(result);
			
		} catch (Exception e) {
			throw new TaskNotFound("There are no tasks assigned"
			    	+ " to username with that state").except();
		}
		
		
	}


	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 12/4/15 11:17 AM
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
	 * @generatedBy CodePro at 12/4/15 11:17 AM
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
	 * @generatedBy CodePro at 12/4/15 11:17 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MobileTaskAPITest.class);
	}
}