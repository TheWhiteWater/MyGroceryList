package nz.co.redice.mygrocerylist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.domain.Item

class ItemActivity : AppCompatActivity() {

    private var screenMode: String = MODE_UNKNOWN
    private var itemId: Int = Item.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        parseIntent()
        if (savedInstanceState == null) {
            selectScreenMode()
        }
    }


    private fun selectScreenMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> ItemFragment.newInstanceEditItem(itemId)
            MODE_ADD -> ItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.item_container, fragment)
            .commit()

    }


    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
            throw RuntimeException("Intent has no proper extra")
        val sentIntentMode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (sentIntentMode != MODE_EDIT && sentIntentMode != MODE_ADD)
            throw RuntimeException("Unknown screen mode: $sentIntentMode")
        screenMode = sentIntentMode

        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Intent launched in edit mode, but ITEM ID is absent")
            }
            itemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, Item.UNDEFINED_ID)
        }
    }


    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private var MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, itemId: Int): Intent {
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, itemId)
            return intent
        }
    }
}