import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import Login from "./Login";

function App() {

  const [isAuthenticated, setAuthenticated] = useState(false)


  useEffect(() => {
    const hostname = window.location.hostname + ':8080'

    fetch('http://' + hostname + '/api/users/login').then(res => {
      if(res.ok)
        setAuthenticated(true)
    }).catch((err) => {console.log(err)})
  })

  if(!isAuthenticated) {
    return (
        <div className="App">
          <Login setAuth={setAuthenticated}/>
        </div>
    )
  }


  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
