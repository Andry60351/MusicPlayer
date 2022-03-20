package com.example.RebmemMusicPlayer.utlis

import android.graphics.Point
import com.example.RebmemMusicPlayer.views.activities.MainActivity

object ScreenSizeUtils {
    fun getScreenHeight(): Int {
        val display = MainActivity.activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun getScreenWidth(): Int {
        val display = MainActivity.activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

}