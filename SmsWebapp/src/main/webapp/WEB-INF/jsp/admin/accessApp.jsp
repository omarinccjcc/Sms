<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">

	<div class="panel panel-primary">
	    <div class="panel-heading">Administración de Codigos de Acceso</div>
	    <div class="panel-body">
	        <a href="frmAccessApp" class="btn btn-primary">Nuevo</a>
	
	        <display:table name="${listAccessApp}" id="item" class="table table-striped">
	            <display:column title="IMEI" property="imei" />
	            <display:column title="Fecha Incio" property="dateIniString" />
	            <display:column title="Fecha Fin" property="dateEndString" />
	            <display:column title="Estado" property="statusAccessApp" />
	            <display:column title="OPC">
	                <a href="editAccessApp?id=${item.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>
	                <a href="deleteAccessApp?id=${item.id}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
	                </display:column>
	            </display:table>
	    </div>
	</div>
</sec:authorize>


