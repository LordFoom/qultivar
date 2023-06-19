// FeedEventGrid.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { format } from 'date-fns';

const FeedEventGrid = ({ growId, token }) => {
    const [feedEvents, setFeedEvents] = useState([]);

    useEffect(() => {
        const fetchFeedEvents = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/event/grow/${growId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setFeedEvents(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchFeedEvents();
    }, [growId, token]);

    return (
        <div className="list-grid-container">
            <h3>Feed Events</h3>
            <table className="list-grid-table">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {feedEvents.map((feedEvent) => (
                        <tr key={feedEvent.id}>
                            <td>{format(new Date(feedEvent.feedDate), 'yyyy-MM-dd')}</td>
                            <td>{feedEvent.description}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default FeedEventGrid;
