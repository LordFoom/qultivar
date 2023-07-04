// GrowEditPage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import GrowDefinition from './GrowDefinition';
import QultivarEntityEditPage from './QultivarEntityEditPage';
import FeedEventGrid from './FeedEventGrid';

const GrowEditPage = ({ email, token }) => {
    const { id } = useParams();
    const entityDefinition = new GrowDefinition();
    const gridComponent = <FeedEventGrid email={email} token={token} growId={id} />;
    return (
        <QultivarEntityEditPage email={email} token={token} entityDefinition={entityDefinition} itemId={id} gridComponent={gridComponent} />
    );
};

export default GrowEditPage;
