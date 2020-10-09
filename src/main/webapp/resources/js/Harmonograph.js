$(function() {
    drawGraphOnCanvas('canvas');
    setInterval(() => {
        $("#canvas").animate({opacity:0}, 1000, function () {
            drawGraphOnCanvas('canvas',true);
            $("#canvas").animate({opacity:1}, 1000);
        });
    }, 3000);
});

function drawGraphOnCanvas(id, needFade=false) {

    let canvas = document.getElementById(id);
    let ctx = canvas.getContext('2d');
    let width = canvas.width;
    let height = canvas.height;

    let harm = new Harmonograph;
    let zoom = 80;

    let widthAdd = width / 2;
    let heightAdd = height / 2;

    let xLast = (harm.x(0) * zoom) + widthAdd;
    let yLast = (harm.y(0) * zoom) + heightAdd;

    let xCurrent = 0;
    let yCurrent = 0;

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    let interval = 0.05;
    for (let t = interval; t < 255; t += interval) {
        xCurrent = (harm.x(t) * zoom) + widthAdd;
        yCurrent = (harm.y(t) * zoom) + heightAdd;

        ctx.beginPath();
        ctx.moveTo(xLast, yLast);
        ctx.lineTo(xCurrent, yCurrent);

        ctx.strokeStyle = '#d00000';
        ctx.stroke();

        xLast = xCurrent;
        yLast = yCurrent;
    }
}

class Pendulum {
    xDump = Math.random() * 1e-02;
    xPhase = Math.random() * Math.PI;
    xFreq = 2 + 1e-01 + (Math.random() * -0.09);
    xAmp = 1;

    yDump = Math.random() * 1e-02;
    yPhase = Math.random() * Math.PI;
    yFreq = 2 + 1e-01 + (Math.random() * -0.09);
    yAmp = 1;

    x(t) {
        return Math.exp(-this.xDump * t) * Math.cos(this.xFreq * t + this.xPhase);
    }

    y(t) {
        return Math.exp(-this.yDump * t) * Math.sin(this.yFreq * t + this.yPhase);
    }
}

class Harmonograph {
    pendulums = [new Pendulum, new Pendulum, new Pendulum];

    x(t) {
        let x = 0;
        for (let i = 0; i < this.pendulums.length; i++) {
            x += this.pendulums[i].x(t);
        }
        return x;
    }

    y(t) {
        let y = 0;
        for (let i = 0; i < this.pendulums.length; i++) {
            y += this.pendulums[i].y(t);
        }
        return y;
    }
}