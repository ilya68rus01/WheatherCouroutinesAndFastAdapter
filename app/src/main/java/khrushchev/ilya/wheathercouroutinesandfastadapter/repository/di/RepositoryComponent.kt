package khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di


import dagger.Component
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.Repository

@Component(
    modules = [
        RepositoryModule::class,
    ]
)
interface RepositoryComponent: RepositoryProvider {

    @Component.Factory
    interface Factory {
        fun create(): RepositoryProvider
    }
}

interface RepositoryProvider {
    fun getRepository(): Repository
}