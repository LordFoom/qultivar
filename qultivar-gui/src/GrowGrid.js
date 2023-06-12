// GrowGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { fetchUserId } from './UserUtils';
import './ListGrid.css';

const GrowGrid = ({ email, token }) => {
    const [growData, setGrowData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const growsPerPage = 10;

    useEffect(() => {
        const fetchGrowData = async () => {
            try {
                const userId = await fetchUserId(email, token);
                if (userId) {
                    const response = await axios.get(`/api/v1/feed/grow/user/${userId}`, {
                        headers: { Authorization: `Bearer ${token}` },
                    });
                    setGrowData(response.data);
                }
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrowData();
    }, [email, token]);

    const indexOfLastGrow = currentPage * growsPerPage;
    const indexOfFirstGrow = indexOfLastGrow - growsPerPage;
    const currentGrows = growData.slice(indexOfFirstGrow, indexOfLastGrow);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="list-grid-container">
            <h2>Grow Data</h2>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>User ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {currentGrows.map((grow) => (
                        <tr key={grow.id}>
                            <td>{grow.id}</td>
                            <td>{grow.name}</td>
                            <td>{grow.startDate}</td>
                            <td>{grow.endDate}</td>
                            <td>{grow.userId}</td>
                            <td>
                                <Link to={`/grow/edit/${grow.id}`}>
                                    <button>Edit</button>
                                </Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="list-grid-pagination">
                {growData.length > growsPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(growData.length / growsPerPage))
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

export default GrowGrid;
