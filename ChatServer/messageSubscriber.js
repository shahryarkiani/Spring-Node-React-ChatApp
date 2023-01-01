import amqp from 'amqplib/callback_api.js'

const exchange = 'chat.fanout'

export function setupConsumer(handleMessage) {
    amqp.connect('amqp://localhost:5672', (err0, conn) => {
        if (err0)
            throw err0

        conn.createChannel((err1, chan) => {
            if (err1)
                throw err1

            chan.assertExchange(exchange, 'fanout', {
                durable: false
            })

            chan.assertQueue('', {
                exclusive: true
            }, (err2, q) => {

                chan.bindQueue(q.queue, exchange, '')


                chan.consume(q.queue, handleMessage, {noAck: true})

                console.log('Queue is alive and kicking')

            })
        })
    })
}

