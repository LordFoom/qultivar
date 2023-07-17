// ProductGrid.js
import React from 'react';
import ProductDefinition from './ProductDefinition'
import QultivarEntityGrid from './QultivarEntityGrid'

const ProductGrid = ({ email, token }) => {

    const entityDefinition = new ProductDefinition();
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
        />
    );
};

export default ProductGrid;
