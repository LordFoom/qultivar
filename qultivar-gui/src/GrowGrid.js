// GrowGrid.js
import React, { useEffect, useState } from 'react';
import { fetchUserId } from './UserUtils';
import QultivarEntityGrid from './QultivarEntityGrid'
import GrowDefinition from './GrowDefinition';

const GrowGrid = ({ email, token }) => {
    const [userId, setUserId] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const resolveUserId = async () => {
            try {
                const resolvedUserId = await fetchUserId(email, token);
                setUserId(resolvedUserId);
                setIsLoading(false);
            } catch (error) {
                console.log(error);
                setIsLoading(false);
            }
        };

        resolveUserId();
    }, [email, token]);

    // ensure that the userId is resolved before continuing
    if (isLoading) {
        return <p>Loading...</p>;
    }

    const entityDefinition = new GrowDefinition(userId);
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
        />
    );
};

export default GrowGrid;
