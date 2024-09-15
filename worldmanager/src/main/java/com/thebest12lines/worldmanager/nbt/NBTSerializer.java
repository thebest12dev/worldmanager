package com.thebest12lines.worldmanager.nbt;

import com.thebest12lines.worldmanager.util.Converter;

public class NBTSerializer {
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
    public static String toNBT(CompoundTag tag) {
        StringBuilder builder = new StringBuilder();
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        
        if (tag.children.size() == 0) {
            builder.append("00");
            return toString(builder.toString());
            
        } else if (tag.children.size() > 0) {
            
            for (int i = 0; i < tag.children.size(); i++) {
                
                Tag childTag = tag.children.get(i);
                
                if (childTag instanceof StringTag) {
                    builder.append(toNBTRaw((StringTag) childTag));
                } else if (childTag instanceof CompoundTag) {
                    builder.append(toNBTRaw((CompoundTag) childTag));
                } else if (childTag instanceof ByteTag) {
                    builder.append(toNBTRaw((ByteTag) childTag));
                } else if (childTag instanceof ShortTag) {
                    builder.append(toNBTRaw((ShortTag) childTag));
                } else if (childTag instanceof IntTag) {
                    builder.append(toNBTRaw((IntTag) childTag));
                } else if (childTag instanceof LongTag) {
                    builder.append(toNBTRaw((LongTag) childTag));
                } else if (childTag instanceof FloatTag) {
                    builder.append(toNBTRaw((FloatTag) childTag));
                } else if (childTag instanceof DoubleTag) {
                    builder.append(toNBTRaw((DoubleTag) childTag));
                } else if (childTag instanceof ByteArrayTag) {
                    builder.append(toNBTRaw((ByteArrayTag) childTag));
                } else if (childTag instanceof IntArrayTag) {
                    builder.append(toNBTRaw((IntArrayTag) childTag));
                } else if (childTag instanceof LongArrayTag) {
                    builder.append(toNBTRaw((LongArrayTag) childTag));
                } else if (childTag instanceof ListTag) {
                    builder.append(toNBTRaw((ListTag) childTag));
                }
                
                
            }
            builder.append("00");
        }
        return toString(builder.toString());
    }
    private static Object toNBTRaw(CompoundTag tag) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        if (tag.children.size() == 0) {
            builder.append("00");
            return toString(builder.toString());
            
        } else if (tag.children.size() > 0) {
            
            for (int i = 0; i < tag.children.size(); i++) {
                
                Tag childTag = tag.children.get(i);
                
                if (childTag instanceof StringTag) {
                    builder.append(toNBTRaw((StringTag) childTag));
                } else if (childTag instanceof CompoundTag) {
                    builder.append(toNBTRaw((CompoundTag) childTag));
                } else if (childTag instanceof ByteTag) {
                    builder.append(toNBTRaw((ByteTag) childTag));
                } else if (childTag instanceof ShortTag) {
                    builder.append(toNBTRaw((ShortTag) childTag));
                } else if (childTag instanceof IntTag) {
                    builder.append(toNBTRaw((IntTag) childTag));
                } else if (childTag instanceof LongTag) {
                    builder.append(toNBTRaw((LongTag) childTag));
                } else if (childTag instanceof FloatTag) {
                    builder.append(toNBTRaw((FloatTag) childTag));
                } else if (childTag instanceof DoubleTag) {
                    builder.append(toNBTRaw((DoubleTag) childTag));
                } else if (childTag instanceof ByteArrayTag) {
                    builder.append(toNBTRaw((ByteArrayTag) childTag));
                } else if (childTag instanceof IntArrayTag) {
                    builder.append(toNBTRaw((IntArrayTag) childTag));
                } else if (childTag instanceof LongArrayTag) {
                    builder.append(toNBTRaw((LongArrayTag) childTag));
                } else if (childTag instanceof ListTag) {
                    builder.append(toNBTRaw((ListTag) childTag));
                }

                
            }
            builder.append("00");
        }
       
        
        return builder.toString();
    }
    private static String toNBTRaw(StringTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));;
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value.length(),4));
        builder.append(Converter.convertToHex(tag.value));
        
        return builder.toString();
    }
    private static String toNBTRaw(ByteTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,2));
        
        return builder.toString();
    }
    private static String toNBTRaw(ShortTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,4));
        
        return builder.toString();
    }
    private static String toNBTRaw(IntTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,8));
        
        return builder.toString();
    }
    private static String toNBTRaw(LongTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,16));
        
        return builder.toString();
    }
    private static String toNBTRaw(FloatTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,8));
        
        return builder.toString();
    }
    private static String toNBTRaw(DoubleTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.value,16));
        
        return builder.toString();
    }
    private static String toNBTRaw(ByteArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.getBytes().length,8));
        for (byte b: tag.getBytes()) {
            builder.append(Converter.convertToHex(b,2));
        }
        
        
        return builder.toString();
    }
    private static String toNBTRaw(IntArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
      //  System.out.println(tag.getIntegers().length);
        builder.append(Converter.convertToHex(tag.getIntegers().length,8));
        for (int i: tag.getIntegers()) {
            builder.append(Converter.convertToHex(i,8));
        }
        
        
        return builder.toString();
    }
    private static String toNBTRaw(LongArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagId,2));
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
      //  System.out.println(tag.getIntegers().length);
        builder.append(Converter.convertToHex(tag.getIntegers().length,8));
        for (long i: tag.getIntegers()) {
            builder.append(Converter.convertToHex(i,16));
            
        }
        
        
        return builder.toString();
    }
    private static Object toNBTRawList(CompoundTag tag) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        
        if (tag.children.size() == 0) {
            builder.append("00");
            return toString(builder.toString());
            
        } else if (tag.children.size() > 0) {
            
            for (int i = 0; i < tag.children.size(); i++) {
                
                Tag childTag = tag.children.get(i);
                
                if (childTag instanceof StringTag) {
                    builder.append(toNBTRaw((StringTag) childTag));
                } else if (childTag instanceof CompoundTag) {
                    builder.append(toNBTRaw((CompoundTag) childTag));
                } else if (childTag instanceof ByteTag) {
                    builder.append(toNBTRaw((ByteTag) childTag));
                } else if (childTag instanceof ShortTag) {
                    builder.append(toNBTRaw((ShortTag) childTag));
                } else if (childTag instanceof IntTag) {
                    builder.append(toNBTRaw((IntTag) childTag));
                } else if (childTag instanceof LongTag) {
                    builder.append(toNBTRaw((LongTag) childTag));
                } else if (childTag instanceof FloatTag) {
                    builder.append(toNBTRaw((FloatTag) childTag));
                } else if (childTag instanceof DoubleTag) {
                    builder.append(toNBTRaw((DoubleTag) childTag));
                } else if (childTag instanceof ByteArrayTag) {
                    builder.append(toNBTRaw((ByteArrayTag) childTag));
                } else if (childTag instanceof IntArrayTag) {
                    builder.append(toNBTRaw((IntArrayTag) childTag));
                } else if (childTag instanceof LongArrayTag) {
                    builder.append(toNBTRaw((LongArrayTag) childTag));
                } else if (childTag instanceof ListTag) {
                    builder.append(toNBTRaw((ListTag) childTag));
                }

                
            }
            builder.append("00");
        }
       
        
        return builder.toString();
    }
    private static String toNBTRawList(StringTag tag) {
        StringBuilder builder = new StringBuilder();
        
        // builder.append(Converter.convertToHex(tag.tagId,2));;
        builder.append(Converter.convertToHex(tag.value.length(),4));
        builder.append(Converter.convertToHex(tag.value));
        //builder.append("00");
        
        return builder.toString();
    }
    private static String toNBTRawList(ByteTag tag) {
        StringBuilder builder = new StringBuilder();
        builder.append(Converter.convertToHex(tag.value,2));
        
        return builder.toString();
    }
    private static String toNBTRawList(ShortTag tag) {
        StringBuilder builder = new StringBuilder();
        builder.append(Converter.convertToHex(tag.value,4));
        
        return builder.toString();
    }
    private static String toNBTRawList(IntTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.value,8));
        
        return builder.toString();
    }
    private static String toNBTRawList(LongTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.value,16));
        
        return builder.toString();
    }
    private static String toNBTRawList(FloatTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.value,8));
        
        return builder.toString();
    }
    private static String toNBTRawList(DoubleTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.value,16));
        
        return builder.toString();
    }
    private static String toNBTRawList(ByteArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.getBytes().length,8));
        for (byte b: tag.getBytes()) {
            builder.append(Converter.convertToHex(b,2));
        }
        
        
        return builder.toString();
    }
    private static String toNBTRawList(IntArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
      //  System.out.println(tag.getIntegers().length);
        builder.append(Converter.convertToHex(tag.getIntegers().length,8));
        for (int i: tag.getIntegers()) {
            builder.append(Converter.convertToHex(i,8));
        }
        
        
        return builder.toString();
    }
    private static String toNBTRawList(LongArrayTag tag) {
        StringBuilder builder = new StringBuilder();
        
      //  System.out.println(tag.getIntegers().length);
        builder.append(Converter.convertToHex(tag.getIntegers().length,8));
        for (long i: tag.getIntegers()) {
            builder.append(Converter.convertToHex(i,16));
            
        }
        
        
        return builder.toString();
    }
    private static Object toNBTRawList(ListTag tag) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        
        builder.append(Converter.convertToHex(tag.tagIdFor,2));
        builder.append(Converter.convertToHex(tag.children.size(),8));
        // builder.append(Converter.convertToHex(tag.name));
        if (tag.children.size() == 0) {
            // builder.append("00");
            return toString(builder.toString());
            
        } else if (tag.children.size() > 0) {
            
            for (int i = 0; i < tag.children.size(); i++) {
                
                Tag childTag = tag.children.get(i);
                
                if (childTag instanceof StringTag) {
                    builder.append(toNBTRawList((StringTag) childTag));
                } else if (childTag instanceof CompoundTag) {
                    builder.append(toNBTRawList((CompoundTag) childTag));
                } else if (childTag instanceof ByteTag) {
                    builder.append(toNBTRawList((ByteTag) childTag));
                } else if (childTag instanceof ShortTag) {
                    builder.append(toNBTRawList((ShortTag) childTag));
                } else if (childTag instanceof IntTag) {
                    builder.append(toNBTRawList((IntTag) childTag));
                } else if (childTag instanceof LongTag) {
                    builder.append(toNBTRawList((LongTag) childTag));
                } else if (childTag instanceof FloatTag) {
                    builder.append(toNBTRawList((FloatTag) childTag));
                } else if (childTag instanceof DoubleTag) {
                    builder.append(toNBTRawList((DoubleTag) childTag));
                } else if (childTag instanceof ByteArrayTag) {
                    builder.append(toNBTRawList((ByteArrayTag) childTag));
                } else if (childTag instanceof IntArrayTag) {
                    builder.append(toNBTRawList((IntArrayTag) childTag));
                } else if (childTag instanceof LongArrayTag) {
                    builder.append(toNBTRawList((LongArrayTag) childTag));
                } else if (childTag instanceof ListTag) {
                    builder.append(toNBTRawList((ListTag) childTag));
                }

                
            }
            // builder.append("00");
        }
       
        
        return builder.toString();
    }
    private static Object toNBTRaw(ListTag tag) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder();
        System.out.println("sda");
        builder.append("09");
        builder.append(Converter.convertToHex(tag.name.length(),4));
        builder.append(Converter.convertToHex(tag.name));
        builder.append(Converter.convertToHex(tag.tagIdFor,2));
        builder.append(Converter.convertToHex(tag.children.size(),8));

        if (tag.children.size() == 0) {
            // builder.append("00");
            return toString(builder.toString());
            
        } else if (tag.children.size() > 0) {
            
            for (int i = 0; i < tag.children.size(); i++) {
                
                Tag childTag = tag.children.get(i);
                
                if (childTag instanceof StringTag) {
                    builder.append(toNBTRawList((StringTag) childTag));
                } else if (childTag instanceof CompoundTag) {
                    builder.append(toNBTRawList((CompoundTag) childTag));
                } else if (childTag instanceof ByteTag) {
                    builder.append(toNBTRawList((ByteTag) childTag));
                } else if (childTag instanceof ShortTag) {
                    builder.append(toNBTRawList((ShortTag) childTag));
                } else if (childTag instanceof IntTag) {
                    builder.append(toNBTRawList((IntTag) childTag));
                } else if (childTag instanceof LongTag) {
                    builder.append(toNBTRawList((LongTag) childTag));
                } else if (childTag instanceof FloatTag) {
                    builder.append(toNBTRawList((FloatTag) childTag));
                } else if (childTag instanceof DoubleTag) {
                    builder.append(toNBTRawList((DoubleTag) childTag));
                } else if (childTag instanceof ByteArrayTag) {
                    builder.append(toNBTRawList((ByteArrayTag) childTag));
                } else if (childTag instanceof IntArrayTag) {
                    builder.append(toNBTRawList((IntArrayTag) childTag));
                } else if (childTag instanceof LongArrayTag) {
                    builder.append(toNBTRawList((LongArrayTag) childTag));
                } else if (childTag instanceof ListTag) {
                    builder.append(toNBTRawList((ListTag) childTag));
                }

                
            }
            // builder.append("00");
        }
       
        
        return builder.toString();
    }
}