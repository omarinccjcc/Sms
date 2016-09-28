<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UPeU</title>
        <link rel="icon" href="<%=request.getContextPath()%>/assets/img/icons/favicon.ico" type="image/x-icon" />
        <%@ include file = "/WEB-INF/jsp/include/include.jsp"%>
    </head>

    <body>
        <header id="site_head">
            <%@ include file="/WEB-INF/jsp/include/header.jsp"%>
        </header>
        <div class="container">

            <div class="containerBody">
                <decorator:body />
            </div>

            <footer>
                <%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
            </footer>
        </div>

    </body>

</html>
