// ProductCreatePage.js
import React from 'react';
import QultivarEntityCreatePage from './QultivarEntityCreatePage';
import ProductDefinition from './ProductDefinition';

const ProductCreatePage = ({ email, token }) => {
    const entityDefinition = new ProductDefinition();
    return (
        <QultivarEntityCreatePage email={email} token={token} entityDefinition={entityDefinition} />
    );
};

export default ProductCreatePage;
