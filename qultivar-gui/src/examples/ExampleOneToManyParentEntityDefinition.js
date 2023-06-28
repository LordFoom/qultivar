// ExampleOneToManyParentEntityDefinition.js
import QultivarEntityDefinition from '../QultivarEntityDefinition';
import QultivarEntityField from '../QultivarEntityField'

const name = "OneToMany Parent";
const plural = "OneToMany Parents";
const baseUrl = "/api/v1/example/onetomanyparent";

const fields = [
    // purposefully made the displayOrder out of sequence to test the order of columns in the grid and input on the create/edit pages
    new QultivarEntityField('id', 'Id', 'id', true, 1),
    new QultivarEntityField('name', 'Name', 'text', true, 2, true, true, true, true, false),
    new QultivarEntityField('description', 'Description', 'text', true, 3, true, true, true, true, true),
    new QultivarEntityField('firstDate', 'First Date', 'date', true, 4, true, true, true, true, true),
];

export default class ExampleOneToManyParentEntityDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields
        );
    }
}
