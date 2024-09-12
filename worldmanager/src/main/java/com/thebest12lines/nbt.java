package com.thebest12lines;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.thebest12lines.world.nbt.*;

public class nbt {
    public static void main(String[] args) throws TagException {
       
    
        CompoundTag tag = new CompoundTag();
        tag.name = "name";
        
        

        
        
       // System.out.println(intArrayTag.getIntegers()[0]);
        String string = NBTSerializer.toNBT(tag);
        CompoundTag tag2 = NBTDeserializer.fromNBT(string);
        System.out.println(tag2.name);

        try {
           Files.write(new File("nbt.dat").toPath(),NBTSerializer.toNBT(tag).getBytes());
       } catch (IOException e) {
          // TODO Auto-generated catch block
           e.printStackTrace();
       }
    }
}
