// GrowEditPage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import AbstractEntityEditPage from './AbstractEntityEditPage';
import FeedEventGrid from './FeedEventGrid';

const GrowEditPage = ({ email, token }) => {
    const { growId } = useParams();

    const entityType = 'Grow';

    const fields = [
        {
            name: 'name',
            label: 'Name',
            type: 'text',
            visible: true,
        },
        {
            name: 'startDate',
            label: 'Start Date',
            type: 'date',
            visible: true,
        },
        {
            name: 'endDate',
            label: 'End Date',
            type: 'date',
            visible: true,
        }
    ];


    return (
        <>
            <AbstractEntityEditPage
                email={email}
                token={token}
                entityType={entityType}
                fields={fields}
                fetchPath={`/api/v1/feed/grow/${growId}`}
                editPath={`/api/v1/feed/grow/${growId}`}
            />
            <FeedEventGrid email={email} token={token} growId={growId} />
        </>
    );

};

export default GrowEditPage;
