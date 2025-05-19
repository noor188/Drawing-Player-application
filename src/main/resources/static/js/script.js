
const container = document.querySelector('.container');
const canvas = document.getElementById('myCanvas');
let shape = null;

canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;

let ctx = canvas.getContext('2d');

const pick_oval = () => {
    shape = "oval";
    console.log(shape);
}

const pick_rectangle = () => {
    shape = "rectangle";
    console.log(shape);
}

container.addEventListener("mousedown", (event) => {
    console.log("inside event");
    let mouse = {
        x: event.pageX - container.getBoundingClientRect().left,
        y: event.pageY - container.getBoundingClientRect().top
    }
    ctx.fillStyle = "#eee";
    ctx.beginPath();
    let radiusX = 50;
    let radiusY = 100;
    let startAngle = 0;
    let endAngle = 2 * Math.PI;
    if (shape == "oval"){
        ctx.fillStyle = "#FFC0CB";
        ctx.beginPath();
        ctx.ellipse(mouse.x, mouse.y, radiusX, radiusY, 0, startAngle, endAngle);
        ctx.fill();
    }
    else if (shape == "rectangle"){
        ctx.fillStyle = "#800080";
        ctx.beginPath();
        ctx.fillRect(mouse.x, mouse.y, 100, 200);
        ctx.fill();
    }
});

const clearCanvas = () =>{
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

