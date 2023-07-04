// FeedEventDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "Feed Event";
const plural = "Feed Events";
const baseUrl = "/api/v1/feed/event";
const guiUrl = "/feedEvent";

const fields = [
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('feedDate', 'Feed Date', 'date', true, 1, true, true, true, true, true),
    new QultivarEntityField('description', 'Description', 'text', false, 2, true, true, true, true, true),
    new QultivarEntityField('growId', 'Grow Id', 'text', true, 3, false),
];

export default class FeedEventDefinition extends QultivarEntityDefinition {
    constructor(growId) {
        super(
            name,
            plural,
            baseUrl,
            fields,
            guiUrl
        );
        this.growId = growId;
        this.fields.find((field) => field.name === 'growId').defaultValue = growId;
    }

    selectAllPath() {
        return this.baseUrl + '/grow/' + this.growId;
    }

    guiCreatePath() {
        return this.guiUrl + "/create/" + this.growId;
    }
}
