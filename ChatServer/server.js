import {WebSocketServer} from 'ws'
import {setupConsumer} from "./messageSubscriber.js";
import * as crypto from "crypto";



const clients = new Map()
const wss = new WebSocketServer({port: 8081})

const secretKey = 'SecretKeyThatShouldBeSetInEnvVariable'

const hash = crypto.createHash('sha256')


wss.on('connection', (ws) => {

    //Used to keep track of client connection
    ws.isAlive = true
    //Keeps track of who is on the other side of this websocket
    ws.client = null


    ws.on('message', (data) => {
        const msg = JSON.parse(data.toString())
        console.log(msg)

        if (msg.hasOwnProperty('username') && msg.hasOwnProperty('token')) {
            if(hash.update(msg.username + secretKey).digest('hex') === msg.token)
            {
                ws.client = msg.username
                clients.set(msg.username, ws)
            }
            else{ ws.close() }
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
        handleDisconnect(ws.client)
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

function handleDisconnect(client) {
    if (client !== null) {
        clients.delete(client)
        //Removes the WebSocket connection from the Map if necessary
    }
}

function handleMessage(msg) {
    const toSend = JSON.parse(msg.content.toString())
    console.log(toSend)
    if(toSend.hasOwnProperty('to') && toSend.hasOwnProperty('msgBody')) {
        const receiver = clients.get(toSend.to)
        if(receiver) {
            receiver.send(toSend.msgBody)
        }
    }
}

setupConsumer(handleMessage)

console.log('Everything is ready to go')