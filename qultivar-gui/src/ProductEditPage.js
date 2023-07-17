// ProductEditPage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import QultivarEntityEditPage from './QultivarEntityEditPage';
import ProductDefinition from './ProductDefinition';

const ProductEditPage = ({ email, token }) => {
    const { id } = useParams();
    const entityDefinition = new ProductDefinition();
    return (
        <QultivarEntityEditPage email={email} token={token} entityDefinition={entityDefinition} itemId={id} />
    );
};

export default ProductEditPage;
