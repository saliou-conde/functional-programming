package de.arag.functinal.programming.utility;

import java.util.ArrayList;

public class ListWithoutDuplicate<T> extends ArrayList<T> {

    @Override
    public boolean add(Object e) {
        if(this.contains(e)) {
            return true;
        }
        return this.add(e);
    }
}
