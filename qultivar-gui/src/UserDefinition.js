// UserDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "User";
const plural = "Users";
const baseUrl = "/api/v1/user";

const fields = [
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('name', 'Name', 'text', true, 1, true),
    new QultivarEntityField('email', 'Email', 'text', false, 2, true),
];

export default class UserDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields
        );
    }
}
