// AbstractEntityCreatePage2.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './ListGrid.css';

const AbstractEntityCreatePage = ({ email, token, entityDefinition }) => {
    const navigate = useNavigate();
    const [changesMade, setChangesMade] = useState(false);
    const [isLoading, setLoading] = useState(true);
    const [parentData, setParentData] = useState({});
    const [formData, setFormData] = useState({});

    useEffect(() => {
        const fetchParentData = async () => {
            if (entityDefinition.parentSuffix === "grow") {
                const fetchPath = "/api/v1/feed/grow/" + entityDefinition.parentId;
                try {
                    const response = await axios.get(fetchPath, {
                        headers: { Authorization: `Bearer ${token}` },
                    });
                    setParentData(response.data);
                    setLoading(false);
                } catch (error) {
                    throw new Error(error);
                }
            } else {
                setLoading(false);
            }
        };

        fetchParentData();
    }, [entityDefinition.parentSuffix, entityDefinition.parentId, token]);

    useEffect(() => {
        const initialFormData = {};
        entityDefinition.fields.forEach((field) => {
            if (field.isObject) {
                if (entityDefinition.parentSuffix === "grow") {
                    initialFormData[field.name] = parentData;
                }
            } else {
                initialFormData[field.name] = '';
            }
        });
        setFormData(initialFormData);
    }, [entityDefinition.fields, entityDefinition.parentSuffix, parentData]);

    const handleDateChange = (date, fieldName) => {
        setFormData(prevFormData => ({ ...prevFormData, [fieldName]: date !== null ? date : null }));
        setChangesMade(true);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevFormData) => ({ ...prevFormData, [name]: value }));
        setChangesMade(true);
    };

    const resetForm = () => {
        const initialFormData = {};
        entityDefinition.fields.forEach((field) => {
            initialFormData[field.name] = '';
        });
        setFormData(initialFormData);
        setChangesMade(false);
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
                    if (formData[field.name] !== null && formData[field.name] !== undefined && formData[field.name] !== "") {
                        formattedFormData[field.name] = format(formData[field.name], "yyyy-MM-dd'T'HH:mm:ss.SSS");
                    } else {
                        formattedFormData[field.name] = null;
                    }
                }
                if (field.isObject) {
                    formattedFormData[field.name] = parentData;
                }
            });

            if (entityDefinition.includeParentId()) {
                formattedFormData[entityDefinition.getParentIdName()] = entityDefinition.parentId;
            }

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

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="list-grid-container">
            <h2>Create {entityDefinition.name}</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                {entityDefinition.fields.map((field) => {
                    if (!field.renderOnCreate) {
                        return null;
                    }

                    return (
                        <div className="list-grid-input-field" key={field.name}>
                            <label className="list-grid-label">{field.label}:</label>
                            {field.type === 'date' ? (
                                <DatePicker
                                    selected={formData[field.name]}
                                    onChange={date => handleDateChange(date, field.name)}
                                    value={formData[field.name] || ''}
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

export default AbstractEntityCreatePage;
