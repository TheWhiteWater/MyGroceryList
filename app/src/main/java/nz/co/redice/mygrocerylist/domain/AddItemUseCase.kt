package nz.co.redice.mygrocerylist.domain

import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val repository: ListRepository) {

    suspend  fun addItem(item: Item) {
        repository.addItem(item)
    }


}