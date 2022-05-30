package nz.co.redice.mygrocerylist.domain

class AddGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun addGroceryItem (item: GroceryItem)   {
        repository.addGroceryItem(item)
    }


}