package nz.co.redice.mygrocerylist.domain

class RemoveGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun removeGroceryItem(item: GroceryItem) {
        repository.removeGroceryItem(item)
    }
}