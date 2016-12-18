<%-- 
    Document   : upload
    Created on : 11-Dec-2016, 20:26:19
    Author     : freyb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Picture Upload</title>
    </head>
    <body>
        <form method="post" action="${pageContext.servletContext.contextPath}/uploader" enctype="multipart/form-data">
            <input type ="file" name="file" value="Select Image ..." />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>
