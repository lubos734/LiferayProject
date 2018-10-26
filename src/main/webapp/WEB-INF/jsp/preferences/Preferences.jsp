<%@include file="../Init.jspf"%>

<portlet:actionURL var="savePreferences" name="sonetPreferencesSettingsSave" />

<div class="container-fluid">
	<form:form id="${ns}PreferencesForm"  modelAttribute="sonetPreferencesSettingsCrate" >
		<div class="row">
			<div class="form-group col-md-8">
				<form:label path="permissionGroups" for="${ns}permissionGroups">
					<spring:message code="PREFERENCES.GROUPS" />
				</form:label>
				<form:input path="permissionGroups" id="${ns}permissionGroups" class="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-8">
				<form:label path="permissionUsers" for="${ns}permissionUsers">
					<spring:message code="PREFERENCES.USERS" />
				</form:label>
				<form:input path="permissionUsers" id="${ns}permissionUsers" class="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
		   		<div class="pull-right btn btn-default" onclick="sonetSubmitForm('${ns}PreferencesForm','${savePreferences}')" >
					<spring:message code="PREFERENCES.SAVE" />
				</div>
			</div>
	   	</div>
	</form:form>
</div>


<script>
  	$(function() {
  	});
 </script>
