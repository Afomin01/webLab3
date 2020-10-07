$(function() {
    setClockHands('client-date', new Date);
    setInterval(() => setClockHands('client-date', new Date), 5000);
    setServerDate();
});

function setClockHands(id, date) {
    const hourAngle = 30 * ((date.getHours() % 12) + (date.getMinutes() / 60));
    const minuteAngle = (60 * date.getMinutes() + date.getSeconds())/10;
    const secondAngle = 6 * date.getSeconds();

    $(`#${id} .hourHand`).attr('transform', `rotate(${hourAngle})`);
    $(`#${id} .minuteHand`).attr('transform', `rotate(${minuteAngle})`);
    $(`#${id} .secondHand`).attr('transform', `rotate(${secondAngle})`);

    let hours = date.getHours() > 9 ? date.getHours().toString() : "0"+ date.getHours().toString()
    let minutes = date.getMinutes() > 9 ? date.getMinutes().toString() : "0"+ date.getMinutes().toString();
    let seconds = date.getSeconds() > 9 ? date.getSeconds().toString() : "0"+ date.getSeconds().toString();
    let day = date.getDate() > 9 ? date.getDate().toString() : "0"+ date.getDate().toString();
    let month = (date.getMonth()+1) > 9 ? (date.getMonth()+1).toString() : "0"+ (date.getMonth()+1).toString();
    let year = date.getFullYear();

    let timeText = hours +":"+ minutes +":"+ seconds;
    let dateText = day+"."+month+"."+year;

    $(`#${id} .text-time`).text(timeText);
    $(`#${id} .text-date`).text(dateText);
}

var attempt = 0;
function setServerDate() {
    $.ajax({
        type:"GET",
        success: (output, status, xhr) => {
            let serverDate = new Date(xhr.getResponseHeader("Date"));

            if (isNaN(serverDate.getTime())) {
                noValidServerDate()
            } else {
                $('#server-date svg').removeClass('blur');
                setClockHands('server-date', serverDate);
                setInterval(() => {
                    serverDate.setSeconds(serverDate.getSeconds() + 5);
                    setClockHands('server-date', serverDate) }, 5000);
            }
        },
        error: (xhr, status, error) => {
            noValidServerDate()
        }
    })
}

function noValidServerDate() {
    $('#server-date svg').addClass('blur');
    $('#server-date .text-time').html('Server');
    $('#server-date .text-date').html('error');

    if (attempt < 3) {
        attempt++;
        setServerDate();
    }
}