// QultivarEntityGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';
import { FaChevronUp, FaChevronDown } from 'react-icons/fa';

import './QultivarEntityGrid.css';

const QultivarEntityGrid = ({
    email,
    token,
    entityDefinition,
    readOnly = false
}) => {
    const [entityData, setEntityData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortConfig, setSortConfig] = useState({ key: '', direction: '' });
    const [isCollapsed, setIsCollapsed] = useState(false);
    const entitiesPerPage = 10;
    const dateFormat = 'yyyy-MM-dd';

    const navigate = useNavigate();

    // load the data
    useEffect(() => {
        const fetchEntityData = async () => {
            try {
                const response = await axios.get(entityDefinition.selectAllPath(), {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setEntityData(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchEntityData();
    }, [email, token, entityDefinition]);

    const indexOfLastEntity = currentPage * entitiesPerPage;
    const indexOfFirstEntity = indexOfLastEntity - entitiesPerPage;
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    // redirect to the gui create page 
    const handleCreateClick = () => {
        if (readOnly) return;
        navigate(entityDefinition.guiCreatePath());
    };

    // redirect to the gui edit page 
    const handleEditClick = (entityId) => {
        if (readOnly) return;
        navigate(entityDefinition.guiUpdatePath(entityId));
    };

    // DELETE sent to the api-service 
    const handleDeleteClick = async (entityId) => {
        if (readOnly) return;
        if (window.confirm('Are you sure you want to delete this item?')) {
            try {
                await axios.delete(entityDefinition.deletePath(entityId), {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log(`${entityDefinition.name} with ID ${entityId} deleted successfully.`);
                setEntityData(entityData.filter((entity) => entity.id !== entityId));
            } catch (error) {
                console.log(`Error deleting ${entityDefinition.name} with ID ${entityId}:`, error);
            }
        }
    };

    // grid column sorting functionality
    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        setSortConfig({ key, direction });
    };

    const sortedEntities = (data, sortConfig) => {
        const sortedData = [...data];
        if (sortConfig.key) {
            sortedData.sort((a, b) => {
                if (a[sortConfig.key] < b[sortConfig.key]) {
                    return sortConfig.direction === 'asc' ? -1 : 1;
                }
                if (a[sortConfig.key] > b[sortConfig.key]) {
                    return sortConfig.direction === 'asc' ? 1 : -1;
                }
                return 0;
            });
        }
        return sortedData;
    };

    const renderSortIcon = (key) => {
        if (sortConfig.key === key) {
            return sortConfig.direction === 'asc' ? '▲' : '▼';
        }
        return null;
    };

    // grid collapse/expand functionality
    const handleToggleCollapse = () => {
        setIsCollapsed(!isCollapsed);
    };

    // format the dates in the grid
    const formatDate = (date) => {
        if (date) {
            return format(new Date(date), dateFormat);
        }
        return '';
    };

    // Helper function to get the field label based on the attribute name
    const getFieldLabel = (attributeName) => {
        const field = entityDefinition.fields.find((field) => field.name === attributeName);
        return field ? field.label : '';
    };

    return (
        <div className={`list-grid-container ${isCollapsed ? 'collapsed' : ''}`}>
            <div className="list-grid-header" onClick={handleToggleCollapse}>
                <h2>{`${entityDefinition.plural}`}</h2>
                <div className="header-buttons">
                    {!readOnly && (
                        <button className="create-button" onClick={handleCreateClick}>
                            Create
                        </button>
                    )}
                    <span className="icon-space">
                        {isCollapsed ? <FaChevronDown /> : <FaChevronUp />}
                    </span>
                </div>
            </div>
            {!isCollapsed && (
                <>
                    <table className="list-grid-table">
                        <thead>
                            <tr>
                                {entityDefinition.renderOnGridFields.map((attributeName, index) => (
                                    <th key={index} onClick={() => handleSort(attributeName)}>
                                        {getFieldLabel(attributeName)}
                                        {renderSortIcon(attributeName)}
                                    </th>
                                ))}
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {sortedEntities(entityData, sortConfig).slice(indexOfFirstEntity, indexOfLastEntity).map((entity) => (
                                <tr key={entity.id}>
                                    {entityDefinition.renderOnGridFields.map((attributeName, index) => {
                                        const field = entityDefinition.fields.find((field) => field.name === attributeName);
                                        const fieldValue = entity[field.name];
                                        return (
                                            <td key={index}>
                                                {field.dataType === 'date' ? formatDate(fieldValue) : fieldValue}
                                            </td>
                                        );
                                    })}
                                    <td>
                                        {!readOnly && (
                                            <button className="edit-button" onClick={() => handleEditClick(entity.id)}>
                                                Edit
                                            </button>
                                        )}
                                        {!readOnly && (
                                            <button className="delete-button" onClick={() => handleDeleteClick(entity.id)}>
                                                Delete
                                            </button>
                                        )}
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
                </>
            )}
        </div>
    );
};

export default QultivarEntityGrid;
