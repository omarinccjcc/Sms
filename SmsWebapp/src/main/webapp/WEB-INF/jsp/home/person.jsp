<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<br />
<div class="container-fluid">
	<div class="row-fluid">

		<form:form modelAttribute="uploadItem" action="uploadFile" method="post" enctype="multipart/form-data">
			<h4>Cambiar Foto de Perfil</h4>
			<form:input path="fileData" cssClass="btn " type="file" accesskey="image/jpeg, image/png, image/gif"/>
			<input type="submit" class="btn btn-primary" value="Subir Archivo"/>
		</form:form>

		<a href="frmPerson" class="label label-info">Nueva Persona</a>

		<display:table name="${listPerson}" id="item" class="table">
			<%-- 	<display:column title="Codigo" property="id" /> --%>
			<display:column title="Nombres" property="firstName" />
			<display:column title="Ape Paterno" property="lastNameF" />
			<display:column title="Ape Materno" property="lastNameM" />
			<display:column title="Número Telefónico" property="numPhone" />
			<display:column title="message" property="message" />
			<display:column title="OPC">
				<a href="editPerson?id=${item.id}" class="label label-info">Modificar</a>
				<a href="deletePerson?id=${item.id}" class="label label-important">Eliminar</a>
			</display:column>

		</display:table>

	</div>
</div>