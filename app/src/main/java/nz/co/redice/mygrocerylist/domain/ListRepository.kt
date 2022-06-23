package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData

interface  ListRepository {
    fun addItem (item: Item)
    fun editItem(item: Item)
    fun getItem(id: Int): Item
    fun removeItem(item: Item)
    fun getList ():LiveData <List<Item>>
}