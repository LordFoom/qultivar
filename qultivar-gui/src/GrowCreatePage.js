// GrowCreatePage.js
import React from 'react';
import AbstractCreatePage from './AbstractCreatePage';

const GrowCreatePage = ({ email, token }) => {
    const entityType = 'Grow';

    const fields = [
        {
            name: 'name',
            label: 'Name',
            type: 'text',
            initialValue: '',
            visible: true,
        },
        {
            name: 'startDate',
            label: 'Start Date',
            type: 'date',
            initialValue: new Date().getTime(),
            visible: true,
        },
        {
            name: 'endDate',
            label: 'End Date',
            type: 'date',
            initialValue: null,
            visible: true,
        },
    ];

    return (
        <AbstractCreatePage
            email={email}
            token={token}
            entityType={entityType}
            fields={fields}
            createPath="/api/v1/feed/grow" />
    );
};

export default GrowCreatePage;
