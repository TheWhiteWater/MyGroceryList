package nz.co.redice.mygrocerylist.domain.usecases

class GetGroceryListUseCase (private val repository: GroceryListRepository){

    fun getGroceryList ():List <GroceryItem> {
        return repository.getGroceryList()
    }
}