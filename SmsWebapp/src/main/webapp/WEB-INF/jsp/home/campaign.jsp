<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAnyGranted="ROLE_SECRE">

	<ul class="nav nav-pills nav-justified">
	  <li class="active"><a href="#">Paso 1</a></li>
	  <li><a href="#">Paso 2</a></li>
	</ul>
	
	<div class="panel panel-primary">
	    <div class="panel-heading">Administración de Campañas</div>
	    <div class="panel-body">
	        <a href="frmCampaign" class="btn btn-primary">Nuevo</a>
	
	        <display:table name="${listCampaign}" id="item" class="table table-striped">
	            <display:column title="Nombre de campaña" property="campaingName" />
	            <display:column title="Descripción" property="description" />
	            <display:column title="Mensaje" property="message" />
	            <display:column title="Estado" property="statusCampaign" />
	            <display:column title="OPC"> 
				
	                <a href="sms?id=${item.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>
	                <a href="editCampaign?id=${item.id}" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>
	                <a href="deleteCampaign?id=${item.id}" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-remove"></span></a>
	                </display:column>
	            </display:table>
	    </div>
	</div>
</sec:authorize>
