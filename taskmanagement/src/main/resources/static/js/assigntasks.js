$(document).ready(function () {
	$.getJSON("projectlist", function (data) {
		$.each(data, function (key, project) {
			$('#projects').append($('<option></option>').attr('id', project.projectId).attr('value', project.projectName).text(project.projectName));
		})
	});

	$('#projects').change(function () {
		clearAlert();
		$('#employees').replaceWith('<select id="employees" multiple></select>').html();
		$('.addtaskresult').attr('hidden', 'hidden');
		$.getJSON($(this).children(':selected').attr('id') + "/employeelist", function (data) {
			$.each(data, function (key, employeelist) {
				$('#employees').append($('<option></option>').attr('id', employeelist.employeeId).attr('value', employeelist.employeeName).text(employeelist.employeeName + " " + employeelist.employeeId));
			})
		});
	});
});

function addTask() {
	if(validatePage()) {
		var data = {
			projectId: $('#projects').children(':selected').attr('id'),
			desc: $('#desc').val(),
			startDate: $('#startDate').val(),
			endDate: $('#endDate').val(),
			assignedEmployees: $('#employees option:selected').toArray().map(item => employeeList = { employeeId: item.id })
		}

		$.ajax({
			url: $('#projects').children(':selected').attr('id') + '/addTask',
			type: "POST",
			data: JSON.stringify(data),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success: function () {
				alertSuccess('Task added successfully.');
				$('#projects').prop('selectedIndex',0);
				$('#employees').replaceWith('<select id="employees" multiple></select>').html();
				$('#desc').val('');
				$('#startDate').val('');
				$('#endDate').val('');
			},
			error: function (error) {
				if (error.status == 406) {
					var errorText = '';
					$.each(error.responseJSON, function(index, data) {
						$('#' + data.errorFieldId).addClass('input-error');
						errorText = errorText + data.errorMessage + ',';
					});
					alertFailure(errorText);
				} else {
					alertFailure('Failed to add Task.');
				}
			}
		});
	}
}

function alertSuccess(message) {
	clearAlert();
	$.each(message.split(','), function(index, text) {
		if (text != '') {
			$('#alert').removeClass('alert-failure');
			$('#alert').addClass('alert-success');
			$('#alert-message').addClass('alert-message');
			$('#close').removeAttr('hidden');
			$('#alert-message').append('<li>' + text + '</li>');
		}
	});
}

function alertFailure(message) {
	clearAlert();
	$.each(message.split(','), function(index, text) {
			if (text != '') {
			$('#alert').removeClass('alert-success');
			$('#alert').addClass('alert-failure');
			$('#alert-message').addClass('alert-message');
			$('#close').removeAttr('hidden');
			$('#alert-message').append('<li>' + text + '</li>');
		}
	});
}

function clearAlert() {
	$('#alert').replaceWith('<span id="alert"><span id="alert-message"></span><a hidden href ="#" id ="close" onclick="clearAlert()" class="alert-close" title="close">×</a></span>').html();
}

function validatePage() {
	clearAlert();
	$('#projects').removeClass('input-error');
	$('#desc').removeClass('input-error');
	$('#startDate').removeClass('input-error');
	$('#endDate').removeClass('input-error');
	$('#employees').removeClass('input-error');
	var errormsg = '';
	if ($('#projects').children(':selected').index() == 0) {
		$('#projects').addClass('input-error');
		alertFailure('Please select at least one project.');
		return false;
	}

	if ($('#desc').val() == "") {
		$('#desc').addClass('input-error');
		errormsg = errormsg + 'Please enter valid Description.' + ',';
	}

	if ($('#startDate').val() == "" || !isValidDate($('#startDate').val())) {
		$('#startDate').addClass('input-error');
		errormsg = errormsg + 'Please enter valid Start Date of Task.  Date should be in format dd-mm-yyyy.' + ',';
	}

	if ($('#endDate').val() == "" || !isValidDate($('#endDate').val())) {
		$('#endDate').addClass('input-error');
		errormsg = errormsg + 'Please enter valid End Date of Task.  Date should be in format dd-mm-yyyy.' + ',';
	}	

	if ($('#employees').find('option:selected').val() == undefined) {
		$('#employees').addClass('input-error');
		errormsg = errormsg + 'Please select at least one Employee.' + ',';
	}
	
	if (errormsg != '') {
		alertFailure(errormsg);
		return false;
	} else {
		return true;
	}
}

function isValidDate(input) {
  var data = input.split('-');
  if (data.length != 3) {
	  return false;
  }
  var date = new Date(data[2] + '/' + data[1] + '/' + data[0]);
  if (date == 'Invalid Date') {
	  return false;
  } else {
  	return date.getFullYear() == data[2] && (date.getMonth() + 1) == data[1] && date.getDate() == Number(data[0]);
  }
}