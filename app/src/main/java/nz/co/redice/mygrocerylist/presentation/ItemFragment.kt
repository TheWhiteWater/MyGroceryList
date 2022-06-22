package nz.co.redice.mygrocerylist.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import nz.co.redice.mygrocerylist.R
import nz.co.redice.mygrocerylist.databinding.FragmentItemBinding
import nz.co.redice.mygrocerylist.domain.Item

class ItemFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel
    private var _binding: FragmentItemBinding? = null
    private val binding: FragmentItemBinding
        get() = _binding ?: throw RuntimeException("FragmentItemBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var itemId: Int = Item.UNDEFINED_ID

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener)
            onEditingFinishedListener = context
        else
            throw RuntimeException("Activity must implement OnEditingFinishedListener interface!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()
        selectScreenMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }

    private fun selectScreenMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.etCount.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputCount()
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            }
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getItem(itemId)
        binding.saveButton.setOnClickListener {
            viewModel.editItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent!")
        }
        val sentMode = args.getString(SCREEN_MODE)
        if (sentMode != MODE_EDIT && sentMode != MODE_ADD)
            throw RuntimeException("Unknown screen mode: $sentMode")
        screenMode = sentMode

        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(ITEM_ID)) {
                throw RuntimeException("Intent launched in edit mode, but ITEM ID is absent")
            }
            itemId = args.getInt(ITEM_ID, Item.UNDEFINED_ID)
        }


    }

    companion object {
        private const val MODE_UNKNOWN: String = "unknown_mode"
        private const val SCREEN_MODE = "screen_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val ITEM_ID: String = "item_id"


        fun newInstanceAddItem(): ItemFragment {
            return ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(itemId: Int): ItemFragment {
            Log.d("APPP", "newInstanceEditItem: $MODE_EDIT")
            return ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID, itemId)
                }
            }
        }

    }


    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}