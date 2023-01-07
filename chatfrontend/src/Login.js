const hostname = window.location.hostname + ':8080'

function Login({setAuth}) {


    function handleLogin(e) {
        e.preventDefault()
        console.log(e.target.username.value)
        console.log(e.target.password.value)
    }



    return (
        <form onSubmit={handleLogin}>
            <input type="text" name="username" />
            <input type="password" name="password"/>
            <input type="submit"/>
        </form>
    )


}

export default Login