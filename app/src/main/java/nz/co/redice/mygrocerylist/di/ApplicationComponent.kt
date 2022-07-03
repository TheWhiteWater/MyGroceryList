package nz.co.redice.mygrocerylist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import nz.co.redice.mygrocerylist.presentation.ItemFragment
import nz.co.redice.mygrocerylist.presentation.MainActivity

@ApplicationScope
@Component (modules = [
    DataModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ItemFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}