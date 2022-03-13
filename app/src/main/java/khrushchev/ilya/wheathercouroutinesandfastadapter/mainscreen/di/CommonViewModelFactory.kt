package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Module
abstract class ViewModelFactoryModule {
    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}


class ViewModelProviderFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider =
            viewModels[modelClass] ?: throw IllegalArgumentException("Model class not found")
        return (viewModelProvider.get() as T)
    }
}