package nz.co.redice.mygrocerylist.presentation

import androidx.recyclerview.widget.DiffUtil
import nz.co.redice.mygrocerylist.domain.GroceryItem

class GroceryItemDiffCallback:DiffUtil.ItemCallback<GroceryItem>() {
    override fun areItemsTheSame(oldItem: GroceryItem, newItem: GroceryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GroceryItem, newItem: GroceryItem): Boolean {
        return newItem == oldItem
    }
}