// ExampleOneToManyChildEntityDefinition.js
import QultivarEntityDefinition from '../QultivarEntityDefinition';
import QultivarEntityField from '../QultivarEntityField'

const name = "OneToMany Child";
const plural = "OneToMany Children";
const baseUrl = "/api/v1/example/onetomanychild";

const fields = [
    // purposefully made the displayOrder out of sequence to test the order of columns in the grid and input on the create/edit pages
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('randomDate', 'Random Date', 'date', true, 2, true, true, false, true, false),
    new QultivarEntityField('notes', 'Notes', 'text', false, 3, true, true, true, true, true),
];

export default class ExampleOneToManyChildEntityDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields
        );
    }
}
