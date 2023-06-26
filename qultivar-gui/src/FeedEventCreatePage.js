// FeedEventCreatePage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import AbstractEntityCreatePage from './AbstractEntityCreatePage';
import FeedEventEntity from './FeedEventEntity';

const FeedEventCreatePage = ({ email, token }) => {
    const { growId } = useParams();

    const entityDefinition = new FeedEventEntity(growId);

    return (
        <AbstractEntityCreatePage email={email} token={token} entityDefinition={entityDefinition} />
    );
};

export default FeedEventCreatePage;
