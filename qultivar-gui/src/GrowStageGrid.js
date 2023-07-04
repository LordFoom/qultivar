// GrowStageGrid.js
import React from 'react';
import GrowStageDefinition from './GrowStageDefinition'
import QultivarEntityGrid from './QultivarEntityGrid'

const GrowStageGrid = ({ email, token }) => {

    const entityDefinition = new GrowStageDefinition();
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
            readOnly={true}
        />
    );
};

export default GrowStageGrid;
