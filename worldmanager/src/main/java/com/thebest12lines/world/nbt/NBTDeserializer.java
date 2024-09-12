package com.thebest12lines.world.nbt;

import com.thebest12lines.util.Converter;

public class NBTDeserializer {
    private static String toString(String nbtHex) {
        String hexInput = nbtHex; // Replace with your desired hexadecimal input
        hexInput = hexInput.replaceAll("\\s", "");
        // Ensure the input length is even
       // System.out.println(hexInput);
        if (hexInput.length() % 2 != 0) {
            System.out.println("Invalid input: Hexadecimal string must have an even number of characters.");
            return null;
        }

        StringBuilder result = new StringBuilder();

        // Process pairs of characters
        for (int i = 0; i < hexInput.length(); i += 2) {
            String hexPair = hexInput.substring(i, i + 2);
            try {
                int decimalValue = Integer.parseInt(hexPair, 16);
                char asciiChar = (char) decimalValue;
                result.append(asciiChar);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Not a valid hexadecimal string.");
                return null;
            }
        }

        return result.toString();
}
    public static CompoundTag fromNBT(String nbt) throws TagException {
        String hex = Converter.convertToHex(nbt);
        CompoundTag tag = new CompoundTag();
        System.out.println(hex.substring(0, 2));
        int tagType = Integer.parseInt(hex.substring(0, 2),16);
        if (tagType == 10) {
            if (hex.length() > 6) {
                System.out.println(hex.substring(2, 6));
                int tagNameLength = Integer.parseInt(hex.substring(3, 6),16);
                tag.name = toString(hex.substring(6,(7+(tagNameLength*2))-1));
                if (hex.substring((7+(tagNameLength*2))+1,7+(tagNameLength*2)+1) == "00") {
                    System.out.println(hex.substring((7+(tagNameLength*2))-1,7+(tagNameLength*2)+1));
                    System.out.println(1);
                } else {
                    System.err.println(21);
                }
            }
            
        } else {
            throw new TagException("Given input is not valid NBT");
        }
        return tag;
        
    }
    public static StringTag fromNBTRaw(String hex) throws TagException {
        if (hex.startsWith("08")) {
            StringTag tag = new StringTag();
            if (hex.length() > 6) {
                int tagNameLength = Integer.parseInt(hex.substring(3, 6),16);
                tag.name = toString(hex.substring(6,(7+(tagNameLength*2))-1));
                int tagValueLength = Integer.parseInt(hex.substring(7+(tagNameLength*2),7+(tagNameLength*2)+3),16);
                System.out.println(tagValueLength);
            } else {
                throw new TagException("Given input is not valid NBT (StringTag)");
            }
            
            return tag;
        } else {
            throw new TagException("Given input is not valid NBT (StringTag)");
        }
        
    } 
}
