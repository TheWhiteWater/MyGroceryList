package nz.co.redice.mygrocerylist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import nz.co.redice.mygrocerylist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var listAdapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.list.observe(this) {
            listAdapter.submitList(it)
        }

        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            startActivity(ItemActivity.newIntentAddItem(this))
        }
    }

    private fun setupRecyclerView() {
        val rvGroceryList = findViewById<RecyclerView>(R.id.rv_shop_list)
        listAdapter = ListAdapter()
        with(rvGroceryList) {
            adapter = listAdapter
            recycledViewPool.setMaxRecycledViews(
                ListAdapter.ACTIVE_VIEW_TYPE,
                ListAdapter.MAX_PULL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ListAdapter.INACTIVE_VIEW_TYPE,
                ListAdapter.MAX_PULL_SIZE
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
                val item = listAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvGroceryList)
    }

    private fun setupClickListener() {
        listAdapter.onGroceryItemClickListener = {
            startActivity(ItemActivity.newIntentEditItem(this, it.id))
        }
    }

    private fun setupLongClickListener() {
        this.listAdapter.onGroceryItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}
