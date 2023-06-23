// AbstractStaticEntityGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { fetchUserId } from './UserUtils';
import './ListGrid.css';
import { format } from 'date-fns';
import { FaChevronUp, FaChevronDown } from 'react-icons/fa';

const AbstractStaticEntityGrid = ({
    email,
    token,
    gridHeader,
    entityName,
    selectPath,
    columnHeaders,
    entityAttributes,
    dateColumns,
}) => {
    const [entityData, setEntityData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [sortConfig, setSortConfig] = useState({ key: '', direction: '' });
    const [isCollapsed, setIsCollapsed] = useState(false);
    const entitiesPerPage = 10;
    const dateFormat = 'yyyy-MM-dd';

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

    const formatDate = (date) => {
        if (date) {
            return format(new Date(date), dateFormat);
        }
        return '';
    };

    const renderSortIcon = (key) => {
        if (sortConfig.key === key) {
            return sortConfig.direction === 'asc' ? '▲' : '▼';
        }
        return null;
    };

    const handleToggleCollapse = () => {
        setIsCollapsed(!isCollapsed);
    };

    return (
        <div className={`list-grid-container ${isCollapsed ? 'collapsed' : ''}`}>
            <div className="list-grid-header" onClick={handleToggleCollapse}>
                <h2>{`${gridHeader}`}</h2>
                <div className="header-buttons">
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
                                {columnHeaders.map((header, index) => (
                                    <th key={index} onClick={() => handleSort(entityAttributes[index])}>
                                        {header}
                                        {renderSortIcon(entityAttributes[index])}
                                    </th>
                                ))}
                            </tr>
                        </thead>
                        <tbody>
                            {sortedEntities().map((entity) => (
                                <tr key={entity.id}>
                                    {entityAttributes.map((attribute, index) => (
                                        <td key={index} readOnly>
                                            {dateColumns.includes(attribute)
                                                ? formatDate(entity[attribute])
                                                : entity[attribute]}
                                        </td>
                                    ))}
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

export default AbstractStaticEntityGrid;
