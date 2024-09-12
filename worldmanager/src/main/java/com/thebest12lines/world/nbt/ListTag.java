package com.thebest12lines.world.nbt;

import java.util.ArrayList;

public class ListTag extends Tag {
    public final int tagId = 9;
    protected int tagIdFor = 0;
    public void add(StringTag tag) throws TagException {
        
            if (tagIdFor == 0) {
                this.children.add(tag);
                tagIdFor = tag.tagId;
            }
            System.out.println(tagIdFor);
            if (tagIdFor != tag.tagId) {
                throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
            } else {
                this.children.add(tag);
            }    
    }
    public void add(CompoundTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(ListTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(IntTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(LongArrayTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(IntArrayTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(LongTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(ByteTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(ShortTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(FloatTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(DoubleTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    public void add(ByteArrayTag tag) throws TagException {
        
        if (tagIdFor == 0) {
            this.children.add(tag);
            tagIdFor = tag.tagId;
        }
        System.out.println(tagIdFor);
        if (tagIdFor != tag.tagId) {
            throw new TagException("Invalid tag type, must be equal to the first tagId of the first tag added");
        } else {
            this.children.add(tag);
        }    
    }
    protected ArrayList<Tag> children = new ArrayList<Tag>();
    public Tag[] getChildrenTags() {

        return (Tag[]) this.children.toArray();
        
    }

    
}
