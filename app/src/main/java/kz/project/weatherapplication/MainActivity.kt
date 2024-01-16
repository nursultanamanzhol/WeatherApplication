package kz.project.weatherapplication

import android.app.VoiceInteractor.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kz.project.weatherapplication.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "98969e0189664392992203333241501"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.get.setOnClickListener {
            getResult("Almaty")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
            url,
            {
                    response ->
                val obj = JSONObject(response)
                val temp =  obj.getJSONObject("current")
                Log.d("MyLog", "$name temp_c: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog", "Volley error: $it")
            })
        queue.add(stringRequest)
    }
}