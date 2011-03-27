$(document).ready(function () {
	_configSortable();
	_configToggleActions();
});

function _configSortable() {
	$(".sortable").sortable({
		cursor: 'move',
		handle: '.handle',
		update : function () { 
		      var data = new Object();
		      data.projectId = $('#projectId').val();
		      data.order = $(".sortable").sortable('toArray');
		      
		      $.post(contextPath + '/secure/project/sort/ajax', data, function(result) {
		    	  // TODO display info on success
		      });
		} 
	});
	$(".sortable").disableSelection();
	$(".sortable .handle").addClass('movable');
}

function _configToggleActions() {
	$(".show-action-link").toggle(function() {
		$(this).parent().next().slideDown('fast');
	}, function() {
		$(this).parent().next().slideUp('fast');
	});	
}

function deleteProject(id) {
	var answer = confirm("Confirmation de suppression ?");
	if (answer) {
		window.location = contextPath + "/secure/project/delete/" + id;
	}
}

function updateProject(id) {
	window.location = contextPath + "/secure/project/update/" + id;
}

function deleteStory(id) {
	var answer = confirm("Confirmation de suppression ?");
	if (answer) {
		window.location = contextPath + "/secure/story/delete/" + id;
	}
}

function updateStory(id) {
	window.location = contextPath + "/secure/story/update/" + id;
}

function completeStory(id) {
	window.location = contextPath + "/secure/story/complete/" + id;
}

function startStory(id) {
	window.location = contextPath + "/secure/story/start/" + id;
}	
