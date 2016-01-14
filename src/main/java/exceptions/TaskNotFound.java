package exceptions;

import javax.ws.rs.core.Response.Status;

	/**
	 * Exception launched when the are not tasks found.
	 */
public class TaskNotFound extends AbstractException {

	/** 
	 * @param message The message that is displayed to the user 
	 */
  public TaskNotFound(final String message) {
      super(Status.NOT_FOUND, message);
  }
}