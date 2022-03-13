package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di

import dagger.Component
import khrushchev.ilya.wheathercouroutinesandfastadapter.DaggerAppComponent
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui.DayliWhetherFragment
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di.DaggerRepositoryComponent
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di.RepositoryComponent
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di.RepositoryProvider
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        MainScreenModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [
        RepositoryProvider::class
    ]
)
interface MainScreenComponent {

    fun inject(fragment: DayliWhetherFragment)

    class Builder {
        companion object {
            fun build(): MainScreenComponent {
                val repositoryProvider = DaggerRepositoryComponent.factory().create()
                return DaggerMainScreenComponent.builder()
                    .repositoryProvider(repositoryProvider)
                    .build()
            }
        }
    }

}

