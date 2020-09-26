package by.bsuir.pokos.message;

import by.bsuir.pokos.utils.Actions;
import java.io.Serializable;

public class Message implements Serializable {

    private String message;
    private Actions action;
    private Object structure;
    static final long serialVersionUID = 5832063776451490808L;

    public Message(String message, Actions action, Object structure) {
        this.message = message;
        this.action = action;
        this.structure = structure;
    }

    public Message(String message, Actions action) {
        this.message = message;
        this.action = action;
    }

    
    public Message(Actions action) {
        this.action = action;
    }

    public Message(Object structure) {
        this.structure = structure;
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public Object getStructure() {
        return structure;
    }

    public void setStructure(Object structure) {
        this.structure = structure;
    }
}

