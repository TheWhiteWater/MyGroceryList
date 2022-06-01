package nz.co.redice.mygrocerylist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.domain.GroceryItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var groceryListAdapter: GroceryListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.groceryList.observe(this) {
            setupRecyclerView()
            groceryListAdapter.groceryList = it
        }
    }

    private fun setupRecyclerView() {
        val rvGroceryList = findViewById<RecyclerView>(R.id.rv_shop_list)
        groceryListAdapter = GroceryListAdapter()
        with(rvGroceryList) {
            adapter = groceryListAdapter
            recycledViewPool.setMaxRecycledViews(
                GroceryListAdapter.ACTIVE_TYPE,
                GroceryListAdapter.MAX_PULL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                GroceryListAdapter.INACTIVE_TYPE,
                GroceryListAdapter.MAX_PULL_SIZE
            )
        }
        groceryListAdapter.onGroceryItemLongClickListener = {
            viewModel.changeEnableState(it)
        }

    }
}
