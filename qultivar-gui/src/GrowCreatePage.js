// GrowCreatePage.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';
import { fetchUserId } from './UserUtils';

const GrowCreatePage = ({ email, token }) => {
    const navigate = useNavigate();
    const userId = fetchUserId(email, token);

    const [grow, setGrow] = useState({
        name: '',
        startDate: new Date().getTime(),
        endDate: null,
        userId: userId, // Set the user ID value as needed
    });
    const [changesMade, setChangesMade] = useState(false);

    const handleDateChange = (date, fieldName) => {
        const formattedDate = format(date, "yyyy-MM-dd'T'HH:mm:ss.SSS");
        setGrow((prevGrow) => ({ ...prevGrow, [fieldName]: formattedDate }));
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
        setGrow((prevGrow) => ({ ...prevGrow, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        setGrow({
            name: '',
            startDate: new Date.getTime(),
            endDate: null,
            userId: userId,
        });
        setChangesMade(false);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('/api/v1/feed/grow', grow, {
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
            <h2>Create Grow</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Name:</label>
                    <input
                        type="text"
                        name="name"
                        value={grow.name}
                        onChange={handleInputChange}
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">Start Date:</label>
                    <DatePicker
                        selected={grow.startDate}
                        onChange={(date) => handleDateChange(date, 'startDate')}
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">End Date:</label>
                    <DatePicker
                        selected={grow.endDate}
                        onChange={(date) => handleDateChange(date, 'endDate')}
                        placeholderText="Select End Date"
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

export default GrowCreatePage;
