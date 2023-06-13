package com.example.novella.presentation.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.novella.R
import com.example.novella.databinding.FragmentBookBinding
import com.example.novella.domain.Entities.Book
import com.example.novella.presentation.dialogs.ModalBottomSheetFragment
import com.example.novella.presentation.dialogs.SetReadPageDialogFragment
import com.example.novella.presentation.dialogs.SetReadPageListener
import com.example.novella.presentation.fragments.viewModels.BookFragmentViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate


class BookFragment : Fragment(), ModalBottomSheetFragment.ModalBottomListener,
    SetReadPageListener {
    lateinit var binding: FragmentBookBinding
    private val viewModel by viewModel<BookFragmentViewModel>()
    val args: BookFragmentArgs by navArgs()
    val selectBook: Book by lazy { args.book }
    private lateinit var setReadPageListener: SetReadPageListener
    lateinit var chooseStatusListener: ModalBottomSheetFragment.ModalBottomListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_book, container, false)
        binding.vm = viewModel

        setReadPageListener = this

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("SelectedBook", args.book.toString())

        chooseStatusListener = this

        viewModel.setSelectedBook(selectBook)

        if (selectBook.coverString != null) {
            val bitmap = BitmapFactory.decodeFile(selectBook.coverString)
            binding.coverImageView.setImageBitmap(bitmap)
        } else if (selectBook.coverUrl != null) {

            Picasso.get()
                .load(selectBook.coverUrl)
                .resize(0, 230)
                .centerCrop()
                .into(binding.coverImageView)
        }

        changeBooksStatusButton(binding.changeBookStatusExtendedFAB, selectBook.readStatus)
        val modalBottomSheet = ModalBottomSheetFragment(chooseStatusListener, selectBook.readStatus)


        binding.changeBookStatusExtendedFAB.setOnClickListener {
            modalBottomSheet.show(activity?.supportFragmentManager!!, ModalBottomSheetFragment.TAG)
        }

        val dateSetListener =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                var selectedDate = LocalDate.of(year,monthOfYear, dayOfMonth)
                viewModel.selectedBookMutable.value?.startReadDate = selectedDate
                selectedDate = selectedDate.plusMonths(1)
                binding.startReadDateEditText.setText(selectedDate.toString())
                viewModel.saveBook()
            }
        var date = viewModel.selectedBookMutable.value?.startReadDate
        if (date != null) {
            date = date.plusMonths(1)
        }
        binding.startReadDateEditText.setText(date.toString())
        binding.startReadDateEditText.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),dateSetListener,
                viewModel.selectedBookMutable.value?.startReadDate!!.year,viewModel.selectedBookMutable.value?.startReadDate!!.monthValue,viewModel.selectedBookMutable.value?.startReadDate!!.dayOfMonth)
            dialog.show()
        }




        binding.readProgressBar.max = viewModel.selectedBookMutable.value!!.pageCount
        binding.readProgressBar.progress = viewModel.selectedBookMutable.value?.readedPages!!

        binding.readProgressLayout.setOnClickListener {
            val dialog = SetReadPageDialogFragment(setReadPageListener, viewModel.selectedBookMutable.value!!.readedPages, viewModel.selectedBookMutable.value!!.pageCount)
            dialog.show(childFragmentManager, SetReadPageDialogFragment.TAG)
        }


        binding.readMoreButton.setOnClickListener {
            if(binding.readMoreButton.text == "Читать больше"){
                binding.descriptionTextView.updateLayoutParams {
                    height = WRAP_CONTENT
                }
                binding.readMoreButton.text = "Скрыть"
            }
            else if(binding.readMoreButton.text == "Скрыть"){
                binding.descriptionTextView.updateLayoutParams {
                    height = 380
                }
                binding.readMoreButton.text = "Читать больше"
            }

        }


        viewModel.selectedReadStatus.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                changeBooksStatusButton(binding.changeBookStatusExtendedFAB, it)
                viewModel.saveBook()
            }
        })
    }

    fun changeBooksStatusButton(button: Button, status: Int) {
        when (status) {
            1 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.noInLists)
                button.text = "Не добавлена"
            }
            4 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.wantRead)
                button.text = "Хочу прочитать"
            }
            3 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.readed)
                button.text = "Прочитано"
            }
            2 -> {
                button.backgroundTintList = context?.resources?.getColorStateList(R.color.readNow)
                button.text = "Читаю сейчас"
            }
        }
    }

    override fun chooseReadStatusClick(seletedStatus: Int) {
        viewModel.setSelectedReadStatus(seletedStatus)
    }

    override fun savePages(int: Int) {


        viewModel.selectedBookMutable.value?.readedPages = int
        viewModel.saveBook()

        binding.readedPagesTextView.text = viewModel.selectedBookMutable.value?.readedPages.toString()
        binding.readProgressBar.progress = viewModel.selectedBookMutable.value?.readedPages!!
    }
}