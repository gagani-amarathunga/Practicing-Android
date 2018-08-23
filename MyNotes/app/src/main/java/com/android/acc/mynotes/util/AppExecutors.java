package com.android.acc.mynotes.util;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Global executor pools for the whole application.
 * <p>
 * It is inefficient to create runnable threads every time a db operation is needed to be performed.
 * Therefore AppExecutors class which uses Singleton pattern is used as a runnable task that executes all the database calls in
 * a same thread sequentially without creating many threads.
 */

public class AppExecutors {

    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;

    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static AppExecutors getsInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                /*diskIO is a single thread executor which ensures that db transactions are done in order.
                * networkIO is a pool of 3 threads which allow us to run different threads simultaneously while controlling the num of threads.
                 * */
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    /* The UI thread can be accessed in an Activity class which loads the UI,
    unless a class as follows has to be used to access the UI thread. */
    private static class MainThreadExecutor implements Executor {
        private android.os.Handler mainThreadHandler = new android.os.Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
