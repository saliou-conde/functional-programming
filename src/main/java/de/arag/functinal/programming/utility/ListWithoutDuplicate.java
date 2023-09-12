package de.arag.functinal.programming.utility;

import java.util.ArrayList;

public class ListWithoutDuplicate<T> extends ArrayList {

    @Override
    public boolean add(Object e) {
        if(this.contains(e)) {
            return true;
        }
        return super.add(e);
    }
}
