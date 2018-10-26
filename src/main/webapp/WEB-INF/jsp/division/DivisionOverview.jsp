<%@include file="../Init.jspf" %>

<%@page import="static cz.lubos.portlet.division.DivisionConstants.*" %>

<portlet:resourceURL var="divisionOverviewResourceTable" id="<%= DIVISION_OVERVIEW_RESOURCE_TABLE %>" />
<portlet:resourceURL var="divisionOverviewResourceControlDeactivate" id="<%= DIVISION_OVERVIEW_RESOURCE_CONTROL_DEACTIVATE %>" />
<portlet:resourceURL var="divisionOverviewResourceExportXls" id="<%= DIVISION_OVERVIEW_RESOURCE_EXPORT_XLS %>" />
<portlet:resourceURL var="divisionOverviewResourceRemove" id="<%= DIVISION_OVERVIEW_RESOURCE_REMOVE %>" />


<div class="container-fluid">
	<form:form id="${ns}divisionOverviewForm" modelAttribute="<%= DIVISION_OVERVIEW_FILTER %>" method="post">
	   	
	   	<div class="row">
   			<div class="col-md-2 col-md-offset-10 form-group">
				<div id="${ns}filterButton" class="btn btn-default filtersBtn">
					<spring:message code="TABLE.FILTERS" />
				</div>
			</div>
		</div>
	   	
	   	
	   	<div id="${ns}filter" style="display:none">
	   		
	   		<div class="row">
		   		<div class="col-md-6">
			   		<div class="row">
			   			<div class="col-md-8 form-group">
			   				<form:label path="id" for="${ns}id">
								<spring:message code="DIVISION_OVERVIEW.FILTER_NAME" />
							</form:label>
							<form:select path="id" id="${ns}id" class="form-control dataTableFilter">
								<form:option value="${null}" label="" />
								<c:forEach items="${divisionOverviewSelectDivision}" var="item">
									<form:option value="${item.id}">${item.name}</form:option>
								</c:forEach>
							</form:select>
							<form:errors path="id" id="${ns}errorId" class="text-danger"/>
			   			</div> 
			   		</div>
			   	</div>
			   	
			   	<div class="col-md-6">
			   		<div class="row">
			   			<div class="col-md-4 form-group">
			   				<fmt:formatDate value="${divisionOverviewFilter.validDateFrom}" var="fmtValidDateFrom" pattern="<%= DATE_FORMAT_CONVERTER %>" />
							<form:label path="validDateFrom" for="${ns}validDateFrom">
								<spring:message code="DIVISION_OVERVIEW.FILTER_VALID_DATE_FROM" />
							</form:label>
							<form:input path="validDateFrom" id="${ns}validDateFrom" value="${fmtValidDateFrom}" class="form-control dataTableFilter" />
			   			</div>
			   			
			   			<div class="col-md-4 form-group">
			   				<fmt:formatDate value="${divisionOverviewFilter.validDateTo}" var="fmtValidDateTo" pattern="<%= DATE_FORMAT_CONVERTER %>" />
							<form:label path="validDateTo" for="${ns}validDateTo">
								<spring:message code="DIVISION_OVERVIEW.FILTER_VALID_DATE_TO" />
							</form:label>
							<form:input path="validDateTo" id="${ns}validDateTo" value="${fmtValidDateTo}" class="form-control dataTableFilter" />
			   			</div>	
			   		</div>
			   		<div class="row">
			   			<div class="checkbox col-md-10 form-group">
			   				<form:label path="showInactive" for="${ns}showInactive">
			   					<form:checkbox path="showInactive" id="${ns}showInactive" class="dataTableFilter" />
								<spring:message code="DIVISION_OVERVIEW.FILTER_SHOW_INACTIVE" />
			   				</form:label>
			   			</div>
			   		</div>
			   	</div>
			</div>
			
			
		   	<div class="row">
				<div class="col-md-10">
			   		<div class="pull-right btn btn-default" onclick="sonetFilterClearBtn('${ns}divisionOverviewForm','${ns}divisionOverviewTable')" >
						<spring:message code="TABLE.FILTER_CLEAR" />
					</div>
			   		<div class="pull-right btn btn-default marginRight5" onclick="sonetFilterBtn('${ns}divisionOverviewForm','${ns}divisionOverviewTable')" >
						<spring:message code="TABLE.FILTER" />
					</div>
				</div>
		   	</div>
	   		
	   	</div>
	
	</form:form>
	
	<div class="row marginTop15">			
		<table id="${ns}divisionOverviewTable" class="table">
			<thead>
				<tr>
			        <th style="width: 20%"><spring:message code="DIVISION_OVERVIEW.TABLE_NAME" /></th>
			        <th style="width: 12%"><spring:message code="DIVISION_OVERVIEW.TABLE_ABBREVIATION" /></th>
			        <th style="width: 20%"><spring:message code="DIVISION_OVERVIEW.TABLE_PARENT_DIVISION" /></th>
					<th style="width: 15%"><spring:message code="DIVISION_OVERVIEW.TABLE_VALIDATION_FROM" /></th>
					<th style="width: 15%"><spring:message code="DIVISION_OVERVIEW.TABLE_VALIDATION_TO" /></th>
					<th style="width: 8%"><spring:message code="DIVISION_OVERVIEW.TABLE_IS_TAKEN" /></th>
					<th style="width: 5%"></th>
					<th style="width: 5%"></th>
				</tr>
			</thead>
		</table> 
	</div>
	
	<div class="row">
		<div class="col-md-12 marginTop5">
	   		<div id="${ns}export" class="pull-right btn btn-default" >
				<spring:message code="TABLE.EXPORT_XLS" />
			</div>
		</div>
   	</div>
</div>

<div id="${ns}removeDivisionDialog" style="display: none;">
	<div class="panel panel-default">
		<div class="panel-heading">
			<spring:message code='DIVISION_OVERVIEW.DIALOG_DEACTIVATE' />
		</div>
		<div class="panel-body">
			<form id="${ns}deactivateForm">
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="${ns}deactivateValidToDialog">
							<spring:message code="DIVISION_OVERVIEW.DIALOG_VALID_DATE_TO" />
						</label>
						<input id="${ns}deactivateValidToDialog" name="deactivateDateTo" class="form-control"/>
						<input type="hidden" name="deactivateId" id="${ns}deactivateId" />
					</div>
				</div>
			</form>
			<div class="row">
		   		<div class="col-md-12">
					<div class="pull-right btn btn-default marginRight5" id="${ns}deactivateDivision" >
						<spring:message code="TABLE.DEACTIVATE" />
					</div>
				</div>
		   	</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	 
	$('#${ns}validDateFrom').datetimepicker({
		format : '<%= DATE_FORMAT_PICKER %>',
		timepicker: false,
		scrollInput: false,
		dayOfWeekStart: <spring:message code='COMMON.FIRST_DAY_OF_THE_WEEK' />
	})
	 
	$('#${ns}validDateTo').datetimepicker({
		format : '<%= DATE_FORMAT_PICKER %>',
		timepicker: false,
		scrollInput: false,
		dayOfWeekStart: <spring:message code='COMMON.FIRST_DAY_OF_THE_WEEK' />
	})
	
	$('#${ns}deactivateValidToDialog').datetimepicker({
		format : '<%= DATE_FORMAT_PICKER %>',
		timepicker: false,
		scrollInput: false,
		dayOfWeekStart: <spring:message code='COMMON.FIRST_DAY_OF_THE_WEEK' />
	})
	
	$.ui.dialog.prototype._focusTabbable = $.noop;
	sonetInitDialog('${ns}removeDivisionDialog', {
		width:400
	});
	
	sonetFilterInit('${ns}filterButton', '${ns}filter', '${ns}divisionOverviewForm');
	sonetDownloadExcelInit({
		url: "${divisionOverviewResourceExportXls}",
		buttonId: "${ns}export",
		containerId: "${ns}divisionOverviewForm",
		errorId: "${ns}pageErrorId",
		errorMessage: "<spring:message code="GLOBAL.ERROR" />"
	});
	sonetSubmitFormAjaxInit({
		formId: '${ns}deactivateForm', 
		url: '${divisionOverviewResourceRemove}', 
		buttonId: "${ns}deactivateDivision",
		errorId: '${ns}pageErrorId',
		errorMessage: "<spring:message code="GLOBAL.ERROR" />",
		finallyFunction: function() {
			$('#${ns}deactivateId').val('');
			$('#${ns}deactivateValidToDialog').val('');
			$('#${ns}divisionOverviewTable').DataTable().ajax.reload();
			sonetCloseDialog('${ns}removeDivisionDialog');
		}
	})
	
	var table = $('#${ns}divisionOverviewTable').DataTable( {
	    "serverSide": true,
	    "bInfo": true,
	    "bFilter": false,
	    "bProcessing":true,
	    "pageLength": 50,
	    "lengthMenu": [ 25, 50, 100, 200 ],
	    "oLanguage" : <spring:message code="TABLE.LANGUAGE" />,
	    "ajax": {
		       	url: "<%= divisionOverviewResourceTable %>",
		       	cache: false,           
		        type: "POST",
		     	"data": function (data) {
		     		return sonetFilterGetValuesTable("${ns}divisionOverviewForm", data);
		     	}
		    },
	    "columnDefs": [
	    			{"targets": 0,
						"data": "name",
		  				"class": "verticalMiddle"
	  				},
	    			{"targets": 1,
						"data": "abbreviation",
		  				"class": "verticalMiddle"
	  				},
	  				{"targets": 2,
						"data": "parent_division_name",
		  				"class": "verticalMiddle"
					},
					{"targets": 3,
	 					"data": "valid_date_from",
	   	  				"class": "verticalMiddle"},
					{"targets": 4,
						"data": "valid_date_to",
	   	  				"class": "verticalMiddle"},
					{"targets": 5,
						"data": "is_taken",
	   	  				"class": "verticalMiddle"},
	 				{"targets": 6,
	   	  				"orderable" : false,
	 					"data": "id",
	   	  				"class": "verticalMiddle dt-body-center",
	   	  				"render": function ( data, type, row ) {
	   	  					if(row.valid_date_to == ""){
	   	  						url="${redirectUrl}" + '?' + '<%= OS_DIVISION_ID %>' + '=' + data;
								return '<div class="btn btn-default" onclick="sonetRedirect(\'' + url + '\')">'
								+ '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>' 
								+ '</div>'
	   	  					} 
   	  						return ''
    			  				}
			   	  	},
	 				{"targets": 7,
	   	  				"orderable" : false,
	 					"data": "id",
	   	  				"class" : "verticalMiddle dt-body-center",
	   	  				"render": function ( data, type, row ) {
	   	  					if(row.valid_date_to == "" && row.is_taken == "\u2717"){
								return '<div class="btn btn-default" onclick="divisionOv_remove(' + data + ')">'
								+ '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>' 
								+ '</div>'
	   	  					} 
   	  						return ''
		  				}
	   	  			}
				]
	});
})


function divisionOv_remove(id) {
	$.ajax({
    	type: 'GET',
    	url:  "<%= divisionOverviewResourceControlDeactivate %>",
	    cache: false,
	    dataType: "text",
	    data : {
	    	"divisionId" : id
	    },
	    success: function(data) {  
	    	if (data == "") {
		    	$("#${ns}deactivateId").val(id);
		    	sonetOpenDialog('${ns}removeDivisionDialog');	
	    	} else {
	    		DO_showErrorMessage(data);
	    	}
	    	
	    }
	});
	
}

function DO_showErrorMessage(data) {
	$("#${ns}pageErrorId").show();
	$("#${ns}pageErrorIdText").text(data);
	
	sonetHideAlert($("#${ns}pageErrorId"), 10);
}
</script>
