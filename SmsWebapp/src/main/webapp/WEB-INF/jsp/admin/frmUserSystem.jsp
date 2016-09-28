<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">

	<div class="panel panel-primary">
	    <div class="panel-heading">Registro de Usuario</div>
	    <div class="panel-body">
	        <form:form commandName="userSystem" action="saveUserSystem" method="POST">
	            <form:hidden path="id" id="id" />
	            <div class="form-group">
	                <label>Login</label>
	                <form:input path="login" id="login" cssClass="form-control" required="true"/>
	            </div>
	            <div class="form-group">
	                <label>Password</label>
	                <form:password path="password" id="password" cssClass="form-control" required="${required}"/>	                
	            </div>
	            <div class="form-group">
	                <label>Fecha Inicio</label>
	                <div class="input-group date datepicker" data-provide="datepicker">
	                    <form:input path="dateIniString" id="dateIniString" cssClass="form-control" />
	                    <div class="input-group-addon">
	                        <span class="glyphicon glyphicon-th"></span>
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">
	                <label>Fecha Fin</label>
	                <div class="input-group date datepicker" data-provide="datepicker">
	                    <form:input path="dateEndString" id="dateEndString" cssClass="form-control" />
	                    <div class="input-group-addon">
	                        <span class="glyphicon glyphicon-th"></span>
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">
	                <label>Nombres</label>
	                <form:input path="firstName" id="firstName" cssClass="form-control" required="true"/>
	            </div>
	            <div class="form-group">
	                <label>Apellido Paterno</label>
	                <form:input path="lastNameF" id="lastNameF" cssClass="form-control" />
	            </div>            
	            <div class="form-group">
	                <label>Apellido Materno</label>
	                <form:input path="lastNameM" id="lastNameM" cssClass="form-control" />
	            </div>            
	            <div class="form-group">                
	                <label>Departamento</label>
	                <form:select path="departamentId" id="departamentId" cssClass="form-control">
	                    <form:options items="${listDepartament}" itemValue="id" itemLabel="areaName"/>
	                </form:select>
	            </div>
	            <div class="form-group">                
	                <label>Estado</label>
	                <form:select path="statusUser" id="statusUser" cssClass="form-control" required="true">
	                    <form:options items="${listStatusUser}" itemValue="statusCode" itemLabel="statusCode"/>
	                </form:select>
	            </div>
				
				<c:forEach items="${listRole}" var="var">
		            <div class="form-group">
		                <label><c:out value="${var.roleName}" /></label>
						<input type="checkbox" id="roleId" name="roleId" value="${var.id}" ${var.check} Class="form-control"/>
		            </div>  
				</c:forEach>
	
	
	            <button type="submit" class="btn btn-success">Guardar</button>
	            <button type="button" class="btn btn-primary">Cancelar</button>
	
	        </form:form>
	    </div>
	</div>
	
	<script src="<%=request.getContextPath()%>/assets/js/frmAccessApp.js"></script>

</sec:authorize>
