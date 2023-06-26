// AbstractEntity.js
import AbstractEntityField from './AbstractEntityField'

export default class AbstractEntity {
    constructor(name, plural, baseUrl, fields, parentSuffix = null, parentId = null) {
        // entity display name
        this.name = name;

        // entity plural name
        this.plural = plural;

        // CRUD operations on a single record by id
        this.baseUrl = baseUrl;

        // the fields must be an instance of the AbstractEntityField class
        if (!Array.isArray(fields) || fields.some(field => !(field instanceof AbstractEntityField))) {
            throw new Error('Fields must be an array of AbstractEntityField objects');
        }
        this.fields = fields;

        // Parent suffix and id is required if the fetchAll is to be filtered.
        // e.g. grows are retrieved by userId, the parentSuffix will be "user" and the parentId = userId 
        //      feedevent are retrieved by growId, the parentSuffix will be "grow" and the parentId = growId 
        //
        // If neither are specified, it will default to the baseUrl
        this.parentSuffix = parentSuffix;
        this.parentId = parentId;

        // fetches all the records.  This is seperate to the CRUD operations which work on a single record at a time.
        this.selectAllUrl = baseUrl;
        if (parentSuffix !== null && parentId !== null) {
            this.fetchPath = this.fetchPath + "/" + parentSuffix + "/" + parentId;
        } else {
            if (parentSuffix === null || parentId === null) {
                throw new Error('Both the parentSuffix and the parentId must be supplied');
            }
        }

    }

    getParentIdName() {
        return this.parentSuffix + "Id";
    }

    includeParentId() {
        return this.parentId !== null;
    }

    createPath() {
        return this.baseUrl;
    }

    editPath(id) {
        return this.baseUrl + "/" + id;
    }

    deletePath(id) {
        return this.baseUrl + "/" + id;
    }

    selectPath(id) {
        return this.baseUrl + "/" + id;
    }

    selectAllPath() {
        return this.selectAllUrl;
    }
}
