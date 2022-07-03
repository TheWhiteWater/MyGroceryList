package nz.co.redice.mygrocerylist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.redice.mygrocerylist.domain.EditItemUseCase
import nz.co.redice.mygrocerylist.domain.GetListUseCase
import nz.co.redice.mygrocerylist.domain.Item
import nz.co.redice.mygrocerylist.domain.RemoveItemUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
    private val removeItemUseCase: RemoveItemUseCase,
    private val editItemUseCase: EditItemUseCase
) : ViewModel() {


    val list = getListUseCase.getList()

    fun removeItem(item: Item) {
        viewModelScope.launch {
            removeItemUseCase.removeItem(item)
        }
    }

    fun changeEnableState(item: Item) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editItemUseCase.editItem(newItem)
        }
    }

}
