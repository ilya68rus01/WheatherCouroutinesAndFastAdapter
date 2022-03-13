package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui.DayliWheatherViewModel
import javax.inject.Singleton


@Module
interface MainScreenModule {

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(DayliWheatherViewModel::class)
    fun bindDayliWheatherViewModel(model: DayliWheatherViewModel): ViewModel
}
