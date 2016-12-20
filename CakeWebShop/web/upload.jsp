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
            <input type="text" name="itemName" value="Name" />
            <input type="text" name="itemDescription" value="Description" />
            <!--<input type="text" name="itemPicture" value="" /> -->
            <input type="text" name="itemPrice" value="Price" />
            <input type="submit" value="Upload" />
        </form>
    </body>
</html>