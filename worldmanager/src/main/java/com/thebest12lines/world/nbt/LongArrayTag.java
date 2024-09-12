package com.thebest12lines.world.nbt;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.thebest12lines.Main;
import com.thebest12lines.util.Converter;

public class LongArrayTag extends Tag {
    public final int tagId = 12;
    public void add(long data) {
        this.integers.append(Converter.convertToHex(data,16));
    }
    public void overrideData(long[] data) {
        integers = new StringBuilder();
        for (long i : data) {
            integers.append(Converter.convertToHex(i,16));
        }
    }
    public long getIndex(int index) {
        return this.getIntegers()[index];
    }
    protected StringBuilder integers = new StringBuilder();
    public long[] getIntegers() {
        String hex = integers.toString();
        long[] array = new long[hex.length()/16];
        
        for (int i = 0; i < array.length; i++) {
            if (i*16 <= hex.length()-16) {
                array[i] = Long.parseLong(hex.substring(i*16, (i*16)+16),16);
            }
            
        }
        
        return array; 
    }
}
