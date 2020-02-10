//add drescriptions for readability

 $(document).ready(function ()
 {
	 setTimeout(function()
	 {
		 downloadImage();

	 },1000)
 });

 function downloadImage()
 {
	 html2canvas(document.body).then(canvas => {
		a = document.createElement('a');
		document.body.appendChild(a);
		a.download = "WhiteBoard.png";
		a.href =  canvas.toDataURL();
		a.click();
		document.getElementById("mdbutton").click();
	});



 }
