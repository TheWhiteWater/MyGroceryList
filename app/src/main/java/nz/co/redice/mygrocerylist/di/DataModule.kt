package nz.co.redice.mygrocerylist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import nz.co.redice.mygrocerylist.data.AppDatabase
import nz.co.redice.mygrocerylist.data.ItemListDAO
import nz.co.redice.mygrocerylist.data.ListRepositoryImpl
import nz.co.redice.mygrocerylist.domain.ListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindsItemRepository(impl: ListRepositoryImpl): ListRepository


    companion object {

        @ApplicationScope
        @Provides
        fun provideItemListDao(
            application: Application
        ): ItemListDAO {
            return AppDatabase.getInstance(application).itemListDao()
        }
    }

}