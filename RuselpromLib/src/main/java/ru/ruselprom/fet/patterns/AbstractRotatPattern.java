package ru.ruselprom.fet.patterns;

public abstract class AbstractRotatPattern {
    
    protected String refAxisName;

    public AbstractRotatPattern(String refAxisName) {
        this.refAxisName = refAxisName;
    }

    public String getRefAxisName() {
        return refAxisName;
    }

    public void setRefAxisName(String refAxisName) {
        this.refAxisName = refAxisName;
    }
}
