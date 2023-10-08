import Home from './pages/Home'
import ForYou from './pages/ForYou'
import Matches from './pages/Matches'
import { Routes, Route } from 'react-router-dom';
import { createContext , useState } from 'react'


import './App.css'
import Sidebar from './Sidebar';

export const Context = createContext();

function App() {
  const [loggedIn, setloggedIn] = useState(false);


  return (
    <>
      <Context.Provider value={[loggedIn, setloggedIn]}>
        <div className='mainwrap'>
          <Sidebar />
          <div className='content'>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/foryou" element={<ForYou />} />
              <Route path="/matches" element={<Matches />} />
            </Routes>
          </div>
          {/* <div className='footer'> </div> */}
        </div>
      </Context.Provider>

    </>
  )
}

export default App
