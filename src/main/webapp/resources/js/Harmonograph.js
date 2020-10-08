$(function() {
    drawGraphOnCanvas('canvas');
});

function drawGraphOnCanvas(id) {
    var canvas = document.getElementById(id);
    var ctx = canvas.getContext('2d');
    var width = canvas.width;
    var height = canvas.height;

    var harm = new Harmonograph;
    var zoom = 80;

    var widthAdd = width / 2;
    var heightAdd = height / 2;

    var xLast = (harm.x(0) * zoom) + widthAdd;
    var yLast = (harm.y(0) * zoom) + heightAdd;

    var xCurrent = 0;
    var yCurrent = 0;

    for (let t = 1e-02; t < 255; t += 1e-02) {
        xCurrent = (harm.x(t) * zoom) + widthAdd;
        yCurrent = (harm.y(t) * zoom) + heightAdd;

        ctx.beginPath();
        ctx.moveTo(xLast, yLast);
        ctx.lineTo(xCurrent, yCurrent);

        ctx.strokeStyle = '#FF0000';
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