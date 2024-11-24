package com.example.jarvis;

public class CommandProcessor {

    public static String process(String command) {
        if (command.toLowerCase().contains("volume up")) {
            return "Increasing volume.";
        } else if (command.toLowerCase().contains("volume down")) {
            return "Decreasing volume.";
        } else if (command.toLowerCase().contains("scroll up")) {
            return "Scrolling up.";
        } else if (command.toLowerCase().contains("scroll down")) {
            return "Scrolling down.";
        } else if (command.toLowerCase().contains("enable dnd")) {
            return "Enabling Do Not Disturb mode.";
        } else if (command.toLowerCase().contains("disable dnd")) {
            return "Disabling Do Not Disturb mode.";
        } else {
            return "Command not recognized.";
        }
    }
}
