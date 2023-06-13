package com.example.novella.presentation.fragments.viewModels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksByIdUseCase
import com.example.novella.domain.usecases.GetBooksIdsUseCase
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class EditBookFragmentViewModel(private val saveBookUseCase: SaveBookUseCase,
private val getBooksByIdUseCase: GetBooksByIdUseCase,
private val getBooksIdsUseCase: GetBooksIdsUseCase):ViewModel() {
    val editableBookMutable: MutableLiveData<Book> = MutableLiveData()
    val editableBookPageCountStringMutable:MutableLiveData<String> = MutableLiveData()
    val imageMutable: MutableLiveData<Bitmap> = MutableLiveData()


    init {
        if(editableBookMutable.value?.pageCount != null){
            editableBookPageCountStringMutable.value = editableBookMutable.value!!.pageCount!!.toString()
        }
    }

    fun setPageCount(){

            if(editableBookPageCountStringMutable.value.equals("")){
                editableBookPageCountStringMutable.value = "0"
            }

        editableBookMutable.value!!.pageCount = editableBookPageCountStringMutable.value?.toInt()!!
    }

    fun updateBook() {
        viewModelScope.launch {
//            var newId:String = ""
//            if(editableBookMutable.value!!.id == null){
//                newId = generateId()
//                Log.e("tt",newId)
//                Log.e("ABOBA", getBooksByIdUseCase.execute("ZxlkehxIZmAC").toString())
//                while(getBooksByIdUseCase.execute(newId) != 0 && !getBooksIdsUseCase.execute().contains(newId)){
//                    newId = generateId()
//                }
//            }
//            editableBookMutable.value!!.id = newId
            if(imageMutable.value != null){
                saveImage(bitmap = imageMutable.value!!)
            }
            Log.e("EditBook",editableBookMutable.value.toString())
            saveBookUseCase.execute(editableBookMutable.value)
        }
    }

    fun saveImage(bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO){
            val mFolder = File("/data/data/com.example.novella/files/images")
            val imgFile = File(mFolder.absolutePath + "/" + "${editableBookMutable.value?.id}.png")

            if (!mFolder.exists()) {
                mFolder.mkdir()
            }
            if (!imgFile.exists()) {
                imgFile.createNewFile()
            }

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(imgFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            editableBookMutable.value?.coverString = "/data/data/com.example.novella/files/images/${editableBookMutable.value?.id}.png"
        }
    }
}