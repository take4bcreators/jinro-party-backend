'use strict';

// WebSocket の接続先となるパス
const WEB_SOCKET_END_POINT = '/api/ws'

let socket;

const sendMsgElem = document.querySelector('#send-msg');
sendMsgElem.value = `{
    "destinationType": "MonitorSite",
    "destinationDeviceId": "",
    "senderType": "Server",
    "senderDeviceId": "",
    "requestAction": "GameScreenChange",
    "actionParameter01": "NightPhase",
    "actionParameter02": "",
    "actionParameter03": ""
}`

function writeMessage(msg) {
    const receiveOutElem = document.querySelector('.receive-out');
    receiveOutElem.innerHTML = msg;
}

// 接続ボタン
document.querySelector('.connect-btn').addEventListener('click', () => {
    socket = new SockJS(WEB_SOCKET_END_POINT);
    
    // WebSocket で接続開始時の処理
    socket.onopen = (_event) => {
        console.log('debug: WebSocket接続確立');
    };

    // WebSocket でメッセージ受信時の処理
    socket.onmessage = (event) => {
        const message = event.data;
        console.log('debug: WebSocketメッセージ受信');
        console.log('debug: 受信内容...');
        console.log(message);
        writeMessage(message);
    };

    // WebSocket で接続終了時の処理
    socket.onclose = (_event) => {
        console.log('debug: WebSocket接続終了');
    };
});

document.querySelector('.send-btn').addEventListener('click', () => {
    const sendMsgElem = document.querySelector('#send-msg');
    const sendMessage = sendMsgElem.value;
    console.log('debug: WebSocketメッセージ送信');
    console.log('debug: 送信内容...');
    console.log(sendMessage);
    socket.send(sendMessage);
});



