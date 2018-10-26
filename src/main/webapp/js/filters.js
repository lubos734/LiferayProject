/*
 * Initialize filters button
 * Add span with icon to button and register on click function that will show/hide container with filters
 * buttonId - mandatory - points to button
 * containerId - mandatory - points to container with filters
 * show - optional - indicates default state, default false
*/ 
function sonetFilterInit(buttonId, containerId, show) {
	if (show === undefined) {
		show = false;
	}
	
	// add span with icon to button
	$('#'+buttonId).append($("<span>", {
		"class": "rotate"
	}));
	
	// initialize state of button, container and hidden
	if (show) {
		$('#'+containerId).show();
		$('#'+buttonId).children("span.rotate").addClass("rotate180Clock");
	} else {
		$('#'+containerId).hide();
		$('#'+buttonId).children("span.rotate").removeClass("rotate180Clock");
	}
	
	// initialize default filters
	$.each($("#"+containerId+" .dataTableFilter"), function(index, e) {
		var element = $(e);
		var val;
		// get value of input
		if (element.is(":checkbox")) { // checkbox
		    val = element.prop('checked').toString();
		} else { // other inputs
			val = element.val().toString();
		}
		// set value to attribute
		if (val != "") {
			element.attr("data-filter", val);
			element.attr("data-filter-default", val);
		}
	})
	
	// register simple on click on button
	$('#'+buttonId).click(function() {
		$('#'+containerId).slideToggle(400);
		$('#'+buttonId).children("span.rotate").toggleClass("rotate180Clock");
	})
}

/*
 * Filter button click
 * Find all inputs with dataTableFilter class in container and copy values into data-filter attribute
 * containerId - id of element that contains all inputs with filters
 * tableId - table Id
 */  
function sonetFilterBtn(containerId, tableId) {
	$.each($("#"+containerId+" .dataTableFilter"), function(index, e) {
		var element = $(e);
		var val;
		// get value of input
		if (element.is(":checkbox")) { // checkbox
		    val = element.prop('checked').toString();
		} else { // other inputs
			val = element.val().toString();
		}
		// set value to attribute
		if (val != "") {
			element.attr("data-filter", val);
		} else {
			element.removeAttr("data-filter");
		}
	})
	
	$('#'+tableId).DataTable().ajax.reload();
}

/*
 * Filter clear button click
 * Find all inputs with dataTableFilter class in container and remove data-filter attribute
 * containerId - id of element that contains all inputs with filters
 * tableId - table Id
 */  
function sonetFilterClearBtn(containerId, tableId) {
	$.each($("#"+containerId+" .dataTableFilter"), function(index, e) {
		var element = $(e);
		var defaultValue = element.attr("data-filter-default");
		if (element.is(":checkbox")) { // checkbox
			if (defaultValue === undefined) { // no default value
				element.attr("data-filter", false);
				element.prop('checked', false);
			} else { // has default value
				var boolean = defaultValue === "true";
				element.attr("data-filter", boolean);
				element.prop('checked', boolean);
			}
		} else { // other inputs
			if (defaultValue === undefined) { // no default value
				element.removeAttr("data-filter");
				element.val("");
			} else { // has default value
				element.attr("data-filter", defaultValue);
				element.val(defaultValue);
			}
		} 
		
	})
	
	$('#'+tableId).DataTable().ajax.reload();
}

/*
 * Collects all filter values from attributes and adds them to data object
 * Function is used to send data in DataTable request
 * containerId - id of element that contains all inputs with filters
 * data - data element from DataTable request to fill filter values
 */ 
function sonetFilterGetValuesTable(containerId, data) {
	$.each($("#"+containerId+" .dataTableFilter"), function(index, e) {
		var element = $(e);
		var attribute = element.attr("data-filter");
		if (attribute === undefined) {
			data[element.attr("name")] = "";
		} else {
			data[element.attr("name")] = attribute;
		}
	})
	return data;
}

/*
 * Collests all filter values from attributes and returns FormData object
 * Function is used to send filter values in export file request
 * containerId - id of element that contains all inputs with filters
 */ 
function sonetFilterGetValuesForm(containerId) {
	var formData = new FormData();
	$.each($("#"+containerId+" .dataTableFilter"), function(index, e) {
		var attribute = $(e).attr("data-filter");
		if (attribute === undefined) {
			formData.append($(e).attr("name"), "");
		} else {
			formData.append($(e).attr("name"), attribute);
		}
	})
	return formData;
}

/*
 * Show alert and set hide timer 
 * alertId - alert id
 * alertText - text that will be shown
 * scroll - optional true/false - if browser will scroll up if alert is not visible, default true
 * hideSeconds - number of second that will be set into timer to hide alert, default 10
 */
function sonetShowAlert(alertId, alertText, scroll, hideSeconds) {
	if (scroll === undefined) {
		scroll = true;
	}
	
	if (hideSeconds === undefined) {
		hideSeconds = 10;
	}
	
	var alert = $("#" + alertId);
	$("#" + alertId + "Text").text(alertText);
	alert.show();
	
	if (scroll) {
		// determine visible area and scroll if alert is not visible
	    var win = $(window);
	    var viewportTop = win.scrollTop();
	    var viewportBottom = win.scrollTop() + win.height();
	    var closest = alert.closest("div.portlet-content");
	    var closestTop = closest.offset().top;
	    var menuHeight = $("div.control-menu").outerHeight();
		
		// scroll top to see message
		if (viewportBottom < closestTop || viewportTop > closestTop) {
			$('html, body').animate({
				// 64 is height of top overlap menu in liferay
			    scrollTop: (closestTop - menuHeight)
			}, 400);
		}
	}
	
	sonetHideAlert(alert, hideSeconds);
}

/*
 * Initialize excel download button
 * parameters json:
 * url - resource url
 * buttonId - export button id
 * containerId - id of element that contains all inputs with filters
 * errorAlertId - alert id of error message
 * errorAlertText - default text that will be show, when no message or file is returned from server or in case of any other error
 */
function sonetDownloadExcelInit(parameters) {
	// check mandatory parameters
	if (parameters.url === undefined || 
			parameters.buttonId === undefined || 
			parameters.containerId === undefined ||
			parameters.errorId === undefined || 
			parameters.errorMessage === undefined) {
		console.log('Mandatory parameters missing - mandatory: url, buttonId, containerId, errorId, errorMessage');
		return;
	}
	
	$("#" + parameters.buttonId).on( "click", function (){
		sonetDownloadExcel(parameters);
	});
}

/*
 * Download excel from resource method
 * parameters described at sonetDownloadExcelInit
 */
function sonetDownloadExcel(parameters) {
	// create spinning icon
	var icon = $("<i>")
		.addClass("glyphicon glyphicon-cog rotateSpin")
		.css("margin-left", "5px");
	// create new request for file
	var xhr = new XMLHttpRequest();
	xhr.open('POST', parameters.url, true);
	xhr.responseType = 'blob';
	xhr.onloadstart = function(e) {
		// find button
		var button = $("#" + parameters.buttonId);
		// append icon
		button.append(icon);
		// unregister click event to prevent other call until call response arrives
		button.off("click");
	};
	xhr.onloadend = function(e) {
		// remove icon from button
	    icon.remove();
	    // reinit button to enable click event
	    sonetDownloadExcelInit(parameters);
	};
	xhr.onload = function(e) {
    	if (this.status == 200) { // response is ready and is success
	        var disposition = xhr.getResponseHeader('Content-Disposition');
	        var contentType = xhr.getResponseHeader('Content-Type');

	        if (contentType === 'application/vnd.ms-excel; charset=UTF-8') { // response with excel file
	        	if (disposition && disposition.indexOf('attachment') !== -1) { // download file if response contains attachment
		    		// get filename from disposition header
		    		var filename = "";
		            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
		            var matches = filenameRegex.exec(disposition);
		            if (matches != null && matches[1]) { 
		            	filename = matches[1].replace(/['"]/g, '');
		            
			            // create a element with link to a file
				    	var blob = new Blob([this.response], {type: 'application/vnd.ms-excel'});
				        var downloadUrl = URL.createObjectURL(blob);
				        var a = document.createElement("a");
				        a.href = downloadUrl;
				        a.download = filename;
				        // append element at the end of the body
				        document.body.appendChild(a);
				        // click element to trigger download in browser
				        a.click();
				        // remove created element
				        document.body.removeChild(a);
		            } else { // disposition header does not contains filename, should not happen
	    	    		sonetShowAlert(parameters.errorId, parameters.errorMessage);
		            }
		    	} else {
		    		// response does not have disposition header with attachment, should not happen
			    	sonetShowAlert(parameters.errorId, parameters.errorMessage);
		        }
	        } else if (contentType === 'application/json; charset=UTF-8') { // response with error msg
	        	var blob = new Blob([this.response], {type: 'application/json'});
	        	var reader = new FileReader();
	    	   	reader.onloadend = function(e) {
	    	   		var json = JSON.parse(e.srcElement.result);
	    	   		if (json.pageResourceMessageId !== undefined && json.pageResourceMessageText !== undefined) {
	    	   			sonetShowAlert(json.pageResourceMessageId, json.pageResourceMessageText);
	    	   		} else {
	    	   			sonetShowAlert(parameters.errorId, parameters.errorMessage);
	    	   		}
	    	   	}
	    	   	reader.readAsText(blob);
	        } else { // other response contenty types
	    		sonetShowAlert(parameters.errorId, parameters.errorMessage);
	        }
	    } else { // any other status code than 200 is considered error
    		sonetShowAlert(parameters.errorId, parameters.errorMessage);
	    }
	};
	xhr.onerror = function(e) {
		sonetShowAlert(parameters.errorId, parameters.errorMessage);
	};
	// send request
	xhr.send(sonetFilterGetValuesForm(parameters.containerId));
}

/* Init button to submit form with ajax
 * parameters json:
 * formId - mandatory - form id
 * url - mandatory - resource url
 * errorId - mandatory - id of error alert element
 * errorMessage - mandatory - text for error message in case of network or other failure
 * successFunction - optional - function that will be called to process response, json object will be passed as parameter
 * finallyFunction - optional - function that will be called at the end of processing (after success or error)
 */
function sonetSubmitFormAjaxInit(parameters) {
	// check mandatory parameters
	if (parameters.formId === undefined || 
			parameters.url === undefined || 
			parameters.buttonId === undefined || 
			parameters.errorId === undefined ||
			parameters.errorMessage === undefined) {
		console.log('Mandatory parameters missing - mandatory: formId, url, buttonId, errorId, errorMessage');
		return;
	}
	
	$("#" + parameters.buttonId).on( "click", function (){
		sonetSubmitFormAjax(parameters);
	});
}

/*
 * Submit form with ajax
 * parameters described at sonetSubmitFormAjaxInit
 */
function sonetSubmitFormAjax(parameters) {
	// create spinning icon
	var icon = $("<i>")
		.addClass("glyphicon glyphicon-cog rotateSpin")
		.css("margin-left", "5px");
	
	// get form data
	var formData = new FormData($("#"+parameters.formId).get(0));
	// create request
	var xhr = new XMLHttpRequest();
	xhr.open('POST', parameters.url, true);
	xhr.onloadstart = function(e) {
		// find button
		var button = $("#" + parameters.buttonId);
		// append icon
		button.append(icon);
		// unregister click event to prevent other call until call response arrives
		button.off("click");
	};
	// called at the end of request, after onload/onerror
	xhr.onloadend = function(e) {
		if (parameters.finallyFunction !== undefined) {
			parameters.finallyFunction();
		}
		
		// remove icon from button
	    icon.remove();
	    // reinit button to enable click event
	    sonetSubmitFormAjaxInit(parameters);
	};
	// called when response is received
	xhr.onload = function(e) {
    	if (this.status == 200) { // response is ready and is success
	        var contentType = xhr.getResponseHeader('Content-Type');
	        if (contentType === 'application/json; charset=UTF-8') { // response with error msg
    	   		var json = JSON.parse(this.response);
    	   		if (parameters.successFunction === undefined) { // success function not available - show message
	    	   		if (json.pageResourceMessageId !== undefined && json.pageResourceMessageText !== undefined) {
	    	   			sonetShowAlert(json.pageResourceMessageId, json.pageResourceMessageText);
	    	   		} else {
	    	   			sonetShowAlert(parameters.errorId, parameters.errorMessage);
	    	   		}
    	   		} else { // call success function
    	   			parameters.successFunction(json);
    	   		}
	        } else { // other response contenty types
	        	sonetShowAlert(parameters.errorId, parameters.errorMessage);
	        }
    	} else { // any other status code than 200 is considered error
    		sonetShowAlert(parameters.errorId, parameters.errorMessage);
    	}
	};
	// called if network error occures
	xhr.onerror = function(e) {
		sonetShowAlert(parameters.errorId, parameters.errorMessage);
	};
	// send request
	xhr.send(formData);
}
