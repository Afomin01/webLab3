$(function() {
    setClockHands('client-date', new Date);
    setClockHands('server-date', new Date( Date.now() - 1000 * 60 * 4));
    setInterval(() => setClockHands('client-date', new Date), 5000);
    setInterval(() => setClockHands('server-date', new Date( Date.now() - 1000 * 60 * 4)), 5000);
});

function setClockHands(id, date) {
    const hourAngle = 30 * ((date.getHours() % 12) + (date.getMinutes() / 60));
    const minuteAngle = (60 * date.getMinutes() + date.getSeconds())/10;
    const secondAngle = 6 * date.getSeconds();

    $(`#${id} .hourHand`).attr('transform', `rotate(${hourAngle})`);
    $(`#${id} .minuteHand`).attr('transform', `rotate(${minuteAngle})`);
    $(`#${id} .secondHand`).attr('transform', `rotate(${secondAngle})`);
}