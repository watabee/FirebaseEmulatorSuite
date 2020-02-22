package com.github.watabee.firebaseemulatorsuite

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // firebase emulators:start --only firestore でローカルにエミュレーターを起動する.
        val settings = FirebaseFirestoreSettings.Builder()
            .setHost("10.0.2.2:8080")
            .setSslEnabled(false)
            .setPersistenceEnabled(false)
            .build()

        FirebaseFirestore.getInstance().firestoreSettings = settings
    }
}