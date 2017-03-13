package com.example.dominik.prontoshop.core.events;


import com.example.dominik.prontoshop.model.LineItem;

import java.util.List;

public class UpdateToolbarEvent {

    private List<LineItem> mLineItems;

    public UpdateToolbarEvent(List<LineItem> mLineItems) {
        this.mLineItems = mLineItems;
    }

    public List<LineItem> getmLineItems() {
        return mLineItems;
    }
}
