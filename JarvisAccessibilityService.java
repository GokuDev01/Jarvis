package com.example.jarvis;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.media.AudioManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class JarvisAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}

    @Override
    public void onInterrupt() {}

    public void adjustVolume(boolean increase) {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (increase) {
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
        } else {
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
        }
    }

    public void scrollScreen(String direction) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode != null) {
            if (direction.equalsIgnoreCase("up")) {
                rootNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
            } else if (direction.equalsIgnoreCase("down")) {
                rootNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
            }
        }
    }
}
