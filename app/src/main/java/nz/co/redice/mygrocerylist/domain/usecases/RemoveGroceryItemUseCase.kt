package nz.co.redice.mygrocerylist.domain.usecases

class RemoveGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun removeGroceryItem(item: GroceryItem) {
        repository.removeGroceryItem(item)
    }
}