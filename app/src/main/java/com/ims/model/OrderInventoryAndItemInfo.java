package com.ims.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OrderInventoryAndItemInfo {
    @Embedded public OrderInventory orderInventory;
    @Relation(
            parentColumn = "part_number",
            entityColumn = "part_number"
    )
    public Item item;
}
