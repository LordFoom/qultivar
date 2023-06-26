// AbstractEntityField.js
export default class AbstractEntityField {

    constructor(name, label, type, renderInGrid = true, mandatory = true, renderOnCreate = true, renderOnEdit = true, editOnCreate = true, editOnEdit = true) {
        this.name = name;                       // attribute name of the entity
        this.label = label;                     // attribute label (used for column headers and placeholder text)
        this.type = type;                       // required for formatting and data handling
        this.renderInGrid = renderInGrid;       // render the column in the grid
        this.mandatory = mandatory              // indicate if the field is mandatory
        this.renderOnCreate = renderOnCreate;   // render the field on create
        this.renderOnEdit = renderOnEdit;       // render the field on edit
        this.editOnCreate = editOnCreate;       // render the field on create
        this.editOnEdit = editOnEdit;           // render the field on edit

        this.isId = false;
        this.isLong = false;
        this.isDate = false;
        this.isText = false;
        this.isObject = false;

        AbstractEntityField.validateType(type, this);
    }

    static validateType(type, field) {
        switch (type) {
            case 'id':
                field.isId = true;
                field.isLong = true;
                break;
            case 'text':
                field.isText = true;
                break;
            case 'date':
                field.isDate = true;
                break;
            case 'object':
                field.isObject = true;
                break;
            default:
                throw new Error('Invalid field type');
        }
    }
}
