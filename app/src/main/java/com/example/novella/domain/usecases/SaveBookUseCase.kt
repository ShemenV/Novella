package com.example.novella.domain.usecases

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.example.novella.Data.Room.Repository.RoomBookRepositoryImpl
import com.example.novella.domain.Entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class SaveBookUseCase(private val repository:RoomBookRepositoryImpl) {
    suspend fun execute(book: Book){
        val idsList = repository.getIds()

        if (idsList.contains(book.id)) {
            Log.e("vvvuivui","hohohohoho")
            repository.updateBook(book)
        }
        else{
            if (book.cover == null && book.coverUrl != null) {

                val imageUrl: URL = URL(book.coverUrl)

                withContext(Dispatchers.IO) {
                    val urlConnection: URLConnection =imageUrl.openConnection()
                    val inputStream: InputStream = urlConnection.getInputStream()
                    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()

                    inputStream.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }

                    convertBitmapToFile(context,outputStream.toByteArray(),book.id.toString())
                    book.cover = "${book.id.toString()}.png"
                }
            }
            Log.e("vvvuivui","hihihihi")
            repository.saveBook(book)
        }

    }


    fun convertBitmapToFile(context: Context, bitmap: Bitmap, fileName:String): Uri {
        val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileName)
        file.createNewFile()
        // Convert bitmap to byte array
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos) // It can be also saved it as JPEG
        val bitmapdata = baos.toByteArray()
    }


}