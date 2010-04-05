function draw() {
	var canvas = find("#canvas2");
	if (canvas.getContext) {
		var ctx = canvas.getContext("2d");

		ctx.fillStyle = "rgb(200,0,0)";
		ctx.fillRect(0, 0, 55, 50);

		// Draws a filled rectangle
		ctx.fillStyle = "rgba(0, 0, 200, 0.5)";// Í¸Ã÷¶È
		ctx.fillRect(30, 30, 55, 50);

		// Draws a rectangular outline
		ctx.fillStyle = "rgba(300, 300, 300, 0.5)";
		ctx.strokeRect(60, 60, 55, 50);

		// Clears the specified area and makes it fully transparent
		//ctx.clearRect(350, 200, 55, 50);

		ctx.fillStyle = "rgb(200,0,0)";
		ctx.beginPath();
		ctx.moveTo(90, 30);
		ctx.lineTo(120, 0);
		ctx.lineTo(120, 60);
		ctx.fill();
	}
}

function drawImage() {
	var ctx = find('#canvas').getContext('2d');
	var img = new Image();
	img.src = 'images/backdrop.png';
	img.onload = function() {
		ctx.drawImage(img, 0, 0);
		ctx.beginPath();
		ctx.moveTo(30, 96);
		ctx.lineTo(70, 66);
		ctx.lineTo(103, 76);
		ctx.lineTo(170, 15);
		ctx.stroke();
	}
}