package com.learning.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.R
import com.learning.apis.Const.Companion.USER_ID
import com.learning.ui.adapter.MoviesRecyclerAdapter
import com.learning.databinding.ActivityMainBinding
import com.learning.utills.SharedPrf
import com.learning.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity   : Fragment() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var moviesAdapter : MoviesRecyclerAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var sharedPrf: SharedPrf
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        activityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)
        setUpViewModel()
        sharedPrf = SharedPrf(requireContext())
        setUpRecyclerView()
        mainViewModel.getMoviesFromAPI("x")
        mainViewModel.responseContainer.observe(requireActivity(), Observer {
            if (it != null){

                moviesAdapter.result = it.result
            //    activityMainBinding.validationTextForSearch.visibility = View.GONE

            }else{
                Toast.makeText(requireContext(), "There is some error!", Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.isShowProgress.observe(requireActivity()) {
            if (it) {
                activityMainBinding.mainProgressBar.visibility = View.VISIBLE
                //   activityMainBinding.searchGoBtn.visibility = View.GONE
            } else {
                activityMainBinding.mainProgressBar.visibility = View.GONE
                //  activityMainBinding.validationTextForSearch.visibility = View.GONE
            }
        }


        mainViewModel.errorMessage.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        activityMainBinding.searchGoBtn.setOnClickListener { it->
            if(activityMainBinding.searchBar.text.isEmpty()){
                Toast.makeText(requireActivity(), "Please Enter ", Toast.LENGTH_SHORT).show()
            }
            else{
                val searchText = activityMainBinding.searchBar.text

                mainViewModel.getMoviesFromAPI(searchText.toString())

            }

        }
return  activityMainBinding.root
    }

    private fun setUpRecyclerView() = activityMainBinding.movieRecyclerView.apply {
        moviesAdapter = MoviesRecyclerAdapter()
        adapter = moviesAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpViewModel(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}