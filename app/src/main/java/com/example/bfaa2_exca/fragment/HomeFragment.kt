package com.example.bfaa2_exca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bfaa2_exca.R
import com.example.bfaa2_exca.adapter.PersonAdapter
import com.example.bfaa2_exca.databinding.FragmentHomeBinding
import com.example.bfaa2_exca.extra.ShowState
import com.example.bfaa2_exca.extra.State
import com.example.bfaa2_exca.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    companion object{
        const val stateHomeId = 1
    }
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeAdapter : PersonAdapter
    private lateinit var homeViewModel : HomeViewModel
    private var showState = ShowState(stateHomeId)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        homeViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        homeBinding.errLayout.emptyText.text = "Input Username..."
        homeAdapter= PersonAdapter(arrayListOf()){
            username, iv -> findNavController().navigate(HomeFragmentDirections.profileAction(username),
                FragmentNavigatorExtras(iv to username)
            )
        }
        homeBinding.recyclerHome.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
        homeBinding.searchView.apply{
            queryHint= resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    homeViewModel.setSearch(query)
                    homeBinding.searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
        observeHome()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    private fun observeHome() {
        homeViewModel.searchPerson.observe(viewLifecycleOwner, Observer{
            it?.let { resource ->
                when (resource.state) {
                    State.SUCCESS -> {
                        resource.data?.let { users ->
                            if (!users.isNullOrEmpty()) {
                                showState.success(homeBinding, null)
                                homeAdapter.setData(users)
                            } else {
                                showState.error(homeBinding, null, null, resources)
                            }
                        }
                    }
                    State.LOADING -> showState.loading(homeBinding, null)
                    State.ERROR -> showState.error(
                        homeBinding, null, it.message, resources
                    )
                }
            }
        })
    }
}