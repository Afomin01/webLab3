$(function() {
    setClockHands();
    setInterval(setClockHands, 5000);
});

function setClockHands() {
    const date = new Date;

    const hourAngle = 30 * ((date.getHours() % 12) + (date.getMinutes() / 60));
    const minuteAngle = (60 * date.getMinutes() + date.getSeconds())/10;
    const secondAngle = 6 * date.getSeconds();

    $('#clock > #hourHand').attr('transform', `rotate(${hourAngle})`);
    $('#clock > #minuteHand').attr('transform', `rotate(${minuteAngle})`);
    $('#clock > #secondHand').attr('transform', `rotate(${secondAngle})`);
}