package nz.co.redice.mygrocerylist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "shop_items")
class ItemDbModel (
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    var enabled: Boolean = true
)