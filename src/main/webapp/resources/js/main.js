const fixedDigits = 7;

$(function() {
    $('#graph').on('mousedown', (event) => {
        let r = parseFloat($("#RSelect option:selected").text());
        console.log(r);
        let svg = document.getElementById('graph');
        let pt = svg.createSVGPoint();
        pt.x = event.clientX;
        pt.y = event.clientY;
        pt = pt.matrixTransform(svg.getScreenCTM().inverse());
        let x = (140/r)*pt.x+175;
        let y = (140/r)*(-pt.y)+175;

        $("#graph-x").val(x.toFixed(fixedDigits));
        $("#graph-y").val(y.toFixed(fixedDigits));
    });
});