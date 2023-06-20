// FeedEventCreatePage.js
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';

const FeedEventCreatePage = ({ email, token }) => {
    const { growId } = useParams();
    const navigate = useNavigate();

    const [feedEvent, setFeedEvent] = useState({
        feedDate: new Date().getTime(),
        description: "",
        grow: null
    });

    useEffect(() => {
        const fetchGrow = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/grow/${growId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const grow = response.data;

                setFeedEvent(prevFeedEvent => ({...prevFeedEvent, grow: grow}));
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrow();
    }, [growId]);

    const [changesMade, setChangesMade] = useState(false);

    const handleDateChange = (date, fieldName) => {
        setFeedEvent((prevFeedEvent) => ({ ...prevFeedEvent, [fieldName]: date }));
        setChangesMade(true);
    };

    const handleExit = () => {
        if (changesMade) {
            const confirmExit = window.confirm('Are you sure you want to exit without saving changes?');
            if (confirmExit) {
                navigate(-1);
            }
        } else {
            navigate(-1);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFeedEvent((prevFeedEvent) => ({ ...prevFeedEvent, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        setFeedEvent({
            feedDate: new Date().getTime(),
            description: "",
            growId: growId
        });
        setChangesMade(false);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            let formattedFeedDate = format(feedEvent.feedDate, "yyyy-MM-dd'T'HH:mm:ss.SSS");
            const formattedFeedEvent = {
                ...feedEvent,
                feedDate: formattedFeedDate,
            };

            await axios.post('/api/v1/feed/event', formattedFeedEvent, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setChangesMade(false);
            navigate(-1);
        } catch (error) {
            console.log(error);
        }
    }


    return (
        <div className="list-grid-container">
            <h2>Create Feed Event</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Feed Date:</label>
                    <DatePicker
                        selected={feedEvent.feedDate}
                        onChange={(date) => handleDateChange(date, 'feedDate')}
                        value={feedEvent.feedDate ? format(feedEvent.feedDate, "yyyy-MM-dd") : format(new Date(), "yyyy-MM-dd")}
                        className="list-grid-input"
                        dateFormat="yyyy-MM-dd"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Name:</label>
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
        </div>
    );
};

export default FeedEventCreatePage;
