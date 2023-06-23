// AbstractCreatePage.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';
import { fetchUserId } from './UserUtils';

const AbstractCreatePage = ({ email, token, entityType, fields, createPath }) => {
    const navigate = useNavigate();
    const [userId, setUserId] = useState(null);
    const [changesMade, setChangesMade] = useState(false);

    useEffect(() => {
        fetchUserId(email, token)
            .then((userId) => setUserId(userId))
            .catch((error) => console.log(error));
    }, [email, token]);

    const [formData, setFormData] = useState(() => {
        const initialFormData = { userId };
        fields.forEach((field) => {
            initialFormData[field.name] = field.initialValue || '';
        });
        return initialFormData;
    });

    useEffect(() => {
        setFormData((prevFormData) => ({ ...prevFormData, userId }));
    }, [userId]);

    const handleDateChange = (date, fieldName) => {
        setFormData((prevFormData) => ({ ...prevFormData, [fieldName]: date }));
        setChangesMade(true);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        const initialFormData = { userId };
        fields.forEach((field) => {
            initialFormData[field.name] = field.initialValue || '';
        });
        setFormData(initialFormData);
        setChangesMade(false);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedFormData = { ...formData };
            fields.forEach((field) => {
                if (field.type === 'date') {
                    if (formData[field.name] !== null && formData[field.name] !== undefined && formData[field.name] !== "") {
                        formattedFormData[field.name] = format(formData[field.name], "yyyy-MM-dd'T'HH:mm:ss.SSS");
                    } else {
                        formattedFormData[field.name] = null;
                    }
                }
                if (field.type === 'object' && !field.visible) {
                    formattedFormData[field.name] = field.initialValue;
                }
            });

            await axios.post(createPath, formattedFormData, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setChangesMade(false);
            navigate(-1);
        } catch (error) {
            console.log(error);
        }
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

    return (
        <div className="list-grid-container">
            <h2>Create {entityType}</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                {fields.map((field) => {
                    if (!field.visible) {
                        return null;
                    }

                    return (
                        <div className="list-grid-input-field" key={field.name}>
                            <label className="list-grid-label">{field.label}:</label>
                            {field.type === 'date' ? (

                                <DatePicker
                                    selected={formData[field.name] !== null ? formData[field.name] : null}
                                    onChange={(date) => handleDateChange(date, field.name)}
                                    value={formData[field.name] ? format(formData[field.name], 'yyyy-MM-dd') : ''}
                                    placeholderText={`Select ${field.label}`}
                                    className="list-grid-input"
                                    dateFormat="yyyy-MM-dd"
                                />
                            ) : (
                                <input
                                    type="text"
                                    name={field.name}
                                    value={formData[field.name]}
                                    onChange={handleInputChange}
                                    placeholder={`Select ${field.label}`}
                                    className="list-grid-input"
                                />
                            )}
                        </div>
                    );
                })}
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

export default AbstractCreatePage;
