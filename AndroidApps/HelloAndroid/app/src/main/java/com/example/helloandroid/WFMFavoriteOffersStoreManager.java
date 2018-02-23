package com.example.helloandroid;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh.k.masthaiah on 7/25/2017.
 */

public class WFMFavoriteOffersStoreManager {
    private DatabaseHelper mDatabaseHelper = null;

    public WFMFavoriteOffersStoreManager(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    public void saveFavoriteOfferForTheUser(String profileId, String offerId) {
        WFMFavoriteOffersDO favoriteOffersDO = null;
        favoriteOffersDO = getFavoriteOfferForTheUser(profileId);

        if (favoriteOffersDO != null && favoriteOffersDO.offer_id_list != null) {
            favoriteOffersDO.offer_id_list.add(offerId);
        } else {
            favoriteOffersDO = new WFMFavoriteOffersDO();
            favoriteOffersDO.profile_id = profileId;
            favoriteOffersDO.offer_id_list = new ArrayList<String>();
            favoriteOffersDO.offer_id_list.add(offerId);
        }
        try {
            mDatabaseHelper.getFavoriteOffersDo().createOrUpdate(favoriteOffersDO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WFMFavoriteOffersDO getFavoriteOfferForTheUser(String profileId) {
        List<WFMFavoriteOffersDO> favoriteOffersDOs = null;
        try {
            favoriteOffersDOs = mDatabaseHelper.getFavoriteOffersDo().queryForEq("profile_id", profileId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (favoriteOffersDOs != null && !favoriteOffersDOs.isEmpty()) {
            Log.d("Favorite", "Retrieved Favorite List From DB");
            return favoriteOffersDOs.get(0);
        }
        Log.d("Favorite", "Favorite List From DB is NULL");
        return null;
    }

    public WFMFavoriteOffersDO removeFavoriteOfferForTheUser(String profileId) {
        List<WFMFavoriteOffersDO> favoriteOffersDOs = null;
        try {
            mDatabaseHelper.getFavoriteOffersDo().deleteById(profileId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (favoriteOffersDOs != null && !favoriteOffersDOs.isEmpty()) {
            Log.d("Favorite", "Retrieved Favorite List From DB");
            return favoriteOffersDOs.get(0);
        }
        Log.d("Favorite", "Favorite List From DB is NULL");
        return null;
    }
}
