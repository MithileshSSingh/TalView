package com.example.mithilesh.talview.utils;

import android.support.test.espresso.idling.CountingIdlingResource;

public class IdelingResourceInstance {

    private static IdelingResourceInstance Instance;

    private CountingIdlingResource countingIdlingResource;

    private IdelingResourceInstance() {
        countingIdlingResource = new CountingIdlingResource("SingleTonIdlingResource");
    }

    public static IdelingResourceInstance getInstance() {

        synchronized (IdelingResourceInstance.class) {
            if (Instance == null) {
                synchronized (IdelingResourceInstance.class) {
                    if (Instance == null) {
                        Instance = new IdelingResourceInstance();
                    }
                }
            }

        }
        return Instance;
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return countingIdlingResource;
    }
}