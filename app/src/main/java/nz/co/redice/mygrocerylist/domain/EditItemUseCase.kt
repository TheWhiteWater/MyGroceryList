package nz.co.redice.mygrocerylist.domain

class EditItemUseCase (private val repository: ListRepository) {

    fun editItem(item: Item) {
        repository.editItem(item)
    }




}