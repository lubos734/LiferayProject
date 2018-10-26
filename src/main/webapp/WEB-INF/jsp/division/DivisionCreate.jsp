<%@include file="../Init.jspf" %>

<%@page import="static cz.lubos.portlet.division.DivisionConstants.*" %>

<portlet:actionURL var="divisionCreateActionSave" name="<%= DIVISION_CREATE_ACTION_SAVE %>" />
 
<div class="container-fluid">
	<form:form id="${ns}divisionCreateForm" modelAttribute="<%= DIVISION_CREATE_FORM_CRATE %>" method="post">
	
		<form:hidden path="divisionId" id="${ns}divisionId" />
		
		<div class="row">
	   		<div class="col-md-6">
	   			<div class="row">
		   			<div class="col-md-8 form-group">
		   				<form:label path="name" for="${ns}name">
							<spring:message code="DIVISION_CREATE.FORM_NAME" />
						</form:label>
						<form:input path="name" id="${ns}name" class="form-control"/>
						<form:errors path="name" id="${ns}errorName" class="text-danger"/>
		   			</div> 
		   		</div>
		   		<div class="row">
		   			<div class="col-md-8 form-group">
		   				<form:label path="parentId" for="${ns}parentId">
							<spring:message code="DIVISION_CREATE.FORM_PARENT_DIVISION" />
						</form:label>
						<form:select path="parentId" id="${ns}parentId" class="form-control">
							<form:option value="${null}" label="" />
							<c:forEach items="${divisionCreateSelectDivision}" var="item">
								<form:option value="${item.id}">${item.name}</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="parentId" id="${ns}errorParentId" class="text-danger"/>
		   			</div> 
		   		</div>
	   		</div>
	   		<div class="col-md-6">
	   			<div class="row">
		   			<div class="col-md-8 form-group">
		   				<form:label path="abbreviation" for="${ns}name">
							<spring:message code="DIVISION_CREATE.FORM_ABBREVIATION" />
						</form:label>
						<form:input path="abbreviation" id="${ns}abbreviation" class="form-control"/>
						<form:errors path="abbreviation" id="${ns}errorAbbreviation" class="text-danger"/>
		   			</div> 
		   		</div>
	   			<div class="row">
		   			<div class="col-md-8 form-group">
		   				<fmt:formatDate value="${divisionCreateFormCrate.validDateFrom}" var="dcfmtValidDateFrom" pattern="<%= DATE_FORMAT_CONVERTER %>" />
		   				<form:label path="validDateFrom" for="${ns}validDateFrom">
							<spring:message code="DIVISION_CREATE.FORM_VALID_DATE_FROM" />
						</form:label>
						<form:input path="validDateFrom" id="${ns}validDateFrom" value="${dcfmtValidDateFrom}" class="form-control"/>
						<form:errors path="validDateFrom" id="${ns}errorValidDateFrom" class="text-danger"/>
		   			</div> 
		   		</div>
	   		</div>
		</div>
		<div class="row">
	   		<div class="col-md-10">
				<div class="pull-right btn btn-default marginRight5" onclick="sonetSubmitForm('${ns}divisionCreateForm','${divisionCreateActionSave}')" >
					<spring:message code="TABLE.SAVE" />
				</div>
			</div>
	   	</div>
	</form:form>
</div>


<script>
$(document).ready(function() {
	 
	$('#${ns}validDateFrom').datetimepicker({
		format : '<%= DATE_FORMAT_PICKER %>',
		timepicker: false,
		scrollInput: false,
		dayOfWeekStart: <spring:message code='COMMON.FIRST_DAY_OF_THE_WEEK' />
	})
	
})
</script>