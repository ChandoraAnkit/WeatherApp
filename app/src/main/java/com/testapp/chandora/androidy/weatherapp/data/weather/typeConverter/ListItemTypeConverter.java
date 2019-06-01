package com.testapp.chandora.androidy.weatherapp.data.weather.typeConverter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.ListItem;
import com.testapp.chandora.androidy.weatherapp.data.weather.model.WeatherItem;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by chandora on 01-Jun-2019
 */
public class ListItemTypeConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<ListItem> stringToListItemsList(String data){

        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ListItem>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String listItemsListToString(List<ListItem> listItems) {
        return gson.toJson(listItems);
    }
}
