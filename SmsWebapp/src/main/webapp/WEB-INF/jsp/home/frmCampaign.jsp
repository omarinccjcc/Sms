<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize ifAnyGranted="ROLE_SECRE">

	<div class="container-fluid">
	    <div class="panel panel-primary">
	        <div class="panel-heading">Registro de Campaña</div>
	        <div class="panel-body">
	            <form:form commandName="campaign" action="saveCampaign" method="POST">
	                <form:hidden path="id" id="id" />
	                <form:hidden path="departamentId" id="departamentId" />
	                
	                <div class="form-group">
	                    <label>Campaña</label>
	                    <form:input path="campaingName" id="campaingName" cssClass="form-control" required="true"/>
	                </div>
	                <div class="form-group">
	                    <label>Descripción</label>
	                    <form:input path="description" id="description" cssClass="form-control" required="true"/>
	                </div>
	                <div class="form-group">
	                    <label>Mensaje</label>
	                    <form:input path="message" id="message" cssClass="form-control" required="true"/>
	                </div>
		            <div class="form-group">                
		                <label>Estado</label>
		                <form:select path="statusCampaign" id="statusCampaign" cssClass="form-control">
		                    <form:options items="${listStatusCampaign}" itemValue="statusCode" itemLabel="statusCode"/>
		                </form:select>
		            </div>
	
	                <button type="submit" class="btn btn-success">Guardar</button>
	            </form:form>
	        </div>
	    </div>
	</div>

</sec:authorize>
