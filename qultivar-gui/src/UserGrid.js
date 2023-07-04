// UserGrid.js
import React from 'react';
import UserDefinition from './UserDefinition'
import QultivarEntityGrid from './QultivarEntityGrid'

const UserGrid = ({ email, token }) => {

    const entityDefinition = new UserDefinition();
    return (
        <QultivarEntityGrid
            email={email}
            token={token}
            entityDefinition={entityDefinition}
            readOnly={true}
        />
    );
};

export default UserGrid;
