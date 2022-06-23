package nz.co.redice.mygrocerylist.domain

class GetItemUseCase (private val repository: ListRepository) {

     fun getItem(id: Int): Item {
        return repository.getItem(id)
    }
}