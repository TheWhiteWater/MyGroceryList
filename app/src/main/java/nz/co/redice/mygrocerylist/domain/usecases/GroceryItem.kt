package nz.co.redice.mygrocerylist.domain.usecases

data class GroceryItem(
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)
