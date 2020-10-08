const fixedDigits = 7;

$(function() {
    $('#graph').on('mousedown', (event) => {
        console.log("sdfsdgf")
        let svg = document.getElementById('graph');
        let pt = svg.createSVGPoint();
        pt.x = event.clientX;
        pt.y = event.clientY;
        pt = pt.matrixTransform(svg.getScreenCTM().inverse());
        let x = (pt.x - 175) / 35;
        let y = (pt.y - 175) / -35;

        $("#graph-x").val(x.toFixed(fixedDigits));
        $("#graph-y").val(y.toFixed(fixedDigits));
    });
});