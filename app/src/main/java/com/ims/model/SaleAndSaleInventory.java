package com.ims.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SaleAndSaleInventory {
    @Embedded public Sale sale;
    @Relation(
            parentColumn = "id",
            entityColumn = "saleId"
    )
    public List<SaleInventory> saleInventories;
}
