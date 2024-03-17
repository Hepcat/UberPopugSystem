package org.uberpopug.event;

public enum EventVersion {

    ONE (1), TWO (2), THREE (3);

    private int numVal;

    EventVersion(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
