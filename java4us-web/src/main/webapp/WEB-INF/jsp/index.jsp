<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
        <!-- head -->
        <%@include file="pagePartials/head.jsp" %>
        <!-- head -->
   </head>
    <body>
        <!-- staticnavmenu -->
        <%@include file="pagePartials/staticnavmenu.jsp" %> 
        <!-- staticnavmenu -->

        <!-- subnavmenu -->
        <%@include file="pagePartials/subnavmenu.jsp" %>
        <!-- subnavmenu -->

        <div class="main">
            <div class="main-inner">
                <div class="container">
                    <div class="row">
                        <!-- todaysstat -->
                        <%@include file="pagePartials/mainleftcontent.jsp" %>
                        <!-- todaysstat -->
                        
                        <!-- impshortcuts -->
                        <%@include file="pagePartials/mainrightcontent.jsp" %>
                        <!-- impshortcuts -->
                        
                    </div>
                    <!-- /row --> 
                </div>
                <!-- /container --> 
            </div>
            <!-- /main-inner --> 
        </div>
        <!-- /main -->
       <!-- footer -->
       <%@include file="pagePartials/footer.jsp" %>
       <!-- footer -->
    </body>
</html>
