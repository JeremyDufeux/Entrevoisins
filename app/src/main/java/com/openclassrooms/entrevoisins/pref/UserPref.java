package com.openclassrooms.entrevoisins.pref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class UserPref {
    public static final String PREF_KEY_FAVORITES_COUNT = "PREF_KEY_FAVORITES_COUNT";
    public static final String PREF_KEY_FAVORITES_VAL_PREFIX = "PREF_KEY_FAVORITES_VAL_";
    private static final String FILENAME = "UserPref";
    private static SharedPreferences mPreferences;
    private static ArrayList<Long> mFavoritesId;

    public UserPref init(Context context){
        mPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        mFavoritesId = new ArrayList<>();
        readPref();
        return this;
    }

    private void readPref(){
        if(mPreferences.contains(PREF_KEY_FAVORITES_COUNT)){
            int favoritesCount = mPreferences.getInt(PREF_KEY_FAVORITES_COUNT, 0);

            if(favoritesCount >0){
                for(int i = 0; i < favoritesCount; i++){
                    Long id = mPreferences.getLong(PREF_KEY_FAVORITES_VAL_PREFIX +i, 0);
                    mFavoritesId.add(id);
                }
            }
        }
    }

    public Boolean favoritesContains(Long id) {
        return mFavoritesId.contains(id);
    }

    public void addFavoriteId(Long id){
        mFavoritesId.add(id);
        writePrefs();
    }

    public void removeFavoriteId(Long id){
        mFavoritesId.remove(id);
        writePrefs();
        mPreferences.edit().remove(PREF_KEY_FAVORITES_VAL_PREFIX + mFavoritesId.size()).apply();
    }

    private void writePrefs(){
        mPreferences.edit().putInt(PREF_KEY_FAVORITES_COUNT, mFavoritesId.size()).apply();
        for(int i = 0; i < mFavoritesId.size(); i++){
            mPreferences.edit().putLong(PREF_KEY_FAVORITES_VAL_PREFIX +i, mFavoritesId.get(i)).apply();
        }
    }
}
