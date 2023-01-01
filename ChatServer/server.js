import {WebSocketServer} from 'ws'
import {setupConsumer} from "./messageSubscriber.js";



const clients = new Map()
const wss = new WebSocketServer({port: 3000})

wss.on('connection', (ws) => {


    ws.on('open', () => {
        //Used to keep track of client connection
        ws.isAlive = true
        //Keeps track of who is on the other side of this websocket
        ws.client = null
    })

    ws.on('message', (data) => {
        const msg = JSON.parse(data.toString())
        console.log(msg)

        if (msg.hasOwnProperty('username')) {
            console.log(msg.username)
            ws.client = msg.username
            clients.set(msg.username, ws)
        }
        else {
            ws.close()
        }
    })

    ws.on('pong', () => {
        ws.isAlive = true
    })

    //On the close event, call the handle disconnect method
    ws.on('close', () => {
        handleDisconnect(ws)
    })


})

//Checks that connections are alive every so often
const heartbeatChecker = setInterval(() => {
    wss.clients.forEach((ws) => {
        if (ws.isAlive === false)
            return ws.close()
        ws.isAlive = false
        ws.ping()
    })
}, 30000)


function handleDisconnect(ws) {
    if (ws.client !== null) {
        clients.delete(ws.client)
        //Removes the WebSocket connection from the Map if necessary
    }
    wss.clients.delete(ws)
}

function handleMessage(msg) {
    if(msg.content) {
        console.log("[x] %s", msg.content.toString())
    }
}

setupConsumer(handleMessage)

console.log('Everythings ready to go')