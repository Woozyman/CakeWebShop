/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author freyb
 */
@WebServlet(name = "UploadController", urlPatterns = {"/uploader"})
public class UploadController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println("Nothing Uploaded");
            return;
        }

        FileItemFactory itemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemFactory);

        try {
            Map<String, List<FileItem>> items = upload.parseParameterMap(request);
            Collection<List<FileItem>> fileList = items.values();
            List<FileItem> files = new ArrayList();

            for (List<FileItem> list : fileList) {
                files.add(list.get(0));
            }
            //Accepted File Types
            String[] fileTypes = new String[]{"image/jpeg", "image/jpg", "image/png"};

            for (FileItem item : files) {
                String contentType = item.getContentType();
                boolean saved = false;

                for (int i = 0; i < fileTypes.length; i++) {
                    if (contentType.equals(fileTypes[i])) {
                        File uploadDir = new File("/home/pi/ImageUpload"); //Alter to local path to test on localhost
                        File file = File.createTempFile("img", "."+contentType.substring(6), uploadDir);                            
                    
                        item.write(file);
                        out.println("File Saved");
                        saved = true;
                        break;
                    }
                }
                
                if (!saved) {
                    out.println("Only .png and .jpg files are supported\n You Selected: " + contentType);
                }

            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            out.println("upload failed: " + e.getMessage());
            return;
        } catch (Exception e) {
            out.println("Can't save file: " + e.getMessage());
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
