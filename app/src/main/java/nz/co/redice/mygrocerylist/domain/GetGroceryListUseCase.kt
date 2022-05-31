package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData

class GetGroceryListUseCase (private val repository: GroceryListRepository){

    fun getGroceryList ():LiveData <List<GroceryItem>> {
        return repository.getGroceryList()
    }
}