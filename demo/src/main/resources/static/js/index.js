// Called when a specific Rover type button is clicked 
// to set the value of selected Rover type
function selectRoverType(roverType) {
	$('#roverType').val(roverType);
	$('#roverTypeForm').submit();
}

// Highlight the suitable selected Rover type button by changing its
// bootstrap class
function highlightBtnRoverType() {
	var roverType = $('#roverType').val();
	$('#'+roverType).removeClass("btn-secondary").addClass("btn-primary");
}

highlightBtnRoverType();