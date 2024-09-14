package com.triPCups.blogs.showsOnline

import android.app.Application
import com.google.firebase.FirebaseApp
import com.triPCups.blogs.base.di.baseModule
import com.triPCups.blogs.showsOnline.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class BlogApp : Application() {

    init {
//        FirebaseApp.initializeApp(this)
    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        // Start Koin
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@BlogApp)
            modules(appModule, baseModule)
        }
    }

}