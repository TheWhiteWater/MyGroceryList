package nz.co.redice.mygrocerylist.domain.usecases

class GetGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun getGroceryItem(id: Int): GroceryItem {
        return repository.getGroceryItem(id)
    }
}