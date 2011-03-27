$(document).ready(function () {
	_configProjectLinks();
});

function _configProjectLinks() {
	$(".project").mouseover(function () {
		$(this).css('cursor', 'pointer');
	});
	
	$(".project").mouseout(function () {
		$(this).css('cursor', 'default');
	});
	
	$(".project").click(function () {
		window.location = contextPath + "/secure/project/show/" + this.id;
	});
}
