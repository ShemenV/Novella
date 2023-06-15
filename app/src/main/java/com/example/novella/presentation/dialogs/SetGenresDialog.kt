package com.example.novella.presentation.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.core.view.get
import com.example.novella.R
import com.example.novella.domain.Entities.Genre
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SetGenresDialog(private val listener: SetGenresDialogListener,private val genresList: MutableList<Genre>, private val selectedGenresList: MutableList<Genre>): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.set_genres_dialog_layout, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val chipGroup: ChipGroup = view.findViewById(R.id.chipGroup)
        val saveButton: Button = view.findViewById(R.id.saveGenresButton)

        for(i in 0..22){
            val chip: Chip = chipGroup.getChildAt(i) as Chip
            chip.text = genresList[i].title
        }

        if(selectedGenresList != null && selectedGenresList.size > 0){
            Log.e("ABoba", selectedGenresList.map { it.id }.toString())
            for(i in selectedGenresList.map { it.id }){
                val chip: Chip = chipGroup.getChildAt(i-1) as Chip
                chip.isChecked = true
            }
        }




        saveButton.setOnClickListener {
            val genres: MutableList<Genre> = mutableListOf()
            val chips = chipGroup.checkedChipIds

            for(id in chips){
                val chip = chipGroup.findViewById<Chip>(id)
                for(i in genresList){
                    if(chip.text == i.title){
                        genres.add(i)
                    }
                }
            }

            listener.saveGenres(genres)
            this@SetGenresDialog.dismiss()
        }
    }


    companion object {
        const val TAG = "SetGenresDialog"
    }
}

interface SetGenresDialogListener{
    fun saveGenres(genresList: MutableList<Genre>)
}