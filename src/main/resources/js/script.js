// draw a rectangle on canvas

const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');

function drawRectangleAtClick(event) {
    const rect = canvas.getBoundingClientRect();
    console.log("Canvas left top width height", rect);
    const x = event.clientX - rect.x;
    const y = event.clientY - rect.y;
    // ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = 'blue';
    ctx.fillRect( x, y, 100, 80);
    console.log("event.clientX", event.clientX);
    console.log("rect.left", rect.left);
    console.log("left offset",  event.clientX - rect.left);
    console.log("event.clientY", event.clientY);
    console.log("rect.top", rect.top);
    console.log("top offset", event.clientY - rect.top);
}


canvas.addEventListener('mousedown', drawRectangleAtClick);
window.addEventListener('resize', () => {
    rect = canvas.getBoundingClientRect();
});


// Clear canvas once clicked on clear
function clearCanvas(){
    ctx.clearRect(0,0, canvas.width, canvas.height);
}
