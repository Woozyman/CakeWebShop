/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import dataaccess.DB_local;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jens
 */
public class EmailValidateFilter implements Filter {
    
    private final DB_local db;
    private static final boolean debug = true;

    
    private FilterConfig filterConfig = null;
    
    public EmailValidateFilter() {
      this.db = new DB_local();  
    }    
    
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            HttpServletRequest httpreq = (HttpServletRequest)request;
            
            PrintWriter out = response.getWriter();
                {
            {
            try {
                String queryCheck = "SELECT count(*) FROM users WHERE email  = ?";
                PreparedStatement ps = DB_local.getConnection().prepareStatement(queryCheck);
                ps.setString(1, queryCheck);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    out.println("alert('email allready exist!');");
                }
                if (debug) {
                    log("EmailValidateFilter:doFilter()");
                }

                Throwable problem = null;
                try {
                    chain.doFilter(request, response);
                } catch (Throwable t) {
                    // If an exception is thrown somewhere down the filter chain,
                    // we still want to execute our after processing, and then
                    // rethrow the problem after that.
                    problem = t;
                }
                
                doAfterProcessing(request, response);
                
                // If there was a problem, we want to rethrow it if it is
                // a known type, otherwise log it.
                if (problem != null) {
                    if (problem instanceof ServletException) {
                        throw (ServletException) problem;
                    }
                    if (problem instanceof IOException) {
                        throw (IOException) problem;
                    }
                    sendProcessingError(problem, response);
                }
            } catch (SQLException ex) {
                        Logger.getLogger(EmailValidateFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        }
     }

    /**
     * Return the filter configuration object for this filter.
     */
     public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("EmailValidateFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("EmailValidateFilter()");
        }
        StringBuilder sb = new StringBuilder("EmailValidateFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
