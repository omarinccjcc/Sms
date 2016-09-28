<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<jsp:useBean id="now" class="java.util.Date" scope="request" />

<!DOCTYPE html>   
<head>
    <meta charset="utf-8">
    <link href="<%=request.getContextPath()%>/assets/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/assets/libs/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/libs/jquery/jquery-2.1.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/libs/bootstrap/js/bootstrap.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Universidad Peruana Unión</title>
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/icons/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">	
</head>

<body class="login" >
    <div class="container">
        <div class="row main">
            <div class="panel-heading">
                <div class="panel-title text-center">
                    <h1 class="title">Sistema de Mensajería Masiva</h1>
                    <hr />
                </div>
            </div> 
            <div class="main-login main-center">
                <form class="form-horizontal" id="form-login" action="<%=request.getContextPath()%>/j_spring_security_check" method="post" AUTOCOMPLETE="off">
                    <div class="form-group">
                        <label for="username" class="cols-sm-2 control-label">Usuario:</label>	
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span> 
                                <input type="text" name="j_username" id="username" class="form-control" placeholder="Usuario" autocomplete="off">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="cols-sm-2 control-label">Contraseña:</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                <input type="password" name="j_password" id="password" class="form-control" placeholder="Contraseña" autocomplete="off">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" id="passwordPlaceholder" class="textInput" placeholder="Password" style="display: none;"/>
                        <input type="submit" name="submit" class="btn btn-success btn-lg btn-block login-button" value="Ingresar" />
                    </div>

                </form>
            </div>

        </div>
    </div>
</body>
</html>



