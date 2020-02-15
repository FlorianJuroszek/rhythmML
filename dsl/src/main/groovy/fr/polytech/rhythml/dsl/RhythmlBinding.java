package fr.polytech.rhythml.dsl;

import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Map;

public class RhythmlBinding extends Binding {
    private Script script;

    private RhytmlModel model;

    public RhythmlBinding() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public RhythmlBinding(Map variables) {
        super(variables);
    }

    public RhythmlBinding(Script script) {
        super();
        this.script = script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setRhythmlModel(RhytmlModel model) {
        this.model = model;
    }

    public Object getVariable(String name) {
        return super.getVariable(name);
    }

    public void setVariable(String name, Object value) {
        super.setVariable(name, value);
    }

    public RhytmlModel getRhythmlModel() {
        return this.model;
    }
}
