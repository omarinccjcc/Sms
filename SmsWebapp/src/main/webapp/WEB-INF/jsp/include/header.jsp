<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="authentication" value="${sessionScope['SPRING_SECURITY_CONTEXT'].authentication}" />
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<c:set var="user" value="${authentication.details}" />

<script type="text/javascript">
    contextPath = "<%=request.getRequestURI()%>";
</script>

<nav class="navbar navbar-static-top menu-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-calendar"></span>&nbsp;Sistema de Mensajería Masiva</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i>
                            <span class="glyphicon glyphicon-user" src="..." width="25" height="25"></span>
                        </i>
                        ${user.fullName}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/home/editUserSystem" onclick="">Editar Usuario</a></li>
                        <li class="divider"></li>
                        <li><a href="<%=request.getContextPath()%>/logout">logout</a></li>
                    </ul>
                </li>

            </ul>

            <ul class="nav navbar-nav">
			
				<c:forEach items="${user.roles}" var="item">
					<c:if test="${item eq 'ROLE_SECRE'}">
						<li <c:if test="${menuHeader eq 'home'}"> class="active"</c:if>><a href="<%=request.getContextPath()%>/home/campaign.html">Inicio</a></li>
					</c:if>
					
					<c:if test="${item eq 'ROLE_ADMIN'}">

						<li
						
							<c:choose>
								<c:when test="${menuHeader eq 'maintain'}">
									class="dropdown active"
								</c:when>    
								<c:otherwise>
									class="dropdown"
								</c:otherwise>
							</c:choose>
						>
							  <a data-toggle="dropdown">Administrar
							  <span class="caret"></span></a>
							  <ul class="dropdown-menu">
						
										<li <c:if test="${menuHeader eq 'home'}"> class="active"</c:if>><a href="<%=request.getContextPath()%>/admin/accessApp.html">Acceso a App</a></li>
										<li <c:if test="${menuHeaderChild eq 'enterprice'}"> class="active"</c:if>><a href="<%=request.getContextPath() %>/admin/userSystem.html">Usuarios</a></li>
										<li <c:if test="${menuHeaderChild eq 'departament'}"> class="active"</c:if>><a href="<%=request.getContextPath() %>/admin/departament.html">Departamentos</a></li>
	
							  </ul>
						</li>				


					</c:if>
				</c:forEach>
					            
		
            </ul>
			
        </div>
    </div>
</nav>
