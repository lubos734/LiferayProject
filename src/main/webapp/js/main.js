function createDirectoryStructure(divisionsArray, divisionIdKey, actionURL, containerId, contextPath) {
	var indexList, indexRow;

	for (indexList = 0; indexList < divisionsArray.length; indexList++) {
		var rowData = divisionsArray[indexList];
		var row = createRowDiv("row");
		var container = createRowDiv("divisionContainer container-fluid");
		container.attr('id', 'organization_structure_container');
		for (indexRow = 0; indexRow < rowData.length; indexRow++) {
			//create classes for division div
			var divisionItemClasses = "col-md-2 ";
			var divisionClasses = "division ";
			if (indexRow % 6 == 0) {
				divisionItemClasses += calculateOffsetClass(rowData.length);
			}
			if (rowData[indexRow].onPath){
				divisionClasses += "activeDivision ";
			}
			//create division div
			var divisionItem = $("<div>", {"class": divisionItemClasses});
			//create division div
			var division = $("<div>", {"class": divisionClasses});

			var divisionName = rowData[indexRow].name;

			// define which icon should be used for which division
			var icons = {
				'Service Management': 'wrench',
				'IT/IS Department': 'laptop',
				'POS Development': 'code',
				'EFT POS Development': 'code',
				'APP Development': 'keyboard',
				'Sales and Marketing': 'money',
				'Logistic': 'road',
				'Call center': 'phone',
				'Service': 'wrench',
				'Quality Control': 'ok',
				'Warehouse': 'archive',
				'Secretariat': 'paper-clip',
				'IT Management': 'laptop'
			}

			//insert division name into div
			if (divisionName.toLowerCase() === 'sonet') {
				division.append('<img src="' + contextPath + '/img/sonet.png" />');
				division.attr('class', division.attr('class') + ' divisionLogo ');
			}
			else {
				var icon = icons[divisionName] ? icons[divisionName] : 'question';
				division.append('<i class="icon-' + icon + ' divisionIcon"></i>');
				division.append(divisionName);
			}

			division.attr(divisionIdKey, rowData[indexRow].id);
			division.click(function(){
				window.location.href = actionURL + "&" + divisionIdKey + "=" + $(this).attr(divisionIdKey) + "#organization_structure_container";
			})
			divisionItem.append(division);
			//append division item to row
			row.append(divisionItem);
			//if row has 6 division or this is last cycle append row to container
			if (indexRow % 6 == 5 || indexRow == rowData.length-1) {
				container.append(row);
				row = createRowDiv("row");
			}
		}
		//append container with divisions to tree view
		//if not in row-fluid then all rows except first has left margin
		var rowFluid = createRowDiv("");
		rowFluid.append(container)
		$("#"+containerId).append(rowFluid);
	}
}
function calculateOffsetClass(numberOfItems) {
	//returns bootstrap offset class for first item, to center items in row
	//return empty string if offset should be 0
	var offsetSize = (12-numberOfItems*2)/2;
	if (offsetSize == 0) {
		return ""
	}
	else {
		return "col-md-offset-"+offsetSize+" ";
	}
}
function createRowDiv(classes) {
	return $("<div>", {"class": classes});
}


function OSFormatDate(str) {
	if (str == "") return str;
	var date = new Date(str);
	var monthP = date.getUTCMonth()+1;
	var month = monthP < 10 ? '0' + monthP : '' + monthP;
	var day = date.getUTCDate() < 10 ? '0' + date.getUTCDate() : '' + date.getUTCDate();
	var hours = date.getUTCHours() < 10 ? '0' + date.getUTCHours() : '' + date.getUTCHours();
	var minutes = date.getUTCMinutes() < 10 ? '0' + date.getUTCMinutes() : '' + date.getUTCMinutes();
	//return date.getUTCFullYear() + "." + month + "." + day ;
	return day + "." + month + "." + date.getUTCFullYear()
}
