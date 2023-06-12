// UserGrid.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FeedEventGrid = ({ token }) => {
    const [feedEventData, setFeedEventData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const feedEventsPerPage = 10;

    useEffect(() => {
        const fetchFeedEventData = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/event/`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setFeedEventData(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchFeedEventData();
    }, [token]);

    const indexOfLastFeedEvent = currentPage * feedEventsPerPage;
    const indexOfFirstFeedEvent = indexOfLastFeedEvent - feedEventsPerPage;
    const currentFeedEvents = feedEventData.slice(indexOfFirstFeedEvent, indexOfLastFeedEvent);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
            <h2>Feed Event Data</h2>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Feed Date</th>
                        <th>User ID</th>
                    </tr>
                </thead>
                <tbody>
                    {currentFeedEvents.map((feedEvent) => (
                        <tr key={feedEvent.id}>
                            <td>{feedEvent.id}</td>
                            <td>{feedEvent.title}</td>
                            <td>{feedEvent.feedDate}</td>
                            <td>{feedEvent.userId}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div>
                {feedEventData.length > feedEventsPerPage && (
                    <ul className="pagination">
                        {Array(Math.ceil(feedEventData.length / feedEventsPerPage))
                            .fill()
                            .map((_, index) => (
                                <li key={index}>
                                    <button onClick={() => paginate(index + 1)}>{index + 1}</button>
                                </li>
                            ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default FeedEventGrid;
