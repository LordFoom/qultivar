import React, { useState, useEffect } from 'react';
import LoginPage from './LoginPage';
import UserGrid from './UserGrid';
import GrowthStageGrid from './GrowthStageGrid';

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [selectedMenuItem, setSelectedMenuItem] = useState('');
    const [token, setToken] = useState('');

    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        if (storedToken) {
            setIsLoggedIn(true);
            setToken(storedToken);
        }
    }, []);

    const handleLogin = (userToken) => {
        setIsLoggedIn(true);
        setToken(userToken);
        localStorage.setItem('token', userToken);
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
        setToken('');
        localStorage.removeItem('token');
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
                                <button onClick={() => handleMenuItemClick('user')}>User</button>
                            </li>
                            <li>
                                <button onClick={() => handleMenuItemClick('growthStage')}>Growth Stage</button>
                            </li>
                        </ul>
                    </div>
                    <div>
                        {selectedMenuItem === 'user' && <UserGrid token={token} />}
                        {selectedMenuItem === 'growthStage' && <GrowthStageGrid token={token} />}
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
