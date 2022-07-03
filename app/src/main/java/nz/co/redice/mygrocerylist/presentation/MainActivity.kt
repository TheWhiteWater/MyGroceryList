package nz.co.redice.mygrocerylist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.databinding.ActivityMainBinding
import nz.co.redice.mygrocerylist.di.ItemApplication
import nz.co.redice.mygrocerylist.domain.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ItemApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.list.observe(this) {
            listAdapter.submitList(it)
        }

        binding.buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                startActivity(ItemActivity.newIntentAddItem(this))
            } else {
                launchFragment(ItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.itemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.item_container, fragment)
            .addToBackStack(null)
            .commit()
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
            if (isOnePaneMode())
                startActivity(ItemActivity.newIntentEditItem(this, it.id))
            else
                launchFragment(ItemFragment.newInstanceEditItem(it.id))
        }
    }

    private fun setupLongClickListener() {
        this.listAdapter.onGroceryItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditingFinished() {
        supportFragmentManager.popBackStack()
    }


}
