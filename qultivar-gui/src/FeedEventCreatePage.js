// FeedEventCreatePage.js
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import AbstractEntityCreatePage from './AbstractEntityCreatePage';

const FeedEventCreatePage = ({ email, token }) => {
    const { growId } = useParams();

    const entityType = 'Feed Event';
    const [grow, setGrow] = useState(null);

    useEffect(() => {
        const fetchGrow = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/grow/${growId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setGrow(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrow();
    }, [growId, token]);

    const fields = [
        {
            name: 'feedDate',
            label: 'Feed Date',
            type: 'date',
            initialValue: new Date().getTime(),
            visible: true,
        },
        {
            name: 'description',
            label: 'Description',
            type: 'text',
            initialValue: '',
            visible: true,
        },
        {
            name: 'grow',
            label: 'Grow',
            type: 'object',
            initialValue: grow,
            visible: false,
        },
    ];

    return (
        <AbstractEntityCreatePage
            email={email}
            token={token}
            entityType={entityType}
            fields={fields}
            createPath="/api/v1/feed/event" />
    );

};

export default FeedEventCreatePage;
