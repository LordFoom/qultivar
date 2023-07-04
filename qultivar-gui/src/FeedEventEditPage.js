// FeedEventEditPage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import QultivarEntityEditPage from './QultivarEntityEditPage';
import FeedEventDefinition from './FeedEventDefinition';

const FeedEventEditPage = ({ email, token }) => {
    const { id } = useParams();
    const entityDefinition = new FeedEventDefinition();
    return (
        <QultivarEntityEditPage email={email} token={token} entityDefinition={entityDefinition} itemId={id} />
    );
};

export default FeedEventEditPage;
