// ExampleStaticTableGrid.js
import React from 'react';
import QultivarEntityGrid from '../QultivarEntityGrid'
import ExampleOneToManyParentEntityDefinition from './ExampleOneToManyParentEntityDefinition';

const ExampleOneToManyParentGrid = ({ email, token }) => {
    const entityDefinition = new ExampleOneToManyParentEntityDefinition();
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
        />
    );
};

export default ExampleOneToManyParentGrid;
