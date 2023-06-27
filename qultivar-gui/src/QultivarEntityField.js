// QultivarEntityField.js
export default class QultivarEntityField {

    constructor(
        name,
        label,
        type,
        mandatory = true,
        displayOrder = 0,
        renderOnGrid = false,
        renderOnCreate = false,
        editOnCreate = false,
        renderOnEdit = false,
        editOnEdit = false
    ) {
        this.name = name;                       // attribute name of the entity
        this.label = label;                     // attribute label (used for column headers and placeholder text)
        this.type = type;                       // required for formatting and data handling
        this.mandatory = mandatory              // indicate if the field is mandatory
        this.displayOrder = displayOrder;       // display order of the field
        this.renderOnGrid = renderOnGrid;       // render the column in the grid
        this.renderOnCreate = renderOnCreate;   // render the field on create
        this.editOnCreate = editOnCreate;       // render the field on create
        this.renderOnEdit = renderOnEdit;       // render the field on edit
        this.editOnEdit = editOnEdit;           // render the field on edit

        this.isId = false;
        this.isDate = false;
        this.isText = false;

        QultivarEntityField.validateType(type, this);
    }

    static validateType(type, field) {
        switch (type) {
            case 'id':
                field.isId = true;
                break;
            case 'text':
                field.isText = true;
                break;
            case 'date':
                field.isDate = true;
                break;
            default:
                throw new Error('Invalid field type');
        }
    }
}
