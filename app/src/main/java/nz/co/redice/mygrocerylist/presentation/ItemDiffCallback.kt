package nz.co.redice.mygrocerylist.presentation

import androidx.recyclerview.widget.DiffUtil
import nz.co.redice.mygrocerylist.domain.Item

class ItemDiffCallback:DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return newItem == oldItem
    }
}