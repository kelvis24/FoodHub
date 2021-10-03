package com.example.mylogin.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    //reset for displaying names and other things to utalize for the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
