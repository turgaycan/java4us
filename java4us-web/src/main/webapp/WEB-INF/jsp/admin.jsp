<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%-- 
    Document   : admin
    Created on : Jan 19, 2014, 1:34:41 AM
    Author     : turgay
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${adminForm.message} ->>>- 
        <br/>
        ${adminForm.admin.email}
        <br />
        <c:out value="${admin.admin.email}"></c:out>
         
         
             
    </body>
</html>
