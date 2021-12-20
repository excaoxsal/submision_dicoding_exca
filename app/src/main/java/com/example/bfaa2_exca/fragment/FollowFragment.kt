package com.example.bfaa2_exca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bfaa2_exca.R
import com.example.bfaa2_exca.adapter.PersonAdapter
import com.example.bfaa2_exca.databinding.FragmentFollowBinding
import com.example.bfaa2_exca.extra.ShowState
import com.example.bfaa2_exca.extra.State
import com.example.bfaa2_exca.viewmodel.FollowViewModel
import com.example.bfaa2_exca.viewmodel.TypeView


class FollowFragment : Fragment() {
    companion object{
        fun newInstance (username:String,type:String)=
            FollowFragment().apply {
                arguments=Bundle().apply{
                    putString(USERNAME, username)
                    putString(Type, type)
                }
            }
        const val stateFolId = 2
        private const val Type = "type"
        private const val USERNAME = "username"
    }
    private lateinit var followBinding: FragmentFollowBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var followViewModel: FollowViewModel
    private lateinit var username:String
    private var type: String?=null
    private var showState = ShowState(stateFolId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(Type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followBinding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return followBinding.root
        }


    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personAdapter = PersonAdapter(arrayListOf()) { user, _ ->
            Toast.makeText(context, user, Toast.LENGTH_SHORT).show()
        }

        followBinding.recylerFollow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = personAdapter
        }

        followViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowViewModel::class.java)

        when (type) {
            resources.getString(R.string.following) -> followViewModel.setFollow(username, TypeView.FOLLOWING)
            resources.getString(R.string.followers) -> followViewModel.setFollow(username, TypeView.FOLLOWER)
            else -> showState.error(null, followBinding, null, resources)
        }
        observeFollow()
    }
    private fun observeFollow() {
        followViewModel.dataFollow.observe(viewLifecycleOwner, Observer{
            when (it.state) {
                State.SUCCESS ->
                    if (!it.data.isNullOrEmpty()) {
                        showState.success(null, followBinding)
                        personAdapter.run { setData(it.data) }
                    } else {
                        showState.error(
                            null,
                            followBinding,
                            resources.getString(R.string.not_have, username, type),
                            resources
                        )
                    }
                State.LOADING -> showState.loading(null, followBinding)
                State.ERROR -> showState.error(null, followBinding, it.message, resources)
            }
        })
    }


}

