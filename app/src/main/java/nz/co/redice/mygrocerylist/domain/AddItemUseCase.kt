package nz.co.redice.mygrocerylist.domain

class AddItemUseCase(private val repository: ListRepository) {

    suspend  fun addItem(item: Item) {
        repository.addItem(item)
    }


}