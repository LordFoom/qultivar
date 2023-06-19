// FeedEventEditPage.js
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';

const FeedEventEditPage = ({ email, token }) => {
    const { feedEventId } = useParams();
    const navigate = useNavigate();
    const [feedEvent, setFeedEvent] = useState(null);
    const [initialValues, setInitialValues] = useState(null);
    const [changesMade, setChangesMade] = useState(false);

    useEffect(() => {
        const fetchFeedEvent = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/event/${feedEventId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const initialFeedEvent = response.data;
                setFeedEvent(initialFeedEvent);
                setInitialValues(initialFeedEvent);
            } catch (error) {
                console.log(error);
            }
        };

        fetchFeedEvent();
    }, [feedEventId, token]);

    const handleDateChange = (date, fieldName) => {
        try {
            const formattedDate = format(date, "yyyy-MM-dd'T'HH:mm:ss.SSS");
            setFeedEvent((prevFeedEvent) => ({ ...prevFeedEvent, [fieldName]: formattedDate }));
            setChangesMade(true);
        } catch (error) {
            console.log(error);
        }
    };

    const handleExit = () => {
        try {
            if (changesMade) {
                const confirmExit = window.confirm('Are you sure you want to exit without saving changes?');
                if (confirmExit) {
                    navigate(-1);
                }
            } else {
                navigate(-1);
            }
        } catch (error) {
            console.log(error);
        }
    };

    if (!feedEvent) {
        return <div>Loading...</div>;
    }

    const handleInputChange = (e) => {
        try {
            const { name, value } = e.target;
            setFeedEvent((prevFeedEvent) => ({ ...prevFeedEvent, [name]: value }));
            setChangesMade(true);
        } catch (error) {
            console.log(error);
        }
    };

    const resetForm = () => {
        try {
            setFeedEvent(initialValues);
            setChangesMade(false);
        } catch (error) {
            console.log(error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await axios.put(`/api/v1/feed/event/${feedEventId}`, feedEvent, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setChangesMade(false);
            navigate(-1);
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div className="list-grid-container">
            <h2>Manage Feed Event</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Feed Date:</label>
                    <DatePicker
                        selected={new Date(feedEvent.feedDate)}
                        onChange={(date) => handleDateChange(date, 'feedDate')}
                        dateFormat="yyyy-MM-dd"
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Description:</label>
                    <input
                        type="text"
                        name="description"
                        value={feedEvent.description}
                        onChange={handleInputChange}
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-button-row">
                    <button type="submit" disabled={!changesMade} className="list-grid-button">
                        Save
                    </button>
                    <button type="button" disabled={!changesMade} onClick={resetForm} className="list-grid-button">
                        Cancel
                    </button>
                </div>
            </form>
            <div className="list-grid-button-row">
                <button onClick={handleExit} className="list-grid-button">
                    Exit
                </button>
            </div>
            {console.log(JSON.stringify(feedEvent))}
        </div>
    );
};

export default FeedEventEditPage;
