// GrowEditPage.js
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';

const GrowEditPage = ({ token }) => {
    const { growId } = useParams();
    const navigate = useNavigate();
    const [grow, setGrow] = useState(null);
    const [initialValues, setInitialValues] = useState(null);
    const [changesMade, setChangesMade] = useState(false);

    useEffect(() => {
        const fetchGrow = async () => {
            try {
                const response = await axios.get(`/api/v1/feed/grow/${growId}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const initialGrow = response.data;
                setGrow(initialGrow);
                setInitialValues(initialGrow);
            } catch (error) {
                console.log(error);
            }
        };

        fetchGrow();
    }, [growId, token]);

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

    if (!grow) {
        return <div>Loading...</div>;
    }

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setGrow((prevGrow) => ({ ...prevGrow, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        setGrow(initialValues);
        setChangesMade(false);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await axios.put(`/api/v1/feed/grow/${growId}`, grow, {
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
            <h2>Edit Grow</h2>
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
                        selected={new Date(grow.startDate)}
                        onChange={(date) => handleDateChange(date, 'startDate')}
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">End Date:</label>
                    <DatePicker
                        selected={grow.endDate ? new Date(grow.endDate) : null}
                        onChange={(date) => handleDateChange(date, 'endDate')}
                        placeholderText="Select End Date"
                        className="list-grid-input"
                    />
                </div>
                <div className="list-grid-input-field">
                    <label className="list-grid-label">User ID:</label>
                    <input
                        type="text"
                        name="userId"
                        value={grow.userId}
                        onChange={handleInputChange}
                        disabled
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

export default GrowEditPage;
