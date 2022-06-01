package nz.co.redice.mygrocerylist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.domain.GroceryItem

class GroceryListAdapter : ListAdapter<GroceryItem, GroceryItemViewHolder>(
    GroceryItemDiffCallback()) {

    companion object {
        const val ACTIVE_VIEW_TYPE = 1
        const val INACTIVE_VIEW_TYPE = 2
        const val MAX_PULL_SIZE = 15
    }

    var onGroceryItemLongClickListener: ((GroceryItem) -> Unit)? = null
    var onGroceryItemClickListener: ((GroceryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemViewHolder {

        val layoutId = when (viewType) {
            ACTIVE_VIEW_TYPE -> R.layout.item_shop_enabled
            INACTIVE_VIEW_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GroceryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryItemViewHolder, position: Int) {
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

