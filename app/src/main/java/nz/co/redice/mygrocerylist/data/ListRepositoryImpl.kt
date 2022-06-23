package nz.co.redice.mygrocerylist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import nz.co.redice.mygrocerylist.domain.Item
import nz.co.redice.mygrocerylist.domain.ListRepository
import kotlin.random.Random

class ListRepositoryImpl(application: Application) : ListRepository {

    private val itemListDAO = AppDatabase.getInstance(application).itemListDao()
    private val mapper = ItemListMapper()


    override fun addItem(item: Item) {
        itemListDAO.addItem(mapper.mapEntityToDbModel(item))
    }

    override fun editItem(item: Item) {
        addItem(item)
    }

    override fun removeItem(item: Item) {
        itemListDAO.deleteItem(item.id)
    }

    override fun getItem(id: Int): Item {
        val dbModel = itemListDAO.getItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getList(): LiveData<List<Item>> = Transformations.map(itemListDAO.getItemList()) {
        mapper.mapListDbModelToListEntity(it)
    }

}