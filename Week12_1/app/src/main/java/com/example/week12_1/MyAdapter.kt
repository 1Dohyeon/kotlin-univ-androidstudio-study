package com.example.week12_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.week12_1.databinding.ListItemBinding

class MyAdapter(private var dataSet: MutableList<MyElement>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    fun setList(newList: MutableList<MyElement>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }

    fun getElement(pos: Int): MyElement {
        return dataSet[pos]
    }

    private lateinit var itemClickListner: OnItemClickListner
    interface OnItemClickListner {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListner: OnItemClickListner) {
        this.itemClickListner = onItemClickListner
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.rank.text = dataSet[position].rank.toString()
        binding.albumCover.setImageDrawable(
            ContextCompat.getDrawable(binding.root.context, R.drawable.ic_launcher_background)
        )
        binding.title.text = dataSet[position].title
        binding.artist.text = dataSet[position].artist
        binding.album.text = dataSet[position].album
        binding.numLike.text = dataSet[position].num_like.toString()
        binding.elem.setOnClickListener{
            itemClickListner.onClick(it, position)
        }
    }
}
