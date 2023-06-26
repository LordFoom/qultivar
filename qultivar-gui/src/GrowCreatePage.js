// GrowCreatePage.js
import React, { useState, useEffect } from 'react';
import { fetchUserId } from './UserUtils';
import AbstractEntityCreatePage from './AbstractEntityCreatePage';
import GrowEntity from './GrowEntity';

const GrowCreatePage = ({ email, token }) => {
    const [userId, setUserId] = useState(null);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        fetchUserId(email, token)
            .then((fetchedUserId) => {
                setUserId(fetchedUserId);
                setLoading(false);
            })
            .catch((error) => {
                throw new Error(error);
            });
    }, [email, token]);

    // Ensure that the userId is resolved before continuing
    if (isLoading) {
        return <div>Loading...</div>;
    }

    const entityDefinition = new GrowEntity(userId);

    return (
        <AbstractEntityCreatePage email={email} token={token} entityDefinition={entityDefinition} />
    );
};

export default GrowCreatePage;
