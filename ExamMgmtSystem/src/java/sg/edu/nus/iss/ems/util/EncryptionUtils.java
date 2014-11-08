package sg.edu.nus.iss.ems.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {
	private static final String SHA = "SHA";
	
	public static String encryt(String password, String salt) {
		try {
			MessageDigest msgDigest = MessageDigest.getInstance(SHA);
			msgDigest.update(salt.getBytes());
			msgDigest.update(password.getBytes());
			return new BigInteger(1, msgDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encryt(String password) {
		try {
			MessageDigest msgDigest = MessageDigest.getInstance(SHA);
			msgDigest.update(password.getBytes());
			return new BigInteger(1, msgDigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
        
        public static void main(String... args) {
            System.out.println(encryt("password", "admin"));
            System.out.println(encryt("password", "lecturer"));
            System.out.println(encryt("password", "student"));
        }

}
