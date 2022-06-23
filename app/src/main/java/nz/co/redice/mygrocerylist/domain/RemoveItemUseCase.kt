package nz.co.redice.mygrocerylist.domain

class RemoveItemUseCase (private val repository: ListRepository) {

   suspend fun removeItem(item: Item) {
        repository.removeItem(item)
    }
}