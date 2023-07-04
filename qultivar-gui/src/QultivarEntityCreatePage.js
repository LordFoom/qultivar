// QultivarEntityCreatePage.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './QultivarEntityGrid.css';

const QultivarEntityCreatePage = ({ email, token, entityDefinition }) => {
    const navigate = useNavigate();
    const [changesMade, setChangesMade] = useState(false);
    const [formData, setFormData] = useState({});

    useEffect(() => {
        const initialFormData = {};
        entityDefinition.fields.forEach((field) => {
            initialFormData[field.name] = field.defaultValue || '';
        });
        setFormData(initialFormData);
        setChangesMade(false);
    }, [entityDefinition]);

    const resetForm = () => {
        const initialFormData = {};
        entityDefinition.fields.forEach((field) => {
            initialFormData[field.name] = field.defaultValue || '';
        });
        setFormData(initialFormData);
        setChangesMade(false);
    };

    const handleDateChange = (date, fieldName) => {
        setFormData((prevFormData) => ({ ...prevFormData, [fieldName]: date !== null ? date : null }));
        setChangesMade(true);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
        setChangesMade(true);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedFormData = { ...formData };
            entityDefinition.fields.forEach((field) => {
                if (field.isId) {
                    formattedFormData[field.name] = null;
                }
                if (field.isDate) {
                    if (formData[field.name] !== null && formData[field.name] !== undefined && formData[field.name] !== '') {
                        formattedFormData[field.name] = format(formData[field.name], "yyyy-MM-dd'T'HH:mm:ss.SSS");
                    } else {
                        formattedFormData[field.name] = null;
                    }
                }
            });

            await axios.post(entityDefinition.createPath(), formattedFormData, {
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
            <h2>Create {entityDefinition.name}</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                {entityDefinition.fields
                    .filter((field) => field.renderOnCreate)
                    .map((field) => {
                        return (
                            <div className="list-grid-input-field" key={field.name}>
                                <label className="list-grid-label">{field.label}:</label>
                                {field.type === 'date' ? (
                                    <DatePicker
                                        selected={formData[field.name]}
                                        onChange={(date) => handleDateChange(date, field.name)}
                                        value={formData[field.name] || ''}
                                        placeholderText={`Select ${field.label}`}
                                        className="list-grid-input"
                                        dateFormat="yyyy-MM-dd"
                                        disabled={!field.editOnCreate}
                                    />
                                ) : (
                                    <input
                                        type="text"
                                        name={field.name}
                                        value={formData[field.name]}
                                        onChange={handleInputChange}
                                        placeholder={`Select ${field.label}`}
                                        className="list-grid-input"
                                        disabled={!field.editOnCreate}
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

export default QultivarEntityCreatePage;
