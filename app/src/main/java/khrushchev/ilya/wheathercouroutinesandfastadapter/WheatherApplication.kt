package khrushchev.ilya.wheathercouroutinesandfastadapter

import android.app.Application
import khrushchev.ilya.wheathercouroutinesandfastadapter.AppComponent.Builder.Companion.build

class WheatherApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = build(this)
        super.onCreate()
    }
}