// GrowDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "Grow";
const plural = "Grows";
const baseUrl = "/api/v1/feed/grow";
const guiUrl = "/grow";

const fields = [
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('name', 'Name', 'text', true, 1, true, true, true, true, false),
    new QultivarEntityField('startDate', 'Start Date', 'date', true, 2, true, true, true, true, true),
    new QultivarEntityField('endDate', 'End Date', 'date', false, 3, true, true, true, true, true),
    new QultivarEntityField('userId', 'User Id', 'text', true, 4),
];

export default class GrowDefinition extends QultivarEntityDefinition {
    constructor(userId) {
        super(
            name,
            plural,
            baseUrl,
            fields,
            guiUrl
        );
        this.userId = userId;
        this.fields.find((field) => field.name === 'userId').defaultValue = userId;
    }

    selectAllPath() {
        return this.baseUrl + '/user/' + this.userId;
    }
}
