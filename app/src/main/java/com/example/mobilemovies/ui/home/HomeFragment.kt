package com.example.mobilemovies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilemovies.Data.Movie
import com.example.mobilemovies.databinding.FragmentHomeBinding
import com.example.mobilemovies.databinding.MovieViewHolderBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val recyclerView: RecyclerView = binding.nowPlayingMovieCollection

        binding.textHome

        homeViewModel.currentMovies?.observe(
            viewLifecycleOwner, {
                    movies -> recyclerView.apply{
                run {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MovieAdapter(movies)
                }
            }
            }
        )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private inner class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val binding = MovieViewHolderBinding.inflate(layoutInflater)
            return ViewHolder(binding)

           // val v = layoutInflater.inflate(R.layout.movie_view_holder,
        // parent,
        // false)
          //  return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(movies[position])
        }

        override fun getItemCount(): Int {
            return movies.size
        }

    }
    inner class ViewHolder(private val binding: MovieViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                movieTitle.text = item.title
                executePendingBindings()
            }
        }
    }

}



