package brian.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	public static String hashPassword(String password) {
	    // Generate a salt and hash the password
	    return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static boolean verifyPassword(String candidatePassword, String hashedPassword) {
	    // Check if the candidate password matches the hashed password
	    return BCrypt.checkpw(candidatePassword, hashedPassword);
	}
	
	public static boolean verifyLogin(String candidatePassword, String hashedPassword, String candidateUsername, String username) {
		return verifyPassword(candidatePassword, hashedPassword) && username.equals(candidateUsername);
	}
}
