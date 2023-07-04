// QultivarEntityEditPage.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import { format, parseISO } from 'date-fns';
import 'react-datepicker/dist/react-datepicker.css';
import './QultivarEntityGrid.css';

const QultivarEntityEditPage = ({ email, token, entityDefinition, itemId, gridComponent }) => {
    const [item, setItem] = useState(null);
    const [initialValues, setInitialValues] = useState(null);
    const [changesMade, setChangesMade] = useState(false);
    const [isLoading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchItem = async () => {
            try {
                const response = await axios.get(entityDefinition.selectPath(itemId), {
                    headers: { Authorization: `Bearer ${token}` },
                });
                const initialItem = response.data;
                setItem(initialItem);
                setInitialValues(initialItem);
                setLoading(false);
            } catch (error) {
                console.log(error);
            }
        };

        fetchItem();
    }, [itemId, token, entityDefinition]);

    const resetForm = () => {
        setItem(initialValues);
        setChangesMade(false);
    };

    const handleDateChange = (date, fieldName) => {
        var formattedDate = null;
        if (date) {
            formattedDate = format(date, "yyyy-MM-dd");
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

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setItem((prevItem) => ({ ...prevItem, [name]: value }));
        setChangesMade(true);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedItem = { ...item };
            entityDefinition.fields.forEach((field) => {
                if (field.isDate) {
                    if (item[field.name] !== null && item[field.name] !== undefined && item[field.name] !== '') {
                        formattedItem[field.name] = format(parseISO(item[field.name]), "yyyy-MM-dd'T'HH:mm:ss.SSS");
                    } else {
                        formattedItem[field.name] = null;
                    }
                }
            });

            await axios.put(entityDefinition.updatePath(itemId), formattedItem, {
                headers: { Authorization: `Bearer ${token}` },
            });
            setChangesMade(false);
        } catch (error) {
            console.log(error);
        }
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="list-grid-container">
            <h2>Edit {entityDefinition.name}</h2>
            <form className="list-grid-form" onSubmit={handleSubmit}>
                {entityDefinition.fields
                    .filter((field) => field.renderOnEdit)
                    .map((field) => {
                        return (
                            <div className="list-grid-input-field" key={field.name}>
                                <label className="list-grid-label">{field.label}:</label>
                                {field.type === 'date' ? (
                                    <DatePicker
                                        selected={item[field.name] ? parseISO(item[field.name]) : null}
                                        onChange={(date) => handleDateChange(date, field.name)}
                                        value={item[field.name] ? format(parseISO(item[field.name]), 'yyyy-MM-dd') : ''}
                                        placeholderText={`Select ${field.label}`}
                                        className="list-grid-input"
                                        dateFormat="yyyy-MM-dd"
                                        disabled={!field.editOnEdit}
                                    />
                                ) : (
                                    <input
                                        type="text"
                                        name={field.name}
                                        value={item[field.name]}
                                        onChange={handleInputChange}
                                        placeholder={`Select ${field.label}`}
                                        className="list-grid-input"
                                        disabled={!field.editOnEdit}
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
                {/* render the grid component if there is one */}
                {gridComponent && (
                    <>
                        <div className="list-grid-separator"></div>
                        <div>{gridComponent}</div>
                    </>
                )}
                <div className="list-grid-button-row">
                    <button onClick={handleExit} className="list-grid-button">
                        Exit
                    </button>
                </div>
            </form>
        </div>
    );
};

export default QultivarEntityEditPage;
