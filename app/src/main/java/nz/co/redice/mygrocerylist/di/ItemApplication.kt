package nz.co.redice.mygrocerylist.di

import android.app.Application

class ItemApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}