// UserGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ListGrid.css';

const UserGrid = ({ token }) => {
    const [userData, setUserData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const usersPerPage = 10;

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axios.get(`/api/v1/user/`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setUserData(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchUserData();
    }, [token]);

    const indexOfLastUser = currentPage * usersPerPage;
    const indexOfFirstUser = indexOfLastUser - usersPerPage;
    const currentUsers = userData.slice(indexOfFirstUser, indexOfLastUser);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="list-grid-container">
            <h2>User Data</h2>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    {currentUsers.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="list-grid-pagination">
                {userData.length > usersPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(userData.length / usersPerPage))
                            .fill()
                            .map((_, index) => (
                                <li key={index}>
                                    <button onClick={() => paginate(index + 1)}>{index + 1}</button>
                                </li>
                            ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default UserGrid;
