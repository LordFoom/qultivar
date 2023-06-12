// GrowStageGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ListGrid.css';

const GrowStageGrid = ({ token }) => {
    const [growStageData, setGrowStageData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const growStagesPerPage = 10;

    useEffect(() => {
        const fetchGrowStageData = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/growstage/`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setGrowStageData(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrowStageData();
    }, [token]);

    const indexOfLastGrowStage = currentPage * growStagesPerPage;
    const indexOfFirstGrowStage = indexOfLastGrowStage - growStagesPerPage;
    const currentGrowStages = growStageData.slice(indexOfFirstGrowStage, indexOfLastGrowStage);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="list-grid-container">
            <h2>Grow Stage Data</h2>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {currentGrowStages.map((stage) => (
                        <tr key={stage.id}>
                            <td>{stage.id}</td>
                            <td>{stage.name}</td>
                            <td>{stage.description}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="list-grid-pagination">
                {growStageData.length > growStagesPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(growStageData.length / growStagesPerPage))
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

export default GrowStageGrid;
