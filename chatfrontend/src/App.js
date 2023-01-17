
import './App.css';
import {useState} from "react";
import Login from "./Login";
import ChatRoom from "./Chat";

function App() {

  const [isAuthenticated, setAuthenticated] = useState(false)



  if(!isAuthenticated) {
    return (
        <div className="App">
          <Login setAuth={setAuthenticated}/>
        </div>
    )
  }


  return (
    <ChatRoom/>
  );
}

export default App;
