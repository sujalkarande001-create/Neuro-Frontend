package com.example.minivone;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManage {

    private static final String PREF_NAME = "app_preferences";

    private static final String IS_FIRST_LAUNCH = "IsFirstLaunch";
    private static final String PROFILE_COMPLETED = "isProfileCompleted";

    // 🔥 Profile Data Keys
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public PrefManage(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // ✅ First Launch
    public boolean isFirstLaunch() {
        return pref.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(boolean isFirst) {
        editor.putBoolean(IS_FIRST_LAUNCH, isFirst);
        editor.apply();
    }

    // ✅ Profile Completed
    public boolean isProfileCompleted() {
        return pref.getBoolean(PROFILE_COMPLETED, false);
    }

    public void setProfileCompleted(boolean value) {
        editor.putBoolean(PROFILE_COMPLETED, value);
        editor.apply();
    }

    // ✅ 🔥 Save Profile Data
    public void saveUserData(String name, String email, String phone, String address) {
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(PHONE, phone);
        editor.putString(ADDRESS, address);
        editor.apply();
    }

    // ✅ 🔥 Get Profile Data
    public String getName() {
        return pref.getString(NAME, "User");
    }

    public String getEmail() {
        return pref.getString(EMAIL, "No Email");
    }

    public String getPhone() {
        return pref.getString(PHONE, "No Phone");
    }

    public String getAddress() {
        return pref.getString(ADDRESS, "No Address");
    }

    // 🔥 Optional: Clear all data on logout
    public void clearAll() {
        editor.clear();
        editor.apply();
    }
}