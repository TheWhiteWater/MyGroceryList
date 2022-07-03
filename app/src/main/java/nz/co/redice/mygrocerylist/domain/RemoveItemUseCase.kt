package nz.co.redice.mygrocerylist.domain

import javax.inject.Inject

class RemoveItemUseCase @Inject constructor(
    private val repository: ListRepository) {

    suspend fun removeItem(item: Item) {
        repository.removeItem(item)
    }
}