package nz.co.redice.mygrocerylist.domain

import javax.inject.Inject

class GetItemUseCase @Inject constructor(private val repository: ListRepository) {

    suspend  fun getItem(id: Int): Item {
        return repository.getItem(id)
    }
}