package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ShopItem;
import models.ShopItemMapper;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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

        //form fields
        String itemName = "";
        String itemDesc = "";
        String itemPrice = "";

        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Nothing Uploaded');");
            out.println("location='upload.jsp';");
            out.println("</script>");
            return;
        }

        FileItemFactory itemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemFactory);

        try {
            Map<String, List<FileItem>> items = upload.parseParameterMap(request);
            Collection<List<FileItem>> fileList = items.values();
            List<FileItem> files = new ArrayList();

            for (List<FileItem> list : fileList) {
                if (list.get(0).getFieldName().equals("file")) {
                    files.add(list.get(0));
                } else if (list.get(0).getFieldName().equals("itemName")) {
                    itemName = list.get(0).getString();
                } else if (list.get(0).getFieldName().equals("itemDesc")) {
                    itemDesc = list.get(0).getString();
                } else if (list.get(0).getFieldName().equals("itemPrice")) {
                    itemPrice = list.get(0).getString();
                }
            }
            //Accepted File Types
            String[] fileTypes = new String[]{"image/jpeg", "image/jpg", "image/png"};

            // Upload path // Change to local path of WebApp
            //String Dir = "/Users/Michael/Desktop/CPHBusiness/05 Webprogrammering ServerSide/NetBeans/CakeWebShop/CakeWebShop/web/images";
            String Dir = "local PATH TO /images folder in Project";
            boolean saved = false;

            for (FileItem item : files) {
                String contentType = item.getContentType();

                for (String fileType : fileTypes) {
                    if (contentType.equals(fileType)) {
                        File uploadDir = new File(Dir); //Alter to local path to test on localhost
                        // File file = File.createTempFile("img", "." + contentType.substring(6), uploadDir);         
                        File file = File.createTempFile("img", item.getName(), uploadDir);

                        item.write(file);
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('File Saved');");
                        out.println("location='upload.jsp';");
                        out.println("</script>");
                        saved = true;

                        ShopItem newItem = new ShopItem();
                        newItem.setItemName(itemName);
                        newItem.setItemDescription(itemDesc);
                        newItem.setItemPrice(Double.parseDouble(itemPrice));
                        newItem.setItemPicture("images/" + file.getName());

                        ShopItemMapper sim = new ShopItemMapper();
                        sim.addItem(newItem);

                        break;
                    }
                }

                if (!saved) {

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert(' Only .png and .jpg files are supported');");
                    out.println("location='upload.jsp';");
                    out.println("</script>");
                }

            }

        } catch (FileUploadException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert(' upload failed: ');");
            out.println("location='upload.jsp';");
            out.println("</script>");

        } catch (Exception e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Can't save file:');");
            out.println("location='upload.jsp';");
            out.println("</script>");
        }

        // response.sendRedirect("upload.jsp");
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
