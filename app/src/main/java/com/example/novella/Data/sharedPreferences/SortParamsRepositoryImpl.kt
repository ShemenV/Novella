package com.example.novella.Data.sharedPreferences

import android.content.Context
import android.util.Log
import com.example.novella.domain.Entities.SortOrders
import com.example.novella.domain.Entities.SortParams
import com.example.novella.domain.Entities.SortTypes
import com.example.novella.domain.Repositories.SortParamsRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private const val SHARED_PREFS_NAME = "shared_prefs"
private const val KEY_PARAMS = "params"

class SortParamsRepositoryImpl(private val context: Context) : SortParamsRepository {

    val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter: JsonAdapter<SortParams> = moshi.adapter(SortParams::class.java)

    override fun saveParams(sortParams: SortParams) {


        val json = jsonAdapter.toJson(sortParams)
        Log.e("savedParams", json)

        sharedPreferences.edit().putString(KEY_PARAMS,json).apply()
    }

    override fun getParams(): SortParams? {
        var params = SortParams(SortTypes.TitleSort, SortOrders.Descending)
        try {
            val paramsJson = sharedPreferences.getString(KEY_PARAMS,"")
            params = jsonAdapter.fromJson(paramsJson)!!
        }
        catch (e:java.io.EOFException){
            Log.e("Exception", e.toString())

        }

        Log.e("gotParams",params.toString())
        return params
    }
}