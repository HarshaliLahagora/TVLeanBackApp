/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mothership.tvhome;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.tv.ui.metro.model.DisplayItem;

import java.util.HashMap;
import java.util.Random;

/**
 * A collection of utility methods, all static.
 */
public class Utils {

    /*
     * Making sure public utility methods remain static
     */
    private Utils() {
    }

    /**
     * Returns the screen/display size
     */
    public static Point getDisplaySize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Shows a (long) toast
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows a (long) toast.
     */
    public static void showToast(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show();
    }

    public static int convertDpToPixel(Context ctx, int dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    /**
     * Formats time in milliseconds to hh:mm:ss string format.
     */
    public static String formatMillis(int millis) {
        String result = "";
        int hr = millis / 3600000;
        millis %= 3600000;
        int min = millis / 60000;
        millis %= 60000;
        int sec = millis / 1000;
        if (hr > 0) {
            result += hr + ":";
        }
        if (min >= 0) {
            if (min > 9) {
                result += min + ":";
            } else {
                result += "0" + min + ":";
            }
        }
        if (sec > 9) {
            result += sec;
        } else {
            result += "0" + sec;
        }
        return result;
    }
    static HashMap<String, Integer> sUiMap = new HashMap<String, Integer>();
    public static int UiNameToId(DisplayItem di){
        if(sUiMap.size()==0){
            sUiMap.put("tv_home",20001);
            sUiMap.put("block_list",10001);
            sUiMap.put("block_grid",20101);
            sUiMap.put("block_grid_button",20102);
            sUiMap.put("block_grid_button_hr",20102);
            sUiMap.put("block_grid_icon",20103);
            sUiMap.put("display_item",30001);
        }

        DisplayItem.UI type = di.ui_type;
        if(type != null&&type.name()!=null)
        {
            Integer id = sUiMap.get(type.name());
            if(id != null && type.unitary()==false){
                id+=1000;
            }
            if (id != null) {
                return id;
            } else {
                return new Random().nextInt(4);
            }
        }
        return new Random().nextInt(4);
    }

    static boolean scrolling = false;
    public static boolean isScrolling(){
        return scrolling;
    }

    public static void setScrolling(boolean scroll){
        scrolling = scroll;
    }
}
