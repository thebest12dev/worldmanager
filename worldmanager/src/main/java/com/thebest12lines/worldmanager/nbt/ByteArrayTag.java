package com.thebest12lines.worldmanager.nbt;

import java.util.ArrayList;

public class ByteArrayTag extends Tag {
    public final int tagId = 7;
    public void add(byte data) {
        this.bytes.append((char) data);
    }
    public void overrideData(byte[] data) {
        bytes = new StringBuilder();
        for (byte b : data) {
            bytes.append((char) b);
        }
    }
    public byte getIndex(int index) {
        return this.bytes.toString().getBytes()[index];
    }
    protected StringBuilder bytes = new StringBuilder();
    public byte[] getBytes() {

        return this.bytes.toString().getBytes();
        
        
    }
}
