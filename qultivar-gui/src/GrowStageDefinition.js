// GrowStageDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "Grow Stage";
const plural = "Grow Stages";
const baseUrl = "/api/v1/feed/growstage";

const fields = [
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('name', 'Name', 'text', true, 1, true),
    new QultivarEntityField('description', 'Description', 'text', false, 2, true),
    new QultivarEntityField('stageSequence', 'Sequence', 'text', true, 3, true),
];

export default class GrowStageDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields
        );
    }
}
