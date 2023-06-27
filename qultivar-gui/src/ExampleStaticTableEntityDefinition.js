// ExampleStaticTableEntityDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "Static Data";
const plural = "Static Datum";
const baseUrl = "/api/v1/example/statictable";

const fields = [
    // purposefully made the displayOrder out of sequence to test the order of columns in the grid and input on the create/edit pages
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('name', 'Name', 'text', true, 2, true, true, false, true, false),
    new QultivarEntityField('description', 'Description', 'text', true, 3, true, true, false, true, false),
    new QultivarEntityField('rowSequence', 'Row Sequence', 'text', true, 1, true, true, false, true, false),
];

export default class ExampleStaticTableEntityDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields
        );
    }
}
