package nz.co.redice.mygrocerylist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nz.co.redice.mygrocerylist.data.ListRepositoryImpl
import nz.co.redice.mygrocerylist.domain.AddItemUseCase
import nz.co.redice.mygrocerylist.domain.EditItemUseCase
import nz.co.redice.mygrocerylist.domain.GetItemUseCase
import nz.co.redice.mygrocerylist.domain.Item

class ItemViewModel : ViewModel() {

    private val repository = ListRepositoryImpl
    private val getItemUseCase = GetItemUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() {
            return _errorInputName
        }

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount


    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item>
        get() = _item


    fun getItem(itemId: Int) {
        val item = getItemUseCase.getItem(itemId)
        _item.value = item
    }

    private val _shouldCloseScreen =  MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun addItem(inputName: String?, inputCount: String?) {
        val name = parseItemName(inputName)
        val count = parseItemCount(inputCount)
        val fieldsAreValid = validateInput(name, count)
        if (fieldsAreValid) {
            val parsedItem = Item(name, count)
            addItemUseCase.addItem(parsedItem)
            finishWork()
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val parsedName = parseItemName(inputName)
        val parsedCount = parseItemCount(inputCount)
        val fieldsAreValid = validateInput(parsedName, parsedCount)
        if (fieldsAreValid) {
            _item.value?.let {
                val item = it.copy(name = parsedName, count = parsedCount )
                editItemUseCase.editItem(item)
            }
            finishWork()
        }
    }

    private fun parseItemName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseItemCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(inputName: String, inputCount: Int): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (inputCount <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
