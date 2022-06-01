package nz.co.redice.mygrocerylist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nz.co.redice.mygrocerylist.domain.GroceryItem
import nz.co.redice.mygrocerylist.domain.GroceryListRepository
import kotlin.random.Random

object GroceryListRepositoryImpl : GroceryListRepository {

    private val groceryListLD = MutableLiveData<List<GroceryItem>>()
    private val groceryList = sortedSetOf<GroceryItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = GroceryItem("Name $i", count = i, id = i, enabled = Random.nextBoolean())
            groceryList.add(item)
        }
    }

    override fun addGroceryItem(item: GroceryItem) {
        if (item.id == GroceryItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        groceryList.add(item)
        updateList()
    }

    override fun editGroceryItem(item: GroceryItem) {
        val oldItem = getGroceryItem(item.id)
        groceryList.remove(oldItem)
        addGroceryItem(item)
        updateList()
    }

    override fun getGroceryItem(id: Int): GroceryItem? {
        return groceryList.find { it.id == id }
            ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getGroceryList(): LiveData<List<GroceryItem>> {
        updateList()
        return groceryListLD
    }

    override fun removeGroceryItem(item: GroceryItem) {
        groceryList.remove(item)
        updateList()
    }

    private fun updateList() {
        groceryListLD.value = groceryList.toList()
    }
}