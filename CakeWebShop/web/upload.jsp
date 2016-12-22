<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="header.jsp"></c:import>
        <form method="post" action="${pageContext.servletContext.contextPath}/uploader" enctype="multipart/form-data">
            <input type ="file" name="file" value="Select Image ..." />
            <input type="text" name="itemName" value="Name" />
            <input type="text" name="itemDesc" value="Description" />
            <input type="text" name="itemPrice" value="000.00" />
            <input type="submit" value="Upload" />
        </form>
<c:import url="footer.jsp"></c:import>