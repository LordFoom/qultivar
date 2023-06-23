// AbstractEntityEditPage.js
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';

const AbstractEntityEditPage = ({ email, token, entityType, fields, fetchPath, editPath }) => {
    const { itemId } = useParams();
    const navigate = useNavigate();
    const [item, setItem] = useState(null);
    const [initialValues, setInitialValues] = useState(null);
    const [changesMade, setChangesMade] = useState(false);

    useEffect(() => {
        const fetchItem = async () => {
            try {
                const response = await axios.get(fetchPath, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const initialItem = response.data;
                setItem(initialItem);
                setInitialValues(initialItem);
            } catch (error) {
                console.log(error);
            }
        };

        fetchItem();
    }, [itemId, token, fetchPath]);

    const handleDateChange = (date, fieldName) => {
        var formattedDate = null
        if (date) {
            formattedDate = format(date, "yyyy-MM-dd'T'HH:mm:ss.SSS");
        }
        setItem((prevItem) => ({ ...prevItem, [fieldName]: formattedDate }));
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

    if (!item) {
        return <div>Loading...</div>;
    }

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setItem((prevItem) => ({ ...prevItem, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        setItem(initialValues);
        setChangesMade(false);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(editPath, item, {
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
            <h2>Edit {entityType}</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                {fields.map((field) => {
                    if (field.visible) {
                        return (
                            <div className="list-grid-input-field" key={field.name}>
                                <label className="list-grid-label">{field.label}:</label>
                                {field.type === 'date' ? (
                                    <DatePicker
                                        selected={item[field.name] ? new Date(item[field.name]) : null}
                                        onChange={(date) => handleDateChange(date, field.name)}
                                        dateFormat="yyyy-MM-dd"
                                        placeholderText={`Select ${field.label}`}
                                        className="list-grid-input"
                                    />
                                ) : (
                                    <input
                                        type="text"
                                        name={field.name}
                                        value={item[field.name] || ''}
                                        onChange={handleInputChange}
                                        className="list-grid-input"
                                        placeholder={`Select ${field.label}`}
                                    />
                                )}
                            </div>
                        );
                    }
                    return null;
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

export default AbstractEntityEditPage;
