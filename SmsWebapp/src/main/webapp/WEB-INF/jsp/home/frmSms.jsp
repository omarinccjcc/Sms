<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize ifAnyGranted="ROLE_SECRE">

	<div class="container-fluid">
	    <div class="panel panel-primary">
	        <div class="panel-heading">Registro de Mensaje</div>
	        <div class="panel-body">
	            <form:form commandName="sms" action="saveSms" method="POST">
	                <form:hidden path="id" id="id" />

	                <form:hidden path="textA" id="textA" />
	                <form:hidden path="textB" id="textB" />
	                <form:hidden path="dateA" id="dateA" />
	                <form:hidden path="dateB" id="dateB" />
	                <form:hidden path="amountA" id="amountA" />
	                <form:hidden path="amountB" id="amountB" />

	                <div class="form-group">
	                    <label>Nombre</label>
	                    <form:input path="nameAll" id="nameAll" cssClass="form-control" required="true"/>
	                </div>
	<!--                 <div class="form-group"> -->
	<!--                     <label>Apellido Paterno</label> -->
	<%--                     <form:input path="lastNameF" id="lastNameF" cssClass="form-control" required="true"/> --%>
	<!--                 </div> -->
	<!--                 <div class="form-group"> -->
	<!--                     <label>Apellido Materno</label> -->
	<%--                     <form:input path="lastNameM" id="lastNameM" cssClass="form-control" required="true"/> --%>
	<!--                 </div> -->	
	                <div class="form-group">
	                    <label>Numero Celular</label>
	                    <form:input path="numPhone" id="numPhone" cssClass="form-control" required="true"/>
	                </div>
	                <div class="form-group">
	                    <label>Mensaje</label>
	                    <form:input path="message" id="message" cssClass="form-control" required="true"/>
	                </div>
		            <div class="form-group">                
		                <label>Estado</label>
		                <form:select path="statusSms" id="statusCampaign" cssClass="form-control">
		                    <form:options items="${listStatusSms}" itemValue="statusCode" itemLabel="statusCode"/>
		                </form:select>
		            </div>

	                <button type="submit" class="btn btn-success">Guardar</button>
	            </form:form>
	        </div>
	    </div>
	</div>

</sec:authorize>
	