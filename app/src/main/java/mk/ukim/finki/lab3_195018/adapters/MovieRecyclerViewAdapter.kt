package mk.ukim.finki.lab3_195018.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.ukim.finki.lab3_195018.R
import mk.ukim.finki.lab3_195018.models.Data

class MovieRecyclerViewAdapter(private val context: Context, private var movies: MutableList<Data>, private val onMovieClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val onMovieClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val poster: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.movieTitle)
        val year: TextView = view.findViewById(R.id.movieYear)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            onMovieClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return ViewHolder(view) { position ->
            onMovieClick(
                position
            )
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movies[position]

        Glide.with(context).load(currentMovie.Poster).into(holder.poster)
        holder.title.text = "title: ${currentMovie.Title}"
        holder.year.text = "year: ${currentMovie.Year}"
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateData(data: MutableList<Data>){
        this.movies = data
        this.notifyDataSetChanged()
    }
}