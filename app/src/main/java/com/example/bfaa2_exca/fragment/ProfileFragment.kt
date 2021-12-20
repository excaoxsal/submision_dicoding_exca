package com.example.bfaa2_exca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bfaa2_exca.R
import com.example.bfaa2_exca.databinding.FragmentProfileBinding
import com.example.bfaa2_exca.extra.State
import com.example.bfaa2_exca.viewmodel.ProfileViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var pageAdapter : PagingAdapter
    private lateinit var profileViewmodel: ProfileViewModel
    private val args : ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewmodel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())
            .get(ProfileViewModel::class.java)
        profileViewmodel.setProfile(args.username)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        profileBinding.lifecycleOwner = viewLifecycleOwner
        observeDetail()
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileBinding.content.transitionName = args.username

        val tabList = arrayOf(
            resources.getString(R.string.followers),
            resources.getString(R.string.following)
        )
        pageAdapter = PagingAdapter(tabList, args.username, this)
        profileBinding.pager.adapter = pageAdapter

        TabLayoutMediator(profileBinding.tabs, profileBinding.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    private fun observeDetail() {
        profileViewmodel.dataProfile.observe(viewLifecycleOwner, Observer {
            if (it.state == State.SUCCESS) {
                profileBinding.data = it.data
            }
        })
    }

    inner class PagingAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = tabList.size
        override fun createFragment(position: Int): Fragment = FollowFragment.newInstance(username, tabList[position])
    }
}