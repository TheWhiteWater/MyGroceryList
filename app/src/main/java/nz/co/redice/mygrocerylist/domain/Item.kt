package nz.co.redice.mygrocerylist.domain

data class Item(
    val name: String,
    val count: Int,
    var enabled: Boolean = true,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }


}
