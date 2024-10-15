package com.thebest12lines.worldmanager.util;

import com.thebest12lines.worldmanager.annotation.CoreClass;

/**
 * The class to store argument metadata for the <code>CommandArgsParser</code> class.
 * @author thebest12lines
 */
@CoreClass
public class ArgumentMetadata {
    private String value;
    private int index;

    public ArgumentMetadata(String value, int index) {
        this.value = value;
        this.index = index;
    }

    /**
     * Returns the value of the <code>ArgumentMetadata</code>.
     * @return The value of the <code>ArgumentMetadata</code> class.
     */
    public String getValue() {
        return value;
    }
    /**
     * Returns the array index of the <code>ArgumentMetadata</code>.
     * @return The array index of the <code>ArgumentMetadata</code> class.
     */
    public int getIndex() {
        return index;
    }
}
