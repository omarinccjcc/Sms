<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" media="screen"
 href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">


			<form:form commandName="person" action="savePerson" method="POST">
				<form:hidden path="id" id="id" />
				
				<table>

					<tr>
						<td colspan="3"><legend>Datos del personal de UGEL : </legend></td>
					</tr>


					<tr>
						<td colspan="2"><button type="submit" class="btn btn-success">Guardar</button>
						<td />
					</tr>
				</table>
			</form:form>


		</div>
	</div>
</div>


    <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script>
	
	
    <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>
    <script type="text/javascript">
      $('#dateProcessDiv').datetimepicker({
        format: 'yyyy-MM-dd',
        language: 'en'
      });

      $('#dateResolutionsDiv').datetimepicker({
        format: 'yyyy-MM-dd',
        language: 'en'
      })
      $('#dateCaculationsDiv').datetimepicker({
        format: 'yyyy-MM-dd',
        language: 'en'
      })	  
    </script>