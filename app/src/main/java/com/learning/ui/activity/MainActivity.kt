package com.learning.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.apis.Const.Companion.USER_ID
import com.learning.ui.adapter.MoviesRecyclerAdapter
import com.learning.databinding.ActivityMainBinding
import com.learning.utills.SharedPrf
import com.learning.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity   : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var moviesAdapter : MoviesRecyclerAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var sharedPrf: SharedPrf
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setUpViewModel()
        sharedPrf = SharedPrf(this)
        sharedPrf.setStoredTag(USER_ID,"1136")
        setUpRecyclerView()
        mainViewModel.getMoviesFromAPI("x")
        mainViewModel.responseContainer.observe(this, Observer {
            if (it != null){

                moviesAdapter.result = it.results
            //    activityMainBinding.validationTextForSearch.visibility = View.GONE

            }else{
                Toast.makeText(this, "There is some error!", Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.isShowProgress.observe(this) {
            if (it) {
                activityMainBinding.mainProgressBar.visibility = View.VISIBLE
                //   activityMainBinding.searchGoBtn.visibility = View.GONE
            } else {
                activityMainBinding.mainProgressBar.visibility = View.GONE
                //  activityMainBinding.validationTextForSearch.visibility = View.GONE
            }
        }


        mainViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        activityMainBinding.searchGoBtn.setOnClickListener { it->
            if(activityMainBinding.searchBar.text.isEmpty()){
                Toast.makeText(this, "Please Enter ", Toast.LENGTH_SHORT).show()
            }
            else{
                val searchText = activityMainBinding.searchBar.text

                mainViewModel.getMoviesFromAPI(searchText.toString())

            }

        }

    }

    private fun setUpRecyclerView() = activityMainBinding.movieRecyclerView.apply {
        moviesAdapter = MoviesRecyclerAdapter()
        adapter = moviesAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun setUpViewModel(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

}