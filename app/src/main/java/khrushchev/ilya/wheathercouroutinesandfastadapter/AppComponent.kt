package khrushchev.ilya.wheathercouroutinesandfastadapter

import dagger.Component
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di.DaggerRepositoryComponent
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di.RepositoryProvider
import javax.inject.Scope

@ApplicationScope
@Component(
)
interface AppComponent {
    fun inject(app: WheatherApplication)

    class Builder {
        companion object {
            fun build(app: WheatherApplication): AppComponent {
                return DaggerAppComponent.builder()
                    .build()
            }
        }
    }
}


@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope