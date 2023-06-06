// GrowthStageGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const GrowthStageGrid = ({ token }) => {
    const [growthStageData, setGrowthStageData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const growthStagesPerPage = 10;

    useEffect(() => {
        const fetchGrowthStageData = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/growthstage/`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setGrowthStageData(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrowthStageData();
    }, [token]);

    const indexOfLastGrowthStage = currentPage * growthStagesPerPage;
    const indexOfFirstGrowthStage = indexOfLastGrowthStage - growthStagesPerPage;
    const currentGrowthStages = growthStageData.slice(indexOfFirstGrowthStage, indexOfLastGrowthStage);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
            <h2>Grow Stage Data</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {currentGrowthStages.map((stage) => (
                        <tr key={stage.id}>
                            <td>{stage.id}</td>
                            <td>{stage.name}</td>
                            <td>{stage.description}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div>
                {growthStageData.length > growthStagesPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(growthStageData.length / growthStagesPerPage))
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

export default GrowthStageGrid;
