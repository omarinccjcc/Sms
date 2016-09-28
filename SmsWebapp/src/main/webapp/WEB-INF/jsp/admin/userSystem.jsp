<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">

	<div class="panel panel-primary">
	    <div class="panel-heading">Administración de Usuario</div>
	    <div class="panel-body">
	        <a href="frmUserSystem" class="btn btn-primary">Nuevo</a>
	
	        <display:table name="${listUserSystem}" id="item" class="table table-striped">
	            <display:column title="login" property="login" />
	            <display:column title="Fecha Incio" property="dateIniString" />
	            <display:column title="Fecha Fin" property="dateEndString" />
	            <display:column title="Nombre y Apellidos" property="allName" />
	            <display:column title="Estado" property="statusUser" />
	            <display:column title="OPC">
	                <a href="editUserSystem?id=${item.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>
	                <a href="deleteUserSystem?id=${item.id}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
	                </display:column>
	            </display:table>
	    </div>
	</div>
</sec:authorize>
