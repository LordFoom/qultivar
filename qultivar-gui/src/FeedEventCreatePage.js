// FeedEventCreatePage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import QultivarEntityCreatePage from './QultivarEntityCreatePage';
import FeedEventDefinition from './FeedEventDefinition';

const FeedEventCreatePage = ({ email, token }) => {
    const { growId } = useParams();
    const entityDefinition = new FeedEventDefinition(growId);
    return (
        <QultivarEntityCreatePage email={email} token={token} entityDefinition={entityDefinition} />
    );
};

export default FeedEventCreatePage;
