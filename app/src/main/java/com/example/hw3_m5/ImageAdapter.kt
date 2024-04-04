package com.example.hw3_m5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.hw3_m5.databinding.ItemImageBinding

class ImageAdapter(val list: MutableList<ImageModel>) : Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun addData(newData: List<ImageModel>) {
        val lastOne = list.size
        list.addAll(newData)
        notifyItemRangeInserted(lastOne, newData.size)
    }

    fun reloadData(newData: List<ImageModel>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

}

class ImageViewHolder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
    fun onBind(model: ImageModel) {
        binding.itemImage.load(model.largeImageURL)
    }
}
