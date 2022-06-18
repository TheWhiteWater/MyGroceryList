package nz.co.redice.mygrocerylist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import nz.co.redice.mygrocerylist.data.ListRepositoryImpl
import nz.co.redice.mygrocerylist.domain.*

class MainViewModel : ViewModel() {
    private val repository = ListRepositoryImpl
    private val getListUseCase = GetListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)

    val list = getListUseCase.getList()


    fun removeItem(item: Item) {
        removeItemUseCase.removeItem(item)
    }

    fun changeEnableState(item: Item) {
        val newItem = item.copy(enabled = !item.enabled)
        editItemUseCase.editItem(newItem)
    }

    fun addItem(inputName: String, inputCount: Int) {
        val parsedItem = Item(inputName, inputCount)
        addItemUseCase.addItem(parsedItem)
    }
}
