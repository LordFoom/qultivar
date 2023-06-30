// ExampleStaticTableGrid.js
import React from 'react';
import QultivarEntityGrid from '../QultivarEntityGrid'
import ExampleStaticDataEntityDefinition from './ExampleStaticTableEntityDefinition';

const ExampleStaticTableGrid = ({ email, token }) => {
    const entityDefinition = new ExampleStaticDataEntityDefinition();
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
            readOnly={true}
        />
    );
};

export default ExampleStaticTableGrid;
