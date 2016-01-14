package moppaapis;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * 
 * Filter implemented to allow CORS. 
 *  *
 */
public class CrossDomainFilter implements ContainerResponseFilter {

  /**
   * Filter implementation. 
   * @param creq Request Context.
   * @param cres Response Context. 
   * @throws IOException IOException.
   */
  public final void filter(final ContainerRequestContext creq,
      final ContainerResponseContext cres) throws IOException {
    cres.getHeaders().add("Access-Control-Allow-Origin", "*");
    cres.getHeaders().add("Access-Control-Allow-Headers", "Origin, "
        + "X-Requested-With, Content-Type, Accept");
    cres.getHeaders().add("Access-Control-Allow-Credentials", "*");
    cres.getHeaders().add("Access-Control-Allow-Methods", "*");
    cres.getHeaders().add("Access-Control-Max-Age", "*");    
  }

  }