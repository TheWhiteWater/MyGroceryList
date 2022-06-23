package nz.co.redice.mygrocerylist.domain

class AddItemUseCase (private val repository: ListRepository) {

     fun addItem (item: Item)   {
        repository.addItem(item)
    }


}