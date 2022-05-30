package nz.co.redice.mygrocerylist.domain

import nz.co.redice.mygrocerylist.domain.GroceryItem

interface  GroceryListRepository {

    fun addGroceryItem (item: GroceryItem)

    fun editGroceryItem(item: GroceryItem)

    fun getGroceryItem(id: Int): GroceryItem?

    fun getGroceryList ():List <GroceryItem>

    fun removeGroceryItem(item: GroceryItem)

}