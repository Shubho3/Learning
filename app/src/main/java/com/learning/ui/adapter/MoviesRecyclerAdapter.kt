package com.learning.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.IMDB
import com.learning.R

import com.learning.databinding.RawMoviesItemLayoutBinding

class MoviesRecyclerAdapter : RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {
   inner class MoviesViewHolder(private val rawMoviesItemLayoutBinding: RawMoviesItemLayoutBinding) :
       RecyclerView.ViewHolder(rawMoviesItemLayoutBinding.root) {
           fun bind(results: IMDB.Result){
               rawMoviesItemLayoutBinding.apply {
                   Glide.with(root.context)
                       .load(results.image)
                       .placeholder(R.drawable.ic_launcher_foreground)
                       .into(imageOfMovies)
                   titleOfMovie.text = results.first_name
                   descriptionOfMovie.text = results.date_time
                   root.setOnClickListener {
                       Navigation.findNavController(root).navigate(R.id.action_listFragment_to_detailsFragment)
                   }
               }

           }

   }

   private val diffCallback = object : DiffUtil.ItemCallback<IMDB.Result>()
   {
       override fun areItemsTheSame(oldItem: IMDB.Result, newItem: IMDB.Result): Boolean {
           return oldItem.id == newItem.id
       }

       override fun areContentsTheSame(oldItem: IMDB.Result, newItem: IMDB.Result): Boolean {
           return oldItem == newItem
       }
   }

   private val differ = AsyncListDiffer(this, diffCallback)

   var result : List<IMDB.Result>
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