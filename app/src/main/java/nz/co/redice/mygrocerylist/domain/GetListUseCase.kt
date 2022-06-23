package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData

class GetListUseCase (private val repository: ListRepository){

     fun getList ():LiveData <List<Item>> {
        return repository.getList()
    }
}