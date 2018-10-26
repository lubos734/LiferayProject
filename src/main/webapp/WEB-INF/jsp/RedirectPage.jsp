<%@include file="Init.jspf"%>

<div class="container-fluid">
	<div class="row">
		<div id="${ns}backToOverview" class="btn btn-default marginRight5" onclick="sonetRedirect('${redirectUrl}')" >
			<spring:message code='TABLE.BACK_TO_OVERVIEW' />
		</div>
	</div>
</div>