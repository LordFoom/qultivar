// App.js
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import LoginPage from './LoginPage';
import UserGrid from './UserGrid';
import GrowStageGrid from './GrowStageGrid';
import GrowGrid from './GrowGrid';
import GrowEditPage from './GrowEditPage';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState('');
  const [email, setEmail] = useState('');

  useEffect(() => {
    const storedEmail = localStorage.getItem('email');
    const storedToken = localStorage.getItem('token');
    if (storedToken && storedEmail) {
      setIsLoggedIn(true);
      setEmail(storedEmail);
      setToken(storedToken);
    }
  }, []);

  const handleLogin = (userEmail, userToken) => {
    setIsLoggedIn(true);
    setEmail(userEmail);
    setToken(userToken);
    localStorage.setItem('email', userEmail);
    localStorage.setItem('token', userToken);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setToken('');
    setEmail('');
    localStorage.removeItem('token');
    localStorage.removeItem('email');
  };

  return (
    <Router>
      <div>
        {isLoggedIn ? (
          <div>
            <h1>Navigation Menu</h1>
            <div>
              <ul>
                <li>
                  <Link to="/users">Users</Link>
                </li>
                <li>
                  <Link to="/growStages">Grow Stages</Link>
                </li>
                <li>
                  <Link to="/grows">Grows</Link>
                </li>
              </ul>
            </div>

            <Routes>
              <Route path="/users" element={<UserGrid token={token} />} />
              <Route path="/growStages" element={<GrowStageGrid token={token} />} />
              <Route path="/grows" element={<GrowGrid email={email} token={token} />} />
              <Route path="/grow/edit/:growId" element={<GrowEditPage token={token} />} />
            </Routes>

            <button onClick={handleLogout}>Logout</button>
          </div>
        ) : (
          <LoginPage handleLogin={handleLogin} />
        )}
      </div>
    </Router>
  );
};

export default App;
