<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">

	<div class="panel panel-primary">
	    <div class="panel-heading">Registro de Departamento</div>
	    <div class="panel-body">
	        <form:form commandName="departament" action="saveDepartament" method="POST">
	            <form:hidden path="id" id="id" />
	            <form:hidden path="dateCreatedString" id="dateCreatedString" />
	            <form:hidden path="createdBy" id="createdBy" />
	            
	            <div class="form-group">
	                <label>Nombre de departamento</label>
	                <form:input path="areaName" id="areaName" cssClass="form-control" required="true"/>
	            </div>
	            <div class="form-group">
	                <label>Descripción</label>
	                <form:input path="description" id="description" cssClass="form-control"/>
	            </div>
	            <div class="form-group">                
	                <label>Estado</label>
	                <form:select path="statusDepartament" id="statusDepartament" cssClass="form-control">
	                    <form:options items="${listStatusDepartament}" itemValue="statusCode" itemLabel="statusCode"/>
	                </form:select>
	            </div>
				
	            <button type="submit" class="btn btn-success">Guardar</button>
	            <button type="button" class="btn btn-primary">Cancelar</button>
	
	        </form:form>
	    </div>
	</div>
	
	<script src="<%=request.getContextPath()%>/assets/js/frmAccessApp.js"></script>

</sec:authorize>
