 package com.example.hw3_m5

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw3_m5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity : AppCompatActivity() {
    private var adapter = ImageAdapter(mutableListOf())
    private lateinit var binding: ActivityMainBinding
    private var pageCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        with(binding) {
            btnSearch.setOnClickListener {
                firstRequestImages()
            }
            btnMore.setOnClickListener {
                pageCount++
                requestImages()
            }

            binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE
                    ) {
                        pageCount++
                        requestImages()
                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.firstRequestImages() {
        RetrofitService.api.getImages(keyWord = etImage.text.toString(), page = pageCount)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            adapter.reloadData(it.hits)
                            recyclerView.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("lalala", "onFailure: ${t.message}")
                }

            })
    }

    private fun ActivityMainBinding.requestImages() {
        RetrofitService.api.getImages(keyWord = etImage.text.toString(), page = pageCount)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            adapter.addData(it.hits)
                            recyclerView.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("lalala", "onFailure: ${t.message}")
                }

            })
    }

}