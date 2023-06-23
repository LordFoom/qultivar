// AbstractEntityGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { fetchUserId } from './UserUtils';
import './ListGrid.css';
import { format } from 'date-fns';

const AbstractEntityGrid = ({
    email,
    token,
    gridHeader,
    entityName,
    createPath,
    editPath,
    deletePath,
    selectPath,
    columnHeaders,
    entityAttributes,
    dateColumns,
    dateFormat,
    handleButtonClick,
}) => {
    const [entityData, setEntityData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortConfig, setSortConfig] = useState({ key: '', direction: '' });
    const entitiesPerPage = 10;
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEntityData = async () => {
            try {
                const userId = await fetchUserId(email, token);
                if (userId) {
                    const response = await axios.get(selectPath, {
                        headers: { Authorization: `Bearer ${token}` },
                    });
                    setEntityData(response.data);
                }
            } catch (error) {
                console.log(error);
            }
        };

        fetchEntityData();
    }, [email, token, entityName, selectPath]);

    const indexOfLastEntity = currentPage * entitiesPerPage;
    const indexOfFirstEntity = indexOfLastEntity - entitiesPerPage;
    const currentEntities = entityData.slice(indexOfFirstEntity, indexOfLastEntity);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const handleCreateClick = () => {
        navigate(createPath);
        handleButtonClick();
    };

    const handleEditClick = (entityId) => {
        navigate(`${editPath}/${entityId}`);
        handleButtonClick();
    };

    const handleDeleteClick = async (entityId) => {
        if (window.confirm('Are you sure you want to delete this item?')) {
            handleButtonClick();
            try {
                await axios.delete(`${deletePath}/${entityId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log(`${entityName} with ID ${entityId} deleted successfully.`);
                setEntityData(entityData.filter((entity) => entity.id !== entityId));
            } catch (error) {
                console.log(`Error deleting ${entityName} with ID ${entityId}:`, error);
            }
        }
    };

    const formatDate = (date) => {
        if (date) {
            return format(new Date(date), dateFormat);
        }
        return '';
    };

    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        setSortConfig({ key, direction });
    };

    const sortedEntities = () => {
        const data = [...currentEntities];
        if (sortConfig.key) {
            data.sort((a, b) => {
                if (a[sortConfig.key] < b[sortConfig.key]) {
                    return sortConfig.direction === 'asc' ? -1 : 1;
                }
                if (a[sortConfig.key] > b[sortConfig.key]) {
                    return sortConfig.direction === 'asc' ? 1 : -1;
                }
                return 0;
            });
        }
        return data;
    };

    const renderSortIcon = (key) => {
        if (sortConfig.key === key) {
            return sortConfig.direction === 'asc' ? '▲' : '▼';
        }
        return null;
    };

    return (
        <div className="list-grid-container">
            <div className="list-grid-header">
                <h2>{`${gridHeader}`}</h2>
                <button className="create-button" onClick={handleCreateClick}>
                    Create
                </button>
            </div>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        {columnHeaders.map((header, index) => (
                            <th key={index} onClick={() => handleSort(entityAttributes[index])}>
                                {header}
                                {renderSortIcon(entityAttributes[index])}
                            </th>
                        ))}
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {sortedEntities().map((entity) => (
                        <tr key={entity.id}>
                            {entityAttributes.map((attribute, index) => (
                                <td key={index}>
                                    {dateColumns.includes(attribute) ? formatDate(entity[attribute]) : entity[attribute]}
                                </td>
                            ))}
                            <td>
                                <button className="edit-button" onClick={() => handleEditClick(entity.id)}>
                                    Edit
                                </button>
                                <button className="delete-button" onClick={() => handleDeleteClick(entity.id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="list-grid-pagination">
                {entityData.length > entitiesPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(entityData.length / entitiesPerPage))
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

export default AbstractEntityGrid;
