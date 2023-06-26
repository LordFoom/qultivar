// FeedEventEntity.js
import AbstractEntity from './AbstractEntity';
import AbstractEntityField from './AbstractEntityField'

const name = "Feed Event";
const plural = "Feed Events";
const baseUrl = "/api/v1/feed/event";
const parentSuffix = "grow";

const fields = [
    new AbstractEntityField('id', 'Id', 'id', false, false, false, false, false, false),
    new AbstractEntityField('feedDate', 'Start Date', 'date', false, true),
    new AbstractEntityField('description', 'Description', 'text', false, true),
    new AbstractEntityField('grow', 'Grow', 'object', false, true, false, false, false, false),
];

class FeedEventEntity extends AbstractEntity {
    constructor(growId) {
        super(
            name,
            plural,
            baseUrl,
            fields,
            parentSuffix,
            growId
        );
    }
}

export default FeedEventEntity;
