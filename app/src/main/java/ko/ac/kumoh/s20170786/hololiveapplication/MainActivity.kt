package ko.ac.kumoh.s20170786.hololiveapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import ko.ac.kumoh.s20170786.hololiveapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }

    private lateinit var mQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQueue = VolleyRequest.getInstance(this.applicationContext).requestQueue

        requestHololive()
    }

    override fun onStop() {
        super.onStop()
        mQueue.cancelAll(QUEUE_TAG)
    }

    private fun requestHololive() {
        // NOTE: 서버 주소는 본인의 서버 주소 사용할 것
        val url = "https://expresssongdb-ysgby.run.goorm.io/hololive_company"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                binding.result.text = it.toString()
            },
            {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                binding.result.text = it.toString()
            }
        )

        request.tag = QUEUE_TAG
        mQueue.add(request)
    }


}