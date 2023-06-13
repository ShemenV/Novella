package com.example.novella.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.novella.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout

class SetReadPageDialogFragment(
    private val listener: SetReadPageListener,
    private val startCount: Int,
    private val maxCount: Int
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.set_read_page_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pageCountTextView: TextInputLayout = view.findViewById(R.id.filledTextField)
        val saveButton: Button = view.findViewById(R.id.savePageCountButton)

        pageCountTextView.editText?.setText(startCount.toString())

        saveButton.setOnClickListener {
            var result = pageCountTextView.editText?.text.toString()

            if (result.isNullOrEmpty())
                result = startCount.toString()

            if (result.toInt() > maxCount) {
                Toast.makeText(
                    requireContext(),
                    "Значение превышает общее количество страниц",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            listener.savePages(result.toInt())
            this@SetReadPageDialogFragment.dismiss()
        }

    }

    companion object {
        const val TAG = "SetReadPagesDialog"
    }
}

interface SetReadPageListener {
    fun savePages(int: Int)
}

