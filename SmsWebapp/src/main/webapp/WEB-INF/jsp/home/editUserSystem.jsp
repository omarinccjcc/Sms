<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize ifAnyGranted="ROLE_SECRE">

	<div class="container-fluid">
	    <div class="panel panel-primary">
	        <div class="panel-heading">Editar Información</div>
	        <div class="panel-body">
	            <form:form commandName="userSystem" action="saveUserSystem" method="POST">
	                <form:hidden path="id" id="id" />
	                
	                <div class="form-group">
	                    <label>Nombre</label>
	                    <form:input path="firstName" id="firstName" cssClass="form-control" maxlength="30"/>
	                </div>
	                <div class="form-group">
	                    <label>Apellido Paterno</label>
	                    <form:input path="lastNameF" id="lastNameF" cssClass="form-control" maxlength="30"/>
	                </div>
	                <div class="form-group">
	                    <label>Apellido Materno</label>
	                    <form:input path="lastNameM" id="lastNameM" cssClass="form-control" maxlength="30"/>
	                </div>

	                <div class="form-group">
	                    <label>Clave</label>
	                    <form:password path="password" id="password" cssClass="form-control" maxlength="30"/>
	                </div>
	                <div class="form-group">
	                    <label>Repita Clave</label>
	                    <form:password path="passwordConfirmation" id="passwordConfirmation" cssClass="form-control" maxlength="30"/>
	                </div>

	                <button type="submit" class="btn btn-success">Guardar</button>
	            </form:form>
	        </div>
	    </div>
	</div>

</sec:authorize>
