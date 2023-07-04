// QultivarEntityDefinition.js
import QultivarEntityField from './QultivarEntityField'

export default class QultivarEntityDefinition {
    constructor(name, plural, baseUrl, fields, guiUrl) {
        // entity display name
        this.name = name;

        // entity plural name
        this.plural = plural;

        // base url for all CRUD operations sent to the api-service (single record operations)
        this.baseUrl = baseUrl;

        // all fields must be instances of the QultivarEntityField class
        if (!Array.isArray(fields) || fields.some(field => !(field instanceof QultivarEntityField))) {
            throw new Error('Fields must be an array of QultivarEntityField objects');
        }
        this.fields = fields;

        // this is required for the create and edit pages and the route is mapped in the App.js file
        this.guiUrl = guiUrl;

        this.renderOnGridFields = fields
            .filter((field) => field.renderOnGrid)
            .sort((a, b) => a.displayOrder - b.displayOrder)
            .map((field) => field.name);

        this.renderOnCreateFields = fields
            .filter((field) => field.renderOnCreate)
            .sort((a, b) => a.displayOrder - b.displayOrder)
            .map((field) => field.name);

        this.renderOnEditFields = fields
            .filter((field) => field.renderOnEdit)
            .sort((a, b) => a.displayOrder - b.displayOrder)
            .map((field) => field.name);
    }

    selectAllPath() {
        return this.baseUrl;
    }

    selectPath(id) {
        return this.baseUrl + "/" + id;
    }

    createPath() {
        return this.baseUrl;
    }

    updatePath(id) {
        return this.baseUrl + "/" + id;
    }

    deletePath(id) {
        return this.baseUrl + "/" + id;
    }

    guiCreatePath() {
        return this.guiUrl + "/create";
    }

    guiEditPath(entityId) {
        return this.guiUrl + "/edit/" + entityId;
    }
}
