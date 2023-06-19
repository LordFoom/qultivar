// GrowGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { fetchUserId } from './UserUtils';
import './ListGrid.css';
import { format } from 'date-fns';


const GrowGrid = ({ email, token }) => {
    const [growData, setGrowData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const growsPerPage = 10;
    const navigate = useNavigate();

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

    const handleCreateClick = () => {
        navigate(`/grow/create`);
    };

    const handleViewClick = (growId) => {
        navigate(`/grow/edit/${growId}`);
    };

    const handleDeleteClick = async (growId) => {
        if (window.confirm('Are you sure you want to delete this item?')) {
            try {
                axios.delete(`/api/v1/feed/grow/${growId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log(`Grow with ID ${growId} deleted successfully.`);
                setGrowData(growData.filter((grow) => grow.id !== growId));
            } catch (error) {
                console.log(`Error deleting grow with ID ${growId}:`, error);
            }
        }
    };

    return (
        <div className="list-grid-container">
            <div className="list-grid-header">
                <h2>Grow Data</h2>
                <button className="create-button" onClick={() => handleCreateClick()}>Create</button>
            </div>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {currentGrows.map((grow) => (
                        <tr key={grow.id}>
                            <td>{grow.name}</td>
                            <td>{format(new Date(grow.startDate), 'yyyy-MM-dd')}</td>
                            <td>{grow.endDate ? format(new Date(grow.endDate), 'yyyy-MM-dd') : ''}</td>
                            <td>
                                <button className="view-button" onClick={() => handleViewClick(grow.id)}>View</button>
                                <button className="delete-button" onClick={() => handleDeleteClick(grow.id)}>Delete</button>
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
