package nz.co.redice.mygrocerylist.domain.usecases

class EditGroceryItemUseCase (private val repository: GroceryListRepository) {

    fun editGroceryItem(item: GroceryItem) {
        repository.editGroceryItem(item)
    }




}