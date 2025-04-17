// draw a rectangle on canvas

const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');

function drawRectangleAtClick(event) {
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    // ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = 'blue';
    ctx.fillRect( x, y, 100, 80);
    console.log("event.clientX", event.clientX);
    console.log("rect.left", rect.left);
    console.log("event.clientY", event.clientY);
    console.log("rect.top", rect.top);
}

canvas.addEventListener('mousedown', drawRectangleAtClick);


// Clear canvas once clicked on clear
function clearCanvas(){
    ctx.clearRect(0,0, canvas.width, canvas.height);
}
