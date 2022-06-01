package nz.co.redice.mygrocerylist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import nz.co.redice.mygrocerylist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var groceryListAdapter: GroceryListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.groceryList.observe(this) {
            groceryListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvGroceryList = findViewById<RecyclerView>(R.id.rv_shop_list)
        groceryListAdapter = GroceryListAdapter()
        with(rvGroceryList) {
            adapter = groceryListAdapter
            recycledViewPool.setMaxRecycledViews(
                GroceryListAdapter.ACTIVE_VIEW_TYPE,
                GroceryListAdapter.MAX_PULL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                GroceryListAdapter.INACTIVE_VIEW_TYPE,
                GroceryListAdapter.MAX_PULL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeRemove(rvGroceryList)
    }

    private fun setupSwipeRemove(rvGroceryList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = groceryListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeGroceryItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvGroceryList)
    }

    private fun setupClickListener() {
        groceryListAdapter.onGroceryItemClickListener = {
        }
    }

    private fun setupLongClickListener() {
        this.groceryListAdapter.onGroceryItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}
