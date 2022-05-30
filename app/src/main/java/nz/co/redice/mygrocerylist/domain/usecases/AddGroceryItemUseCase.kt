package nz.co.redice.mygrocerylist.domain.usecases

class AddGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun addGroceryItem (item: GroceryItem)   {
        repository.addGroceryItem(item)
    }


}