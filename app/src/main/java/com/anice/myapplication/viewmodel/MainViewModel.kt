package com.anice.myapplication.viewmodel

import com.anice.myapplication.model.Goods
import com.anice.myapplication.common.Utils
import com.anice.myapplication.viewmodel.base.BaseViewModel
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONException
import java.util.*
import javax.inject.Inject


class MainViewModel @Inject constructor() : BaseViewModel() {
    var listdata: List<Goods> = listOfNotNull(null)

suspend fun fetchGoods(): List<Goods> {
            try {
                delay(5000)
                val jArray = JSONArray(Utils.loadJSONFromAsset("serverResponse.json"))
                listdata = ArrayList<Goods>()
                if (jArray != null) {
                    for (i in 0 until jArray.length()) {
                        (listdata as ArrayList<Goods>).add(
                            Goods(
                                jArray.getJSONObject(i).getString("id"),
                                jArray.getJSONObject(i).getString("title"),
                                jArray.getJSONObject(i).getString("categoryColor")
                            )
                        )
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        return  listdata
   }

    suspend fun changeCategory(goodID: String, color: String,position: Int): List<Goods> {
        delay(3000)

        listdata[position].categoryColor=color
return listdata
    }


}
