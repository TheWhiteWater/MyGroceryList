package nz.co.redice.mygrocerylist.domain

class RemoveItemUseCase (private val repository: ListRepository) {

    fun removeItem(item: Item) {
        repository.removeItem(item)
    }
}