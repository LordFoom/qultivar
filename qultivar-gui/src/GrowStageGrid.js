// GrowStageGrid.js
import React from 'react';
import AbstractStaticEntityGrid from './AbstractStaticEntityGrid'

const GrowStageGrid = ({ email, token }) => {

    const columnHeaders = ['Sequence', 'Name', 'Description'];
    const entityAttributes = ['stageSequence', 'name', 'description'];
    const dateColumns = [];

    return (
        <AbstractStaticEntityGrid
            email={email}
            token={token}
            gridHeader="Grow Stages"
            entityName="GrowStage"
            selectPath={`/api/v1/feed/growstage`}
            columnHeaders={columnHeaders}
            entityAttributes={entityAttributes}
            dateColumns={dateColumns}
        />
    );
};

export default GrowStageGrid;
