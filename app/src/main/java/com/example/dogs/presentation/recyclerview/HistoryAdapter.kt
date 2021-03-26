package com.example.dogs.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.data.entities.DogPicture
import com.example.dogs.databinding.ItemPictureBinding
import com.squareup.picasso.Picasso

class HistoryAdapter : ListAdapter<DogPicture, HistoryAdapter.PictureViewHolder>(object :
    DiffUtil.ItemCallback<DogPicture>() {
    override fun areItemsTheSame(oldItem: DogPicture, newItem: DogPicture): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DogPicture, newItem: DogPicture): Boolean = oldItem == newItem


}) {
    class PictureViewHolder(private val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: DogPicture) {
            with(binding) {
                Picasso.get()
                    .load(picture.url)
                    .fit()
                    .into(ivPicture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder =
        PictureViewHolder(
            ItemPictureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) = holder.bind(getItem(position))
}