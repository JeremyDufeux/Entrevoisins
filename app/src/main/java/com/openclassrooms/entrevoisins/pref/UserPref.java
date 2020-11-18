package com.openclassrooms.entrevoisins.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by jerem on 16/11/2020.
 */
public class UserPref {
    public static final String PREF_KEY_FAVOURITES_COUNT = "PREF_KEY_FAVOURITES_COUNT";
    public static final String PREF_KEY_FAVOURITES_VAL_PREFIX = "VAL_";
    private static SharedPreferences mPreferences;
    private static ArrayList<Long> mFavouritesId;

    public UserPref(Context context) {
        if(mPreferences == null){
            mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            mFavouritesId = new ArrayList<Long>();
            readPref();
        }
    }

    private void readPref(){
        if(mPreferences.contains(PREF_KEY_FAVOURITES_COUNT)){
            int favouritesCount = mPreferences.getInt(PREF_KEY_FAVOURITES_COUNT, 0);

            if(favouritesCount >0){
                for(int i = 0; i < favouritesCount; i++){
                    Long id = mPreferences.getLong(PREF_KEY_FAVOURITES_VAL_PREFIX +i, 0);
                    mFavouritesId.add(id);
                }
            }
        }
    }

    public static Boolean favouritesContains(Long id) {
        return mFavouritesId.contains(id);
    }

    public static void addFavorite(Long id){
        mFavouritesId.add(id);
        writePrefs();
    }

    public static void removeFavorite(Long id){
        mFavouritesId.remove(id);
        writePrefs();
        mPreferences.edit().remove(PREF_KEY_FAVOURITES_VAL_PREFIX + mFavouritesId.size()).apply();
    }

    private static void writePrefs(){
        mPreferences.edit().putInt(PREF_KEY_FAVOURITES_COUNT, mFavouritesId.size()).apply();
        for(int i = 0; i < mFavouritesId.size(); i++){
            mPreferences.edit().putLong(PREF_KEY_FAVOURITES_VAL_PREFIX +i, mFavouritesId.get(i)).apply();
        }
    }
}
