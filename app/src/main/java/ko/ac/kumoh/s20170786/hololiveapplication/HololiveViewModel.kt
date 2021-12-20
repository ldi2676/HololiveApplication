package ko.ac.kumoh.s20170786.hololiveapplication

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONObject

class HololiveViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }

    private lateinit var mQueue: RequestQueue

    data class Hololive_company (
        val id : Int,
        val name : String,
        val company : String,
        val division : String,
        val team : String
            )

    val list = MutableLiveData<ArrayList<Hololive_company>>()
    private val holo_mem = ArrayList<Hololive_company>()

    init {
        list.value = holo_mem
        mQueue = VolleyRequest.getInstance(application).requestQueue
    }

    override fun onCleared() {
        super.onCleared()
        mQueue.cancelAll(QUEUE_TAG)
    }

    fun getHolo_mem(i:Int) = holo_mem[i]

    fun getSize() = holo_mem.size

    fun requestHololive() {
        // NOTE: 서버 주소는 본인의 서버 주소 사용할 것
        val url = "https://expresssongdb-ysgby.run.goorm.io/hololive_company"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                //.result.text = it.toString()
                //Toast.makeText(getApplication(),it.toString(),Toast.LENGTH_LONG).show()
                holo_mem.clear()
                parseHololive_companyJSON(it)
                list.value = holo_mem

            },
            {
                Toast.makeText(getApplication(),it.toString(),Toast.LENGTH_LONG).show()
                //binding.result.text = it.toString()
            }
        )

        request.tag = QUEUE_TAG
        mQueue.add(request)
    }

    private fun parseHololive_companyJSON(items: JSONArray) {
        for (i in 0 until items.length()) {
            val item: JSONObject = items.getJSONObject(i)
            val id = item.getInt("id")
            val name = item.getString("name")
            val company = item.getString("company")
            val division = item.getString("division")
            val team = item.getString("team")
            //val height = item.getDouble("height")
            //val weight = item.getDouble("weight")
            holo_mem.add(
                Hololive_company(id, name, company, division, team)
            )
        }
    }
}