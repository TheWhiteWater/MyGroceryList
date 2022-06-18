package nz.co.redice.mygrocerylist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.domain.Item

class ListAdapter : ListAdapter<Item, ItemViewHolder>(
    ItemDiffCallback()) {

    companion object {
        const val ACTIVE_VIEW_TYPE = 1
        const val INACTIVE_VIEW_TYPE = 2
        const val MAX_PULL_SIZE = 15
    }

    var onGroceryItemLongClickListener: ((Item) -> Unit)? = null
    var onGroceryItemClickListener: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val layoutId = when (viewType) {
            ACTIVE_VIEW_TYPE -> R.layout.item_shop_enabled
            INACTIVE_VIEW_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnLongClickListener {
            onGroceryItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener {
            onGroceryItemClickListener?.invoke(item)
        }
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) ACTIVE_VIEW_TYPE else INACTIVE_VIEW_TYPE
    }


}

