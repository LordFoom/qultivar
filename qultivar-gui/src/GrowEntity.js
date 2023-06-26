// GrowEntity.js
import AbstractEntity from './AbstractEntity';
import AbstractEntityField from './AbstractEntityField'

const name = "Grow";
const plural = "Grows";
const baseUrl = "/api/v1/feed/grow";
const parentSuffix = "user";

const fields = [
    new AbstractEntityField('id', 'Id', 'id', false, false, false, false, false, false),
    new AbstractEntityField('name', 'Name', 'text', true, true),
    new AbstractEntityField('startDate', 'Start Date', 'date', true, true),
    new AbstractEntityField('endDate', 'End Date', 'date', false, true),
    new AbstractEntityField('userId', 'User Id', 'parent', false, true, false, false, false, false),
];

export default class GrowEntity extends AbstractEntity {
    constructor(userId) {
        super(
            name,
            plural,
            baseUrl,
            fields,
            parentSuffix,
            userId
        );
    }
}
