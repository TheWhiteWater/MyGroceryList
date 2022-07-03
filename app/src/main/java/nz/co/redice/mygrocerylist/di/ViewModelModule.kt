package nz.co.redice.mygrocerylist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nz.co.redice.mygrocerylist.presentation.ItemViewModel
import nz.co.redice.mygrocerylist.presentation.MainViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ItemViewModel::class)
    fun bindItemViewModel(impl: ItemViewModel): ViewModel


}
