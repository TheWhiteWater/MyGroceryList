package nz.co.redice.mygrocerylist.domain

class GetGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun getGroceryItem(id: Int): GroceryItem? {
        return repository.getGroceryItem(id)
    }
}