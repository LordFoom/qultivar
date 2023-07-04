// FeedEventGrid.js
import React from 'react';
import FeedEventDefinition from './FeedEventDefinition'
import QultivarEntityGrid from './QultivarEntityGrid';

const FeedEventGrid = ({ email, token, growId }) => {

    const entityDefinition = new FeedEventDefinition(growId);
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
        />
    );

};

export default FeedEventGrid;
