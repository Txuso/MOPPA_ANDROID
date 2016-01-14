package exceptions;

import javax.ws.rs.core.Response.Status;

/**
 * The user has introduced an invalid value. 
 */
public class InvalidData extends AbstractException {

  /**
   * @param message Message to show in the response. 
   */
  public InvalidData(final String message) {
      super(Status.NOT_ACCEPTABLE, message);
  }
}