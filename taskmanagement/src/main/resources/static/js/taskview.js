$(document).ready(function () {

	$.getJSON("projectlist", function (data) {
		$.each(data, function (key, project) {
			$('#projects').append($('<option></option>').attr('id', project.projectId).attr('value', project.projectName).text(project.projectName));
		})
	});

	$('#projects').change(function () {
		$('.projectdetails').replaceWith('<div class="projectdetails"></div>').html();
		$.getJSON("details/" + $(this).children(':selected').attr('id'), function (data) {
			$.each(data, function (key, value) {
				$('.projectdetails')
					.append('<h2>Project: ' + value.projectName).append('</h2>')
					.append('<br/>').html();
				$.each(value.tasks, function (index, task) {
					$('.projectdetails')
						.append('</br></br><label>Task Description: ' + task.desc + '</label><br/><label>Task Start Date: '
							+ task.startDate + '</label><br/><label>Task End Date: ' + task.endDate + '</label>')
						.html();
					$('.projectdetails')
						.append('<table class=\"assigned_emp ' + task.taskId + '\" style="width:50%"><th>MID</th>'
							+ '<th>Employee Name</th>').html()
					$.each(task.assignedEmployees, function (id, emp) {
						$('.' + task.taskId)
							.append('<tr><td>' + emp.employeeId + '</td><td>' + emp.employeeName + '</td></tr>').html();
					});
					$('.assigned_emp').append('</table>').html();
				});
			})
			$('.projectdetails br').first().remove();
			$('.projectdetails br').first().remove();
			$('.assigned_emp').next('h2').next('br').remove();
			$('.assigned_emp').next('h2').next('br').remove();
			$('.assigned_emp').next('h2').before('</br></br>').html();

		});
	});
});