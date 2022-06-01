package nz.co.redice.mygrocerylist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.domain.GroceryItem

class GroceryListAdapter : RecyclerView.Adapter<GroceryListAdapter.GroceryItemViewHolder>() {

    companion object {
        const val ACTIVE_TYPE = 1
        const val INACTIVE_TYPE = 2
        const val MAX_PULL_SIZE = 10
    }

    var onGroceryItemLongClickListener: ((GroceryItem) -> Unit)? = null
    var groceryList = listOf<GroceryItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemViewHolder {

        val layoutId = when (viewType) {
            ACTIVE_TYPE -> R.layout.item_shop_enabled
            INACTIVE_TYPE -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GroceryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryItemViewHolder, position: Int) {
        val item = groceryList[position]
        holder.itemView.setOnLongClickListener {
            onGroceryItemLongClickListener?.invoke(item)
            true
        }
        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()

    }

    override fun getItemCount(): Int {
        return groceryList.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (groceryList[position].enabled) ACTIVE_TYPE else INACTIVE_TYPE
    }

    class GroceryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }


}

