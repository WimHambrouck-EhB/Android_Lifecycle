package org.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val logTag = MainActivity::class.java.canonicalName

    /* Sources:
        https://developer.android.com/guide/components/activities/activity-lifecycle
        https://developer.android.com/reference/android/app/Activity#activity-lifecycle
        https://developer.android.com/reference/android/util/Log
        https://developer.android.com/reference/android/os/Bundle
     */

    /**
     * Called at the start of the lifecycle, before the activity is visible.
     * Instantiates the UI (setContentView).
     *
     * Use it to perform basic application startup logic that should happen only once
     * for the entire life of the activity (comparable to how you would use a constructor)
     * e.g.: instantiate objects needed, setup view binding, ...
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(logTag, "Entered onCreate")
        // After the onCreate() method finishes execution, the activity enters the Started state,
        // and the system calls the onStart() and onResume() methods in quick succession.
    }


    /**
     * Called after onStop() when the current activity is being re-displayed to the user
     * (i.e.: the user has navigated back to it).
     *
     * It will be followed by onStart() and then onResume().
     */
    override fun onRestart() {
        super.onRestart()
        Log.d(logTag, "Entered onRestart")
        // Once this callback finishes, system invokes onStart()
    }

    /**
     * Called just before the activity is visible.
     *
     * Use it to initialize the code that maintains the UI.
     * E.g.: live location updates
     */
    override fun onStart() {
        super.onStart()
        Log.d(logTag, "Entered onStart")
        // Once this callback finishes, the activity enters the Resumed state,
        // and the system invokes the onResume() method.
    }

    /**
     * This method is called after onStart() when the activity is being re-initialized
     * from a previously saved state.
     *
     * Usually you'll use onCreate() to restore the activity state,
     * but sometimes it is useful to do this after the activity is visible
     * (i.e.: after all of the initialization has been done)
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(logTag, "Entered onRestoreInstanceState")
        // Once this callback finishes, system invokes onResume()
    }

    /**
     * Called just before the activity becomes the foreground activity
     * (= activity is visible, and the user can interact with it)
     * The app stays in this state until something happens to take focus away from the app.
     * (back button, home button, dialog, incoming call, ...)
     *
     * Use it to enable any functionality that needs to run
     * while the component is visible and in the foreground, such as starting a camera preview.
     */
    override fun onResume() {
        super.onResume()
        Log.d(logTag, "Entered onResume")
        // At this point, the activity is visible and in the foreground.
        // When an interruptive event occurs (e.g.: a dialog),
        // the activity enters the Paused state, and the system invokes the onPause() callback.
    }


    /**
     * Called when the activity is no longer in the foreground (though it may still be visible)
     * Use it to stop any functionality that does not need to run while
     * the component is not in the foreground, such as stopping a camera preview.
     */
    override fun onPause() {
        super.onPause()
        Log.d(logTag, "Entered onPause")
        // The system calls this method as the first indication that the user is leaving your activity
        // (though it does not always mean the activity is being destroyed).
        // The activity will either come back to the foreground and invoke onResume()
        // or it will become invisible and invoke onStop()
    }

    /**
     * Called when your activity is no longer visible to the user.
     * Use it to release or adjust resources that are not needed while the app is not visible to the user.
     * E.g.: pause animations or switch from fine-grained to coarse-grained location updates.
     */
    override fun onStop() {
        super.onStop()
        Log.d(logTag, "Entered onStop")
        // From the Stopped state, the activity will either come back to interact with the user
        // and invoke onRestart() or it is finished running and goes away and calls onDestroy()
    }

    /**
     * Called before activity is killed so that the state can be restored in onCreate(Bundle)
     * or onRestoreInstanceState(Bundle).
     *
     * The default implementation takes care of most of the UI per-instance state for you
     * (all of which is restored by the default implementation of onRestoreInstanceState(Bundle)).
     * Override this method to save additional information not captured by each individual view.
     *
     * @param outState Bundle in which to place your saved state.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(logTag, "Entered onSaveInstanceState")
    }

    /**
     * Called before the activity is destroyed.
     * This could be either due to the activity finishing (user dismissed it or finish() was called)
     * or a configuration change (e.g.: rotating the screen)
     * Use it to release all resources that have not yet been released by earlier callbacks.
     */
    override fun onDestroy() {
        super.onDestroy()

        // If the activity is finishing, onDestroy() is the final lifecycle callback the activity receives.
        //
        // If onDestroy() is called as the result of a configuration change,
        // the system immediately creates a new activity instance and then calls onCreate()
        // on that new instance in the new configuration.
        //
        // Tip: isFinishing() can also be called in onPause() or onStop() to determine if the activity
        // is going to be killed or not.
        if (isFinishing) {
            Log.d(logTag, "Entered onDestroy: activity will be killed after this call.")
        } else {
            Log.d(logTag, "Entered onDestroy: activity will call onCreate after this call.")
        }
    }


}