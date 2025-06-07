
const container = document.querySelector('.container');
const canvas = document.getElementById('myCanvas');
const play = document.getElementById('play-button');
let shape = null;
let shapesList = [];
const userID = play.getAttribute('data-user-id');

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
        addOval(mouse.x, mouse.y, radiusX, radiusY);
    }
    else if (shape == "rectangle"){
        ctx.fillStyle = "#800080";
        ctx.beginPath();
        ctx.fillRect(mouse.x, mouse.y, 100, 200);
        ctx.fill();
        addRectangle(mouse.x, mouse.y, 100, 200);
    }
});

const clearCanvas = () =>{
    shapesList = [];
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

const play_music = () => {
    console.log("userID", userID);
    console.log("width", canvas.width);
    sendShapesToBackend(shapesList);
}

function  addShape(shape){
    shapesList.push(shape);
}

function sendShapesToBackend(currentShapes){
    fetch('/user/playmusic', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            shapes: currentShapes,
            canvasWidth: canvas.width,
            userID: userID
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function addOval(x, y, width, height) {
    const oval = {
        type: 'OVAL',
        x: x,
        y: y,
        width: width,
        height: height
    };
    shapesList.push(oval);
}

function addRectangle(x, y, width, height) {
    const rectangle = {
        type: 'RECTANGLE',
        x: x,
        y: y,
        width: width,
        height: height
    };
    shapesList.push(rectangle);
}

play.addEventListener('click', play_music);

