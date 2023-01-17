

function ChatRoom() {



    function handleSend(e) {
        e.preventDefault()

        const msg = {
            "to" : "nobody",
            "msgBody" : e.target.msgBody.value
        }

        return fetch('http://localhost:8080/api/messages',{
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify(msg)
        }).then((res) => {
            if(res.ok)
                alert('Message Sent Successfully')
            else
                alert('Shoot it went down')
        }).catch((err) => {
            alert('We had an error boss, more in console')
            console.log(err)
        })


    }


    return (
        <div>
            <h1>The Chat Room</h1>
        <form onSubmit={handleSend}>
            <label>
                <p>Send a message</p>
                <input name="msgBody" type="text" />
            </label>
            <div>
                <button type="submit">Send IT</button>
            </div>
        </form>
        </div>
    )




}

export default ChatRoom