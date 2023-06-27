// App.js
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import LoginPage from './LoginPage';
import UserGrid from './UserGrid';
import GrowStageGrid from './GrowStageGrid';
import GrowGrid from './GrowGrid';
import GrowEditPage from './GrowEditPage';
import GrowCreatePage from './GrowCreatePage';
import FeedEventEditPage from './FeedEventEditPage';
import FeedEventCreatePage from './FeedEventCreatePage';
import ExampleStaticTableGrid from './ExampleStaticTableGrid';

import './App.css';

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
            <div className="app-container">
                {isLoggedIn ? (
                    <div className="panel-container">
                        <div className="navigation-panel">
                            <h1>Navigation Menu</h1>
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
                                <li>
                                    Examples
                                    <ul>
                                        <li>
                                            <Link to="/examples/staticTable">StaticData</Link>
                                        </li>
                                        <li>
                                            <Link to="/examples/oneToManyParent">OneToManyParent</Link>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <button onClick={handleLogout}>Logout</button>
                        </div>
                        <div className="grid-panel">
                            <Routes>
                                <Route path="/users" element={<UserGrid token={token} />} />
                                <Route path="/growStages" element={<GrowStageGrid email={email} token={token} />} />
                                <Route path="/grows" element={<GrowGrid email={email} token={token} />} />
                                <Route path="/grow/edit/:growId" element={<GrowEditPage email={email} token={token} />} />
                                <Route path="/grow/create" element={<GrowCreatePage email={email} token={token} />} />
                                <Route path="/feedEvent/edit/:feedEventId" element={<FeedEventEditPage email={email} token={token} />} />
                                <Route path="/feedEvent/create/:growId" element={<FeedEventCreatePage email={email} token={token} />} />
                                <Route path="/examples/staticTable" element={<ExampleStaticTableGrid email={email} token={token} />} />
                                {/* <Route path="/examples/oneToManyParent" element={<OneToManyParentGrid />} /> */}
                            </Routes>
                        </div>
                    </div>
                ) : (
                    <LoginPage handleLogin={handleLogin} />
                )}
            </div>
        </Router>
    );
};

export default App;
