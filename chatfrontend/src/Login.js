
function Login({setAuth}) {


    function handleLogin(e) {

        e.preventDefault()

        const credentials = new URLSearchParams()
        credentials.append('username', e.target.username.value)
        credentials.append('password', e.target.password.value)

        console.log(credentials.toString())

        return fetch('http://localhost:8080/api/users/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            credentials: 'include',
            body: credentials
        }).then((res) => {
            if(res.ok)
                setAuth(true)

        }).catch((err) => {
            console.log(err)
        })
    }



    return (
        <form onSubmit={handleLogin}>
            <label>
                <p>Username</p>
                <input name="username" type="text" />
            </label>
            <label>
                <p>Password</p>
                <input name="password" type="password" />
            </label>
            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
    )


}

export default Login