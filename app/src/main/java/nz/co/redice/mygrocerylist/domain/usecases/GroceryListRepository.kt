package nz.co.redice.mygrocerylist.domain.usecases

interface  GroceryListRepository {

    fun addGroceryItem (item: GroceryItem)

    fun editGroceryItem(item: GroceryItem)

    fun getGroceryItem(id: Int): GroceryItem

    fun getGroceryList ():List <GroceryItem>

    fun removeGroceryItem(item: GroceryItem)

}