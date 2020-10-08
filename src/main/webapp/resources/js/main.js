const fixedDigits = 7;

$(function() {
        $("#j_idt19").click();
        $('#graph-click-form').on('mousedown', (event) => {
        let r = parseFloat($("#graph-form\\:RSelect").val());
        console.log(r);
        let svg = document.getElementById('graph');
        let pt = svg.createSVGPoint();
        pt.x = event.clientX;
        pt.y = event.clientY;
        pt = pt.matrixTransform(svg.getScreenCTM().inverse());
        let x = ((pt.x-175)*r)/140;
        let y = -((pt.y-175)*r)/140;

        $("#graph-x").val(x.toFixed(fixedDigits));
        $("#graph-y").val(y.toFixed(fixedDigits));
        $("#j_idt19").click();
    });
});
