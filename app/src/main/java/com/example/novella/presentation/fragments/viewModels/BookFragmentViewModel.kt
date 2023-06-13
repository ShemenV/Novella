package com.example.novella.presentation.fragments.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksIdsUseCase
import com.example.novella.domain.usecases.SaveBookUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import java.time.LocalDate

class BookFragmentViewModel(
    val saveBookUseCase: SaveBookUseCase,
    private val getBooksIdsUseCase: GetBooksIdsUseCase
) : ViewModel(){
    val selectedBookMutable: MutableLiveData<Book> = MutableLiveData<Book>()
    val selectedReadStatus: MutableLiveData<Int> = MutableLiveData<Int>()




    init {
        selectedReadStatus.value = selectedBookMutable.value?.readStatus

    }

    fun setSelectedBook(book: Book) {
        selectedBookMutable.value = book
    }

    fun setSelectedReadStatus(status: Int) {
        selectedReadStatus.value = status
        selectedBookMutable.value?.readStatus = selectedReadStatus.value!!
    }


    fun saveBook() {
        viewModelScope.launch{
            var byteArray:ByteArray? = null

            if(selectedBookMutable.value?.coverUrl != null){
                val imageUrl: URL = URL(selectedBookMutable.value?.coverUrl)

                withContext(Dispatchers.IO) {
                    val urlConnection: URLConnection =imageUrl.openConnection()
                    val inputStream: InputStream = urlConnection.getInputStream()
                    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()

                    inputStream.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }

                    byteArray = outputStream.toByteArray()

                    val mFolder = File("/data/data/com.example.novella/files/images")
                    val imgFile = File(mFolder.absolutePath + "/" + "${ selectedBookMutable.value?.id}.png")

                    val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray!!.size)
                    if (!mFolder.exists()) {
                        mFolder.mkdir()
                    }
                    if (!imgFile.exists()) {
                        withContext(Dispatchers.IO) {
                            imgFile.createNewFile()
                        }
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
            }
                selectedBookMutable.value?.coverString = "/data/data/com.example.novella/files/images/${ selectedBookMutable.value?.id}.png"
                }


            if(selectedReadStatus.value == 2 && selectedBookMutable.value?.startReadDate == null) {
                selectedBookMutable.value?.startReadDate = LocalDate.now()
            }

            if(selectedReadStatus.value == 3 && selectedBookMutable.value?.finishReadDate == null){
                selectedBookMutable.value?.finishReadDate = LocalDate.now()
            }

            saveBookUseCase.execute(selectedBookMutable.value!!)
        }
    }


}