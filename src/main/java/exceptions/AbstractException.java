package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

/**
 * Class necessary to create custom error responses. 
 * @author usuario
 *
 */
public abstract class AbstractException implements StatusType {

    /**
     * Family. 
     */
    private final Family family;
    
    /**
     * Status Code.
     */
    private final int statusCode;
    
    /**
     * Reason Phrase. 
     */
    private final String reasonPhrase;
  
    /**
     * Constructor.
     * @param family0 Family.
     * @param statusCode0 Status Code. 
     * @param reasonPhrase0 Reason Phrase. 
     */
    public AbstractException(final Family family0, final int statusCode0,
                              final String reasonPhrase0) {
        super();

        this.family = family0;
        this.statusCode = statusCode0;
        this.reasonPhrase = reasonPhrase0;
    }

    /**
     * Constructor.
     * @param status0 Status.
     * @param reasonPhrase0 Reason Phrase. 
     */
    protected AbstractException(final Status status0,
                                 final String reasonPhrase0) {
        this(status0.getFamily(), status0.getStatusCode(), reasonPhrase0);
    }

    /**
     * Get Family. 
     * @return Family.
     */
    public final Family getFamily() { 
      return family; 
    }

    /**
     * Get Reason Phrase. 
     * @return Reason Phrase. 
     */
    public final String getReasonPhrase() { 
      return reasonPhrase; 
    }

    /**
     * Get Status Code. 
     * @return Status Code. 
     */
    public final int getStatusCode() { 
      return statusCode; 
    }

    /**
     * Response Builder. 
     * @return Return the response builder. 
     */
    public final ResponseBuilder responseBuilder() { 
      return Response.status(this); 
    }

    /**
     * Build the response.
     * @return Return the builded response. 
     */
    public final Response build() { 
      return responseBuilder().build(); 
    }

    /**
     * Build the WebApplicationException.
     * @return The WebApplicationException. 
     */
    public final WebApplicationException except() {
        return new WebApplicationException(build());
    }
}