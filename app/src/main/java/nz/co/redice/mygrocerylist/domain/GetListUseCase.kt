package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetListUseCase @Inject constructor(private val repository: ListRepository){

     fun getList ():LiveData <List<Item>> {
        return repository.getList()
    }
}