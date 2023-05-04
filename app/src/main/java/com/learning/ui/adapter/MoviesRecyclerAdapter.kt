package com.learning.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.R
import com.learning.Results

import com.learning.databinding.RawMoviesItemLayoutBinding

class MoviesRecyclerAdapter : RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {

   inner class MoviesViewHolder(private val rawMoviesItemLayoutBinding: RawMoviesItemLayoutBinding) :
       RecyclerView.ViewHolder(rawMoviesItemLayoutBinding.root) {

           fun bind(results: Results){

               rawMoviesItemLayoutBinding.apply {
                   Glide.with(root.context)
                       .load(results.Image)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .into(imageOfMovies)

                   titleOfMovie.text = results.First_name
                   descriptionOfMovie.text = results.Bio
               }

           }

   }

   private val diffCallback = object : DiffUtil.ItemCallback<Results>()
   {
       override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
           return oldItem.Id == newItem.Id
       }

       override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
           return oldItem == newItem
       }
   }

   private val differ = AsyncListDiffer(this, diffCallback)

   var result : List<Results>
   get() = differ.currentList
   set(value){
       differ.submitList(value)
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
       return MoviesViewHolder(RawMoviesItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
           parent,
           false))
   }

   override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       holder.bind(result[position])
   }

   override fun getItemCount() = result.size

}