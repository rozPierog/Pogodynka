package com.example.leon.pogodynka.api;

import android.support.annotation.RequiresPermission;

/**
 * Created by Leon on 12-Dec-17.
 */

public interface ServerResponseListener<T> {

    @RequiresPermission("android.permission.READ_SYNC_STATS")
    void onSuccess(T response);
    void onError(WSError error);
}