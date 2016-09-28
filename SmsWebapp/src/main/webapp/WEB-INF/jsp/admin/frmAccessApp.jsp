<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
	
	<div class="panel panel-primary">
	    <div class="panel-heading">Registro de Codigos de Acceso</div>
	    <div class="panel-body">
	        <form:form commandName="accessApp" action="saveAccessApp" method="POST">
	            <form:hidden path="id" id="id" />
	
	            <div class="form-group">
	                <label>IMEI</label>
	                <form:input path="imei" id="imei" cssClass="form-control" required="true"/>
	            </div>
	
	            <div class="form-group">
	                <label>Fecha Inicio</label>
	                <div class="input-group date datepicker" data-provide="datepicker">
	                    <form:input path="dateIniString" id="dateIniString" cssClass="form-control" required="true"/>
	                    <div class="input-group-addon">
	                        <span class="glyphicon glyphicon-th"></span>
	                    </div>
	                </div>
	            </div>
	
	            <div class="form-group">
	                <label>Fecha Fin</label>
	                <div class="input-group date datepicker" data-provide="datepicker">
	                    <form:input path="dateEndString" id="dateEndString" cssClass="form-control" required="true"/>
	                    <div class="input-group-addon">
	                        <span class="glyphicon glyphicon-th"></span>
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">                
	                <label>Departamento</label>
	                <form:select path="departamentId" id="departamentId" cssClass="form-control">
	                    <form:options items="${listDepartament}" itemValue="id" itemLabel="areaName"/>
	                </form:select>
	            </div>	
	            <div class="form-group">                
	                <label>Estado</label>
	                <form:select path="statusAccessApp" id="statusAccessApp" cssClass="form-control">
	                    <form:options items="${listStatusAccessApp}" itemValue="statusCode" itemLabel="statusCode"/>
	                </form:select>
	            </div>
	
	            <button type="submit" class="btn btn-success">Guardar</button>
	            <button type="button" class="btn btn-primary">Cancelar</button>
	
	        </form:form>
	    </div>
	</div>
	
	<script src="<%=request.getContextPath()%>/assets/js/frmAccessApp.js"></script>
</sec:authorize>
	