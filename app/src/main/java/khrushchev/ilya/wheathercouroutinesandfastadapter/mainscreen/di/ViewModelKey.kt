package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di

import androidx.lifecycle.ViewModel

import dagger.MapKey
import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Documented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)