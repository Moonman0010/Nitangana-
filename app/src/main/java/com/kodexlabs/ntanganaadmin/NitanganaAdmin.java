package com.kodexlabs.ntanganaadmin;

import android.app.Application;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;

/**
 * Created by Aritra on 06-12-2017.
 */

public class NitanganaAdmin extends Application {
    private RecyclerView mBlogList;
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
