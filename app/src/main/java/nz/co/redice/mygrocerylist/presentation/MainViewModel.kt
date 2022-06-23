package nz.co.redice.mygrocerylist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import nz.co.redice.mygrocerylist.data.ListRepositoryImpl
import nz.co.redice.mygrocerylist.domain.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListRepositoryImpl(application)
    private val getListUseCase = GetListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    val list = getListUseCase.getList()

    fun removeItem(item: Item) {
            removeItemUseCase.removeItem(item)
    }

    fun changeEnableState(item: Item) {
            val newItem = item.copy(enabled = !item.enabled)
            editItemUseCase.editItem(newItem)
    }


}
