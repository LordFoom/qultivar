// ProductDefinition.js
import QultivarEntityDefinition from './QultivarEntityDefinition';
import QultivarEntityField from './QultivarEntityField'

const name = "Product";
const plural = "Products";
const baseUrl = "/api/v1/feed/product";
const guiUrl = "/product";


const fields = [
    new QultivarEntityField('id', 'Id', 'id', true, 0),
    new QultivarEntityField('name', 'Name', 'text', true, 1, true, true, true, true, true),
    new QultivarEntityField('description', 'Description', 'text', false, 2, true, true, true, true, true),
    new QultivarEntityField('manufacturer', 'Manufacturer', 'text', true, 3, true, true, true, true, true),
    new QultivarEntityField('category', 'Category', 'text', true, 4, true, true, true, true, true),
];

export default class ProductDefinition extends QultivarEntityDefinition {
    constructor() {
        super(
            name,
            plural,
            baseUrl,
            fields,
            guiUrl
        );
    }
}
