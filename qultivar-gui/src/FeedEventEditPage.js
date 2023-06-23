// FeedEventEditPage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import AbstractEntityEditPage from './AbstractEntityEditPage';

const FeedEventEditPage = ({ email, token }) => {
    const { feedEventId } = useParams();

    const entityType = 'Feed Event';

    const fields = [
        {
            name: 'feedDate',
            label: 'Feed Date',
            type: 'date',
            visible: true,
        },
        {
            name: 'description',
            label: 'Description',
            type: 'text',
            visible: true,
        },
    ];

    return (
        <>
            <AbstractEntityEditPage
                email={email}
                token={token}
                entityType={entityType}
                fields={fields}
                fetchPath={`/api/v1/feed/event/${feedEventId}`}
                editPath={`/api/v1/feed/event/${feedEventId}`}
            />
        </>
    );
};

export default FeedEventEditPage;
