package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData

interface ListRepository {
    suspend fun addItem(item: Item)
    suspend fun editItem(item: Item)
    suspend fun getItem(id: Int): Item
    suspend fun removeItem(item: Item)
    fun getList(): LiveData<List<Item>>
}