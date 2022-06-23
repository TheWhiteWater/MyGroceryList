package nz.co.redice.mygrocerylist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemListDAO {

    @Query("SELECT * FROM shop_items")
    fun getItemList(): LiveData<List<ItemDbModel>>

    @Query("SELECT * FROM shop_items WHERE id=:itemId LIMIT 1")
     fun getItem(itemId: Int): ItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addItem(item: ItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:itemId")
     fun deleteItem(itemId: Int)


}