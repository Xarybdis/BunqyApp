package com.example.bunqyapp.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class DefaultCurrentActivityListener : Application.ActivityLifecycleCallbacks,
    CurrentActivityListener {
    override var currentActivity: Activity? = null
    lateinit var context: Context
    protected var currentActivityStack: MutableList<Activity> = ArrayList()

    fun isActivityRunning(activityClass: Class<out Activity>): Boolean {
        for (activity in currentActivityStack) {
            if (activity.javaClass == activityClass) {
                return true
            }
        }
        return false
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        currentActivity = activity
        context = activity
        currentActivityStack.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(activity: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
        currentActivityStack.remove(activity)
    }
}

interface CurrentActivityListener {
    var currentActivity: Activity?
}