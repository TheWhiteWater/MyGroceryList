package nz.co.redice.mygrocerylist.domain

import androidx.lifecycle.LiveData
import nz.co.redice.mygrocerylist.domain.GroceryItem

interface  GroceryListRepository {

    fun addGroceryItem (item: GroceryItem)

    fun editGroceryItem(item: GroceryItem)

    fun getGroceryItem(id: Int): GroceryItem?

    fun getGroceryList ():LiveData <List<GroceryItem>>

    fun removeGroceryItem(item: GroceryItem)

}