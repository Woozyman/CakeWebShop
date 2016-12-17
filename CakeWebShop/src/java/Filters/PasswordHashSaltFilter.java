/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jens
 */
@WebFilter(filterName = "HashSaltFilter", urlPatterns = {"/AccountController?action=register"})
public  class PasswordHashSaltFilter implements Filter {

    private static String toBase64(byte[] salt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private FilterConfig filterConfig;

    public PasswordHashSaltFilter() throws CannotPerformOperationException, InvalidHashException {
        this.b = verifyPassword("password", hashedPassword);
    }
     
    @Override
    public void init(final FilterConfig filterConfig)
{
    this.filterConfig = filterConfig;
}
    @Override
    public void destroy()
{
    filterConfig = null;
}  
       
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws java.io.IOException, javax.servlet.ServletException
    {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String password = (String) request.getParameter("Password");
        String salt =(String) request.getParameter("salt");
        try {
            createHash(password);
        } catch (CannotPerformOperationException ex) {
            Logger.getLogger(PasswordHashSaltFilter.class.getName()).log(Level.SEVERE, null, ex);
        }  
        try {
            verifyPassword(password, "Password");
        } catch (CannotPerformOperationException ex) {
            Logger.getLogger(PasswordHashSaltFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidHashException ex) {
            Logger.getLogger(PasswordHashSaltFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private byte[] fromBase64(String param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        @SuppressWarnings("serial")
        class InvalidHashException extends Exception {
        public InvalidHashException(String message) {
            super(message);
        }
        public InvalidHashException(String message, Throwable source) {
            super(message, source);
        }
    }

    @SuppressWarnings("serial")
        class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String message) {
            super(message);
        }
        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }
    final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // These constants may be changed without breaking existing hashes.
    final int SALT_BYTE_SIZE = 24;
    final int HASH_BYTE_SIZE = 18;
    final int PBKDF2_ITERATIONS = 64000;

    // These constants define the encoding and may not be changed.
    final int HASH_SECTIONS = 5;
    final int HASH_ALGORITHM_INDEX = 0;
    final int ITERATION_INDEX = 1;
    final int HASH_SIZE_INDEX = 2;
    final int SALT_INDEX = 3;
    final int PBKDF2_INDEX = 4;    
    
    final String createHash(String password)
        throws CannotPerformOperationException
    {
        return createHash(password.toCharArray());
    }

        final String createHash(char[] password)
        throws CannotPerformOperationException
    {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt;
        salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash;
        hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        int hashSize = hash.length;

        // format: algorithm:iterations:hashSize:salt:hash
        String parts = "sha1:" +
            PBKDF2_ITERATIONS +
            ":" + hashSize +
            ":" +
            toBase64(salt) +
            ":" +
            toBase64(hash);
        return parts;
    }
    public boolean verifyPassword(String password, String correctHash)
        throws CannotPerformOperationException, InvalidHashException
    {
        return verifyPassword(password.toCharArray(), correctHash);
    }

    public boolean verifyPassword(char[] password, String correctHash)
        throws CannotPerformOperationException, InvalidHashException
    {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");
        if (params.length != HASH_SECTIONS) {
            throw new InvalidHashException(
                "Fields are missing from the password hash."
            );
        }

        // Currently, Java only supports SHA1.
        if (!params[HASH_ALGORITHM_INDEX].equals("sha1")) {
            throw new CannotPerformOperationException(
                "Unsupported hash type."
            );
        }

        int iterations = 0;
        try {
            iterations = Integer.parseInt(params[ITERATION_INDEX]);
        } catch (NumberFormatException ex) {
            throw new InvalidHashException(
                "Could not parse the iteration count as an integer.",
                ex
            );
        }

        if (iterations < 1) {
            throw new InvalidHashException(
                "Invalid number of iterations. Must be >= 1."
            );
        }


        byte[] salt = null;
        try {
            salt = fromBase64(params[SALT_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new InvalidHashException(
                "Base64 decoding of salt failed.",
                ex
            );
        }

        byte[] hash = null;
        try {
            hash = fromBase64(params[PBKDF2_INDEX]);
        } catch (IllegalArgumentException ex) {
            throw new InvalidHashException(
                "Base64 decoding of pbkdf2 output failed.",
                ex
            );
        }


        int storedHashSize = 0;
        try {
            storedHashSize = Integer.parseInt(params[HASH_SIZE_INDEX]);
        } catch (NumberFormatException ex) {
            throw new InvalidHashException(
                "Could not parse the hash size as an integer.",
                ex
            );
        }

        if (storedHashSize != hash.length) {
            throw new InvalidHashException(
                "Hash length doesn't match stored hash length."
            );
        }

        // Compute the hash of the provided password, using the same salt, 
        {  // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }
    }
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    } 
        public String hashedPassword = createHash("Password");
        public boolean b;
    }
    


    
 