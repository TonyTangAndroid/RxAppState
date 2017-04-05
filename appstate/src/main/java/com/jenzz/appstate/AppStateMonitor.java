package com.jenzz.appstate;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.jenzz.appstate.internal.AppStateRecognizer;
import com.jenzz.appstate.internal.DefaultAppStateRecognizer;

import static android.support.annotation.RestrictTo.Scope.TESTS;
import static com.jenzz.appstate.AppState.BACKGROUND;
import static com.jenzz.appstate.AppState.FOREGROUND;

/**
 * An app state monitor that keeps track of whenever the application
 * goes into background and comes back into foreground.
 */
@SuppressWarnings({"unused"})
public final class AppStateMonitor {

  @NonNull private final AppStateRecognizer recognizer;

  /**
   * Creates a new {@link AppStateMonitor} instance for the given {@link Application}.
   *
   * @param app the application to monitor for app state changes
   * @return a new {@link AppStateMonitor} instance
   */
  @NonNull
  public static AppStateMonitor create(@NonNull Application app) {
    return new AppStateMonitor(app);
  }

  private AppStateMonitor(@NonNull Application app) {
    this.recognizer = new DefaultAppStateRecognizer(app);
  }

  @RestrictTo(TESTS)
  AppStateMonitor(@NonNull AppStateRecognizer recognizer) {
    this.recognizer = recognizer;
  }

  /**
   * Starts monitoring the app for background / foreground changes.
   */
  public void start() {
    recognizer.start();
  }

  /**
   * Stops monitoring the app for background / foreground changes.
   */
  public void stop() {
    recognizer.stop();
  }

  /**
   * Adds a new {@link AppStateListener} to the app state monitor.
   *
   * @param appStateListener the listener to add
   */
  public void addListener(@NonNull AppStateListener appStateListener) {
    recognizer.addListener(appStateListener);
  }

  /**
   * Removes the specified {@link AppStateListener} from the app state monitor.
   *
   * @param appStateListener the listener to remove
   */
  public void removeListener(@NonNull AppStateListener appStateListener) {
    recognizer.removeListener(appStateListener);
  }

  /**
   * Checks whether the app is currently in the foreground.
   *
   * @return {@code true} if the app is currently in the foreground, {@code false} otherwise
   */
  public boolean isAppInForeground() {
    return recognizer.getAppState() == FOREGROUND;
  }

  /**
   * Checks whether the app is currently in the background.
   *
   * @return {@code true} if the app is currently in the background, {@code false} otherwise
   */
  public boolean isAppInBackground() {
    return recognizer.getAppState() == BACKGROUND;
  }
}