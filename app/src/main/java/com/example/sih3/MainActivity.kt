package com.example.sih3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.sih3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

//    http://15.207.3.65:5000/get_data
//    http://3.109.224.64:8080/motor/status
//    http://3.109.224.64:8080/motor/update


lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()


        getStatus()

        binding.statusbtn.setOnClickListener {
            postUpdate()

        }

    }

//    for IOT device Data

    private fun getData() {
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<ResponseData?> {
            override fun onResponse(call: Call<ResponseData?>, response: Response<ResponseData?>) {
                binding.humidity.text=response.body()?.humidity
                binding.temperature.text=response.body()?.temperature
                binding.rain.text=response.body()?.rain.toString()
                binding.time.text=response.body()?.time
                binding.soilmoisture.text=response.body()?.soilmoisture
            }

            override fun onFailure(call: Call<ResponseData?>, t: Throwable) {
               Toast.makeText(this@MainActivity,
                   t.localizedMessage
                   ,Toast.LENGTH_SHORT)
                   .show()
            }
        })
    }

//    for motor Status

    private fun getStatus() {
        RetrofitInstance.apiInterface2.getStatus()
            .enqueue(object : Callback<StatusResponseData?> {
            override fun onResponse(
                call: Call<StatusResponseData?>,
                response: Response<StatusResponseData?>
            ) {
                binding.status.text=response.body()?.status
                if(binding.status.text == "on"){
                    binding.statusbtn.text="off"
                    binding.statusbtn
                        .setBackgroundColor(ContextCompat
                            .getColor(this@MainActivity,R.color.black))
                    binding.statusbtn.setTextColor(ContextCompat
                        .getColor(this@MainActivity,R.color.white))


                }
                else{
                    binding.statusbtn.text="on"
                    binding.statusbtn
                        .setBackgroundColor(ContextCompat
                            .getColor(this@MainActivity,R.color.white))
                    binding.statusbtn.setTextColor(ContextCompat
                        .getColor(this@MainActivity,R.color.black))
                }
            }

            override fun onFailure(call: Call<StatusResponseData?>, t: Throwable) {
                Toast.makeText(this@MainActivity,
                    t.localizedMessage
                    ,Toast.LENGTH_SHORT)
                    .show()
//                Log.e("ambit",t.localizedMessage)
            }
        })
    }

   private fun postUpdate(){

       RetrofitInstance.apiInterface2.postUpdate(StatusResponseData(status = binding.statusbtn.text.toString()))
           .enqueue(object : Callback<StatusResponseData?> {
           override fun onResponse(
               call: Call<StatusResponseData?>,
               response: Response<StatusResponseData?>
           ) {

                getStatus()
           }

           override fun onFailure(call: Call<StatusResponseData?>, t: Throwable) {
               Toast.makeText(this@MainActivity,
                   t.localizedMessage
                   ,Toast.LENGTH_SHORT)
                   .show()
           }
       })
   }
//    private fun change() {
//        if (binding.statusbtn.text == "off") {
//            binding.statusbtn.text = buildString {
//                append("on")
//            }
//        } else {
//            binding.statusbtn.text = buildString {
//                append("off")
//
//            }
//        }
//        getStatus()
//    }
}