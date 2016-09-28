frmDepartament<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_ADMIN">

	<div class="panel panel-primary">
	    <div class="panel-heading">Administración de departamentos</div>
	    <div class="panel-body">
	        <a href="frmDepartament" class="btn btn-primary">Nuevo</a>
	
	        <display:table name="${listDepartaments}" id="item" class="table table-striped">
	            <display:column title="Nombre departamento" property="areaName" />
	            <display:column title="Descripción" property="description" />
	            <display:column title="Estado" property="statusDepartament" />
	            <display:column title="OPC">
	                <a href="editDepartament?id=${item.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>
	                <a href="deleteDepartament?id=${item.id}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
	                </display:column>
	            </display:table>
	    </div>
	</div>
</sec:authorize>
