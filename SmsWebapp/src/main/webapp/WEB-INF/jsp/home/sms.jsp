<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>

<c:set var="authentication"
	value="${sessionScope['SPRING_SECURITY_CONTEXT'].authentication}" />
<c:set var="user" value="${authentication.details}" />

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet"/>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script> 


<script type="text/javascript">
$(document).ready(function() {
    $('#itemSms').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
</script>
	
<sec:authorize ifAnyGranted="ROLE_SECRE">

	<ul class="nav nav-pills nav-justified">
		<li><a href="campaign.html">Paso 1</a></li>
		<li class="active"><a href="#">Paso 2</a></li>
	</ul>

	<div class="panel panel-default panel-primary">
		<div class="panel-heading">Importación de Datos</div>

		<div class="panel-body">
			<form:form modelAttribute="uploadItem" action="uploadFile" method="post" enctype="multipart/form-data">
				<div class="col-md-4">
					<form:input path="fileData" cssClass="btn" type="file" accesskey="image/jpeg, image/png, image/gif" />
				</div>
				<div class="col-md-8">
					<input type="submit" class="btn btn-primary" value="Subir Archivo" />
					&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath()%>/files/Template.xlsx">Descargar Plantilla</a>					
				</div>
			</form:form>
		</div>

		<div class="panel-body">
			<form:form modelAttribute="campaign" action="updateCampaign" method="post">
				<div class="col-md-9">
					<textarea name="campaingMessage" rows="1" class="form-control">${user.campaingMessage}</textarea>
				</div>
				<div class="col-md-3">
					<input type="submit" class="btn btn-primary" value="Guargar" />
				</div>
			</form:form>
		</div>

		<div class="panel-body">

			<a href="frmSms" class="btn btn-success">Nueva Mensage</a>
			<div class="pull-right">
				<form name="form1" action="sms" method="GET">
					<c:if test="${user.status eq 'Espera'}">
						<a href="processDeleteSms" class="btn btn-danger">Eliminar Todo</a>
						<a href="processMessage" class="btn btn-warning">Procesar</a>
						<a href="processActiceSms" class="btn btn-primary">Activar Mensaje </a>
					</c:if>

					<c:if test="${user.status eq 'Activo'}">
						<a href="processDeleteSms" class="btn btn-danger">Eliminar Todo</a>
						<a href="processDesacticeSms?status=Activo" class="btn btn-primary">Espera Mensaje</a>
					</c:if>
					<c:if test="${user.status eq 'Procesado'}">
						<a href="processDesacticeSms?status=Procesado" class="btn btn-primary">Espera Mensaje</a>
					</c:if>
					
					<select name="status"
						onchange="javascript:document.form1.submit();">
						<c:forEach var="item" items="${listStatusSms}">
							<option value="${item.statusCode}"
								<c:if test="${user.status eq item.statusCode}"> selected="selected"</c:if>>${item.statusCode}</option>
						</c:forEach>
					</select>
				</form>
			</div>

			<display:table name="${listSms}" id="itemSms"
				class="table table-striped">
				<display:column title="Nombres y Apellidos" property="nameAll" />
				<display:column title="Núm. Telefónico" property="numPhone" />
				<display:column title="textA" property="textA" />
				<display:column title="textB" property="textB" />
				<display:column title="dateA" property="dateA" />
				<display:column title="dateB" property="dateB" />
				<display:column title="amountA" property="amountA" />
				<display:column title="amountB" property="amountB" />
				<display:column title="message" property="message" />
				<display:column title="Estado" property="statusSms" />
				<display:column title="OPC">
					<a href="editSms?id=${itemSms.id}" class="btn btn-sm btn-primary"><span
						class="glyphicon glyphicon-pencil"></span></a>
					<a href="deleteSms?id=${itemSms.id}" class="btn btn-sm btn-danger"><span
						class="glyphicon glyphicon-remove"></span></a>
				</display:column>
			</display:table>
		</div>
	</div>


</sec:authorize>