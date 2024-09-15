package com.thebest12lines.worldmanager.nbt;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.thebest12lines.worldmanager.util.Converter;
import com.thebest12lines.worldmanager.Main;

public class IntArrayTag extends Tag {
    public final int tagId = 11;
    public void add(int data) {
        this.integers.append(Converter.convertToHex(data,8));
    }
    public void overrideData(int[] data) {
        integers = new StringBuilder();
        for (int i : data) {
            integers.append(Converter.convertToHex(i,8));
        }
    }
    public int getIndex(int index) {
        return this.getIntegers()[index];
    }
    protected StringBuilder integers = new StringBuilder();
    public int[] getIntegers() {
        String hex = integers.toString();
        int[] array = new int[hex.length()/8];
        
        for (int i = 0; i < array.length; i++) {
            if (i*8 <= hex.length()-8) {
                array[i] = Integer.parseInt(hex.substring(i*8, (i*8)+8),16);
            }
            
        }
        
        return array; 
    }
}
