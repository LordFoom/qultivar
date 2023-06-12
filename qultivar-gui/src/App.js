// App.js
import React, { useState, useEffect } from 'react';
import LoginPage from './LoginPage';
import UserGrid from './UserGrid';
import GrowStageGrid from './GrowStageGrid';
import FeedEventGrid from './FeedEventGrid';

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [selectedMenuItem, setSelectedMenuItem] = useState('');
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

    const handleMenuItemClick = (menuItem) => {
        setSelectedMenuItem(menuItem);
    };

    return (
        <div>
            {isLoggedIn ? (
                <div>
                    <h1>Navigation Menu</h1>
                    <div>
                        <ul>
                            <li>
                                <button onClick={() => handleMenuItemClick('user')}>Users</button>
                            </li>
                            <li>
                                <button onClick={() => handleMenuItemClick('growStage')}>Grow Stages</button>
                            </li>
                            <li>
                                <button onClick={() => handleMenuItemClick('feedEvent')}>Feed Events</button>
                            </li>
                        </ul>
                    </div>
                    <div>
                        {selectedMenuItem === 'user' && <UserGrid token={token} />}
                        {selectedMenuItem === 'growStage' && <GrowStageGrid token={token} />}
                        {selectedMenuItem === 'feedEvent' && <FeedEventGrid email={email} token={token} />}
                    </div>
                    <button onClick={handleLogout}>Logout</button>
                </div>
            ) : (
                <LoginPage handleLogin={handleLogin} />
            )}
        </div>
    );
};

export default App;
