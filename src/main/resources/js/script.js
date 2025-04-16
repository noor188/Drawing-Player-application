// draw a rectangle on canvas

const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');

function drawRectangleAtClick(event) {
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    // ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = 'blue';
    ctx.fillRect( x - 50, y - 40, 100, 80);
    console.log("event.clientX", event.clientX);
    console.log("rect.left", rect.left);
}

canvas.addEventListener('mousedown', drawRectangleAtClick);
