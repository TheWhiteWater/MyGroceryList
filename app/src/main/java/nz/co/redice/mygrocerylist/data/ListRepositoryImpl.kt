package nz.co.redice.mygrocerylist.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nz.co.redice.mygrocerylist.domain.Item
import nz.co.redice.mygrocerylist.domain.ListRepository
import kotlin.random.Random

object ListRepositoryImpl : ListRepository {

    private val listLD = MutableLiveData<List<Item>>()
    private val list = sortedSetOf<Item>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrementId = 0

    init {
        for (i in 0 until 5) {
            val item = Item("Name $i", count = i, enabled = Random.nextBoolean())
            addItem(item)
        }
    }

    override fun addItem(item: Item) {
        if (item.id == Item.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        list.add(item)
        updateList()
    }

    override fun editItem(item: Item) {
        val oldItem = getItem(item.id)
        list.remove(oldItem)
        addItem(item)
        updateList()
    }

    override fun getItem(id: Int): Item? {
        return list.find { it.id == id }
            ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getList(): LiveData<List<Item>> {
        updateList()
        return listLD
    }

    override fun removeItem(item: Item) {
        list.remove(item)
        updateList()
    }

    private fun updateList() {
        listLD.value = list.toList()
    }
}