package com.example.novella.presentation.fragments.viewModels


import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novella.domain.Entities.Book
import com.example.novella.domain.usecases.GetBooksCountUseCase
import com.example.novella.domain.usecases.GetReadBooksListUseCase
import com.example.novella.domain.usecases.GetTotalPagesCountUseCase
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfName.BaseFont
import com.itextpdf.kernel.pdf.PdfName.Document
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AnalysisFragmentViewModel(
    private val getBooksCountUseCase: GetBooksCountUseCase,
    private val getTotalPagesCountUseCase: GetTotalPagesCountUseCase,
    private val getReadBooksListUseCase: GetReadBooksListUseCase,
) : ViewModel() {

    val totalBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val readNowBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val wantReadBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val readedBooksCount: MutableLiveData<Int> = MutableLiveData<Int>()
    val totalPagesCount: MutableLiveData<Int?> = MutableLiveData<Int?>()
    val booksList: MutableLiveData<MutableList<Book?>> = MutableLiveData()


    companion object{
        private const val FONT = "/assets/Roboto-Regular.ttf"
    }

    fun setBooksList() {
        viewModelScope.launch {
            booksList.value = getReadBooksListUseCase.execute()
        }
    }

    fun setBooksCount() {
        viewModelScope.launch {
            totalBooksCount.value = getBooksCountUseCase.execute()
            readedBooksCount.value = getBooksCountUseCase.execute(3)
            wantReadBooksCount.value = getBooksCountUseCase.execute(4)
            readNowBooksCount.value = getBooksCountUseCase.execute(2)
        }
    }

    fun setTotalPagesCount() {
        viewModelScope.launch {
            totalPagesCount.value = getTotalPagesCountUseCase.execute()
        }
    }

    fun generatePdf() {
        viewModelScope.launch(Dispatchers.IO) {

            val fileName = SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault())
                .format(System.currentTimeMillis())
            val filePath = "${Environment.getExternalStorageDirectory()}/${fileName}.pdf"

            try {

                val booksList = getReadBooksListUseCase.execute()

                val pdfWriter: PdfWriter = PdfWriter(filePath)
                val pdfDocument = PdfDocument(pdfWriter)

                val document = Document(pdfDocument)
                val data = "Библиотека книг из приложениея Novella"

                val baseFont = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H)
                val boldFont = PdfFontFactory.createFont("/assets/Roboto-Bold.ttf", PdfEncodings.IDENTITY_H)

                document.add(
                    Paragraph(data)
                        .setFont(boldFont)
                        .setBold()
                        .setFontSize(30f)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                )

                val table = Table(4)
                    .setFont(baseFont)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setFontSize(16f)

                table.addCell("Название")
                    .setFont(boldFont)
                    .setBold()

                table.addCell("Автор")
                    .setFont(boldFont)
                    .setBold()

                table.addCell("Издатель")
                    .setFont(boldFont)
                    .setBold()

                table.addCell("Количество страниц")
                    .setFont(boldFont)
                    .setBold()


                for(item in booksList){
                    table.addCell(item?.title)
                        .setFont(baseFont)
                    table.addCell(item?.author)
                        .setFont(baseFont)
                    table.addCell(item?.publisher)
                        .setFont(baseFont)
                    table.addCell(item?.pageCount.toString())
                        .setFont(baseFont)
                }

                document.add(table)

                document.close()

            } catch (e: Exception) {
                Log.e("EXCEPTION", e.toString())
            }
        }


    }

}