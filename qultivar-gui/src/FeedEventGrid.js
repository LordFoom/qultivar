// FeedEventGrid.js
import React from 'react';
import AbstractEntityGrid from './AbstractEntityGrid';

const FeedEventGrid = ({ email, token, growId, handleFeedEventGridButtonClick }) => {
    const columnHeaders = ['Feed Date', 'Description'];
    const entityAttributes = ['feedDate', 'description'];
    const dateColumns = ['feedDate'];
    const dateFormat = 'yyyy-MM-dd';

    const handleButtonClick = () => {
        handleFeedEventGridButtonClick();
    };

    return (
        <AbstractEntityGrid
            email={email}
            token={token}
            gridHeader="Feed Events"
            entityName="FeedEvent"
            createPath={`/feedEvent/create/${growId}`}
            editPath="/feedEvent/edit"
            deletePath="/api/v1/feed/event"
            selectPath={`/api/v1/feed/event/grow/${growId}`}
            columnHeaders={columnHeaders}
            entityAttributes={entityAttributes}
            dateColumns={dateColumns}
            dateFormat={dateFormat}
            handleButtonClick={handleButtonClick}
        />
    );
};

export default FeedEventGrid;
