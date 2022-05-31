package nz.co.redice.mygrocerylist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nz.co.redice.mygrocerylist.data.GroceryListRepositoryImpl
import nz.co.redice.mygrocerylist.data.GroceryListRepositoryImpl.getGroceryList
import nz.co.redice.mygrocerylist.domain.*

class MainViewModel : ViewModel() {
    private val repository = GroceryListRepositoryImpl
    private val getGroceryListUseCase = GetGroceryListUseCase(repository)
    private val removeGroceryItemUseCase = RemoveGroceryItemUseCase(repository)
    private val editGroceryItemUseCase = EditGroceryItemUseCase(repository)

    val groceryList = getGroceryListUseCase.getGroceryList()


    fun removeGroceryItem(item: GroceryItem) {
        removeGroceryItemUseCase.removeGroceryItem(item)
    }

    fun changeEnableState(item: GroceryItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editGroceryItemUseCase.editGroceryItem(newItem)
    }

}