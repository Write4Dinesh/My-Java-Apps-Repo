package com.example.helloandroid;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WFMFavoriteOffersDO {

    public WFMFavoriteOffersDO() {
    }

    @DatabaseField(id = true, index = true, canBeNull = false)
    public String profile_id;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> offer_id_list;
}

