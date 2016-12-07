<%@tag description="Base Template" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>


<%-- any content can be specified here e.g.: --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template Test</title>
        <link href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="container">
        <nav id="header" class="navbar navbar-default navbar-fixed-top">
            <jsp:invoke fragment="header"/>
        </nav>
        <div id="body">
            <jsp:doBody/>
        </div>
        <nav id="footer" class="navbar navbar-default navbar-fixed-bottom">
            <jsp:invoke fragment="footer"/>
        </nav>
    </body>
</html>