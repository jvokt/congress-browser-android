package com.jvokt.csci571.congressapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

/*
 * Based on: http://androidopentutorials.com/android-how-to-store-list-of-values-in-sharedpreferences/
 */

public class SharedPreference<T> {

	public static final String PREFS_NAME = "CONGRESS_API";
	public String FAVORITES;
	
	public SharedPreference(T t) {
		super();
        if (t instanceof Legislator) {
            FAVORITES = "FAV_LEGISLATORS";
        } else if (t instanceof Committee) {
            FAVORITES = "FAV_COMMITTEES";
        } else if (t instanceof Bill) {
            FAVORITES = "FAV_BILLS";
        } else {
            FAVORITES = "FAV_LEGISLATORS";
        }
    }

	// This four methods are used for maintaining favorites.
	public void saveFavorites(Context context, List<T> favorites) {
		SharedPreferences settings;
		Editor editor;

		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		editor = settings.edit();

		Gson gson = new Gson();
		String jsonFavorites = gson.toJson(favorites);

		editor.putString(FAVORITES, jsonFavorites);

		editor.commit();
	}

	public void addFavorite(Context context, T product) {
		List<T> favorites = getFavorites(context);
		if (favorites == null)
			favorites = new ArrayList<T>();
		favorites.add(product);
		saveFavorites(context, favorites);
	}

	public void removeFavorite(Context context, T product) {
		ArrayList<T> favorites = getFavorites(context);
		if (favorites != null) {
			favorites.remove(product);
			saveFavorites(context, favorites);
		}
	}

	public ArrayList<T> getFavorites(Context context) {
		SharedPreferences settings;
		List<T> favorites;

		settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);

		if (settings.contains(FAVORITES)) {
			String jsonFavorites = settings.getString(FAVORITES, null);
			Gson gson = new Gson();

            if (FAVORITES.equals("FAV_LEGISLATORS")) {
                Object[] favoriteItems = gson.fromJson(jsonFavorites,
                        Legislator[].class);
                favorites = (List<T>) Arrays.asList(favoriteItems);
            } else if (FAVORITES.equals("FAV_COMMITTEES")) {
                Object[] favoriteItems = gson.fromJson(jsonFavorites,
                        Committee[].class);
                favorites = (List<T>) Arrays.asList(favoriteItems);
            } else if (FAVORITES.equals("FAV_BILLS")) {
                Object[] favoriteItems = gson.fromJson(jsonFavorites,
                        Bill[].class);
                favorites = (List<T>) Arrays.asList(favoriteItems);
            } else {
                Object[] favoriteItems = gson.fromJson(jsonFavorites,
                        Legislator[].class);
                favorites = (List<T>) Arrays.asList(favoriteItems);
            }
			favorites = new ArrayList<T>(favorites);
		} else
			return null;

		return (ArrayList<T>) favorites;
	}
}
