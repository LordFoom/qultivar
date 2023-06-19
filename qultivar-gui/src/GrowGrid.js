// GrowGrid.js
import React, { useEffect, useState } from 'react';
import AbstractEntityGrid from './AbstractEntityGrid';
import { fetchUserId } from './UserUtils';

const GrowGrid = ({ email, token }) => {
    const columnHeaders = ['Name', 'Start Date', 'End Date'];
    const entityAttributes = ['name', 'startDate', 'endDate'];
    const dateColumns = ['startDate', 'endDate'];
    const dateFormat = 'yyyy-MM-dd';
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        const resolveUserId = async () => {
            try {
                const resolvedUserId = await fetchUserId(email, token);
                setUserId(resolvedUserId);
            } catch (error) {
                console.log(error);
            }
        };

        resolveUserId();
    }, [email, token]);

    return (
        userId && (
            <AbstractEntityGrid
                email={email}
                token={token}
                gridHeader="Grows"
                entityName="Grow"
                createPath="/grow/create"
                editPath="/grow/edit"
                deletePath="/api/v1/feed/grow"
                selectPath={`/api/v1/feed/grow/user/${userId}`}
                columnHeaders={columnHeaders}
                entityAttributes={entityAttributes}
                dateColumns={dateColumns}
                dateFormat={dateFormat}
            />
        )
    );
};

export default GrowGrid;
