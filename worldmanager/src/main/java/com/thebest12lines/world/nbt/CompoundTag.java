package com.thebest12lines.world.nbt;

import java.util.ArrayList;

public class CompoundTag extends Tag {
    public final int tagId = 10;
    public void add(Tag tag) {
        this.children.add(tag);
    }
    protected ArrayList<Tag> children = new ArrayList<Tag>();
    public Tag[] getChildrenTags() {

        return (Tag[]) this.children.toArray();
        
    }

    
}
