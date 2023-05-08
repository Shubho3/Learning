package com.learning.ui.activity.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.learning.R
import com.learning.databinding.FragmentHomeBinding
import com.learning.utills.errorSnack
import com.learning.utills.successSnack
import com.learning.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewmodel.responseContainer.observe(viewLifecycleOwner) {
            if (it.status == "1") {
                binding.btnSubmit.hideLoading()
                binding.root.successSnack(it.message.toString())
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_homeFragment_to_mainActivity)

            } else {
                binding.btnSubmit.hideLoading()
                binding.root.errorSnack(viewmodel.errorMessage.value.toString())
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtEmail.text.toString())) binding.root.errorSnack(
                getString(R.string.empty_email)
            )
            else if (TextUtils.isEmpty(binding.edtPass.text.toString())) binding.root.errorSnack(
                getString(R.string.empty_pass)
            )
            else {
                val map = HashMap<String, String>()
                map["email"] = binding.edtEmail.text.toString()
                map["password"] = binding.edtPass.text.toString()
                // map["register_id"] = "123"
                map["register_id"] = "123"
                map["type"] = "Administrator"
                viewmodel.login(map)
                binding.btnSubmit.showLoading()
            }
        }
        return binding.root
    }


}