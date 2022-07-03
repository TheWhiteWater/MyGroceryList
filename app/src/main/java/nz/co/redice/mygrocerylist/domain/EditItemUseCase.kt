package nz.co.redice.mygrocerylist.domain

import javax.inject.Inject

class EditItemUseCase @Inject constructor(private val repository: ListRepository) {

    suspend  fun editItem(item: Item) {
        repository.editItem(item)
    }


}