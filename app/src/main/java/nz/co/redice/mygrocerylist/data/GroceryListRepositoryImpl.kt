package nz.co.redice.mygrocerylist.data

import nz.co.redice.mygrocerylist.domain.GroceryItem
import nz.co.redice.mygrocerylist.domain.GroceryListRepository

object GroceryListRepositoryImpl : GroceryListRepository {

    private val groceryList = mutableListOf<GroceryItem>()
    private var autoIncrementId = 0

    override fun addGroceryItem(item: GroceryItem) {
        if (item.id == GroceryItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        groceryList.add(item)
    }

    override fun editGroceryItem(item: GroceryItem) {
        val oldItem = getGroceryItem(item.id)
        groceryList.remove(oldItem)
        groceryList.add(item)
    }

    override fun getGroceryItem(id: Int): GroceryItem? {
        return groceryList.find { it.id == id }
    }

//    override fun getGroceryItem(id: Int): GroceryItem {
//        return groceryList.find { it.id == id } ?:
//        throw RuntimeException("Item with $id not found")
//    }

    override fun getGroceryList(): List<GroceryItem> {
        return groceryList.toList()
    }

    override fun removeGroceryItem(item: GroceryItem) {
        groceryList.remove(item)
    }
}