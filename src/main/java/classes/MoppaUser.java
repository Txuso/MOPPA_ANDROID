package classes;

import com.datastax.driver.mapping.annotations.Table;

	/**
	 * 
	 * @author Txuso
	 * This class is related to the MoppaUsers
	 */

@Table(keyspace = "moppa", name = "users",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class MoppaUser {
	
	/**
	 * Moppa user's username.
	 */
	private String username;
	
	/**
	 * Moppa user's password.
	 */
	private String password;
	
	/**
	 * Default constructor.
	 */
	public MoppaUser() {
	    
	}

	/**
	 * 
	 * @param newUsername the username that is going to be created
	 * @param newPassword the password of the username
	 */
	public MoppaUser(final String newUsername, final String newPassword) {
		super();
		this.username = newUsername;
		this.password = newPassword;
	}

	/**
	 * 
	 * @return it returns the username
	 */
	public final String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param user the username that is going to be changed
	 */
	public final void setUsername(final String user) {
		this.username = user;
	}
	
	/**
	 * 
	 * @return it returns the password of the chosen user
	 */
	public final String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param newPassword the password that is going to be changed
	 */
	public final void setPassword(final String newPassword) {
		this.password = newPassword;
	}
	
}
