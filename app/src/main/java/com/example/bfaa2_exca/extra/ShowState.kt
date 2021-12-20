package com.example.bfaa2_exca.extra

import android.content.res.Resources
import android.view.View
import com.example.bfaa2_exca.R
import com.example.bfaa2_exca.databinding.FragmentFollowBinding
import com.example.bfaa2_exca.databinding.FragmentHomeBinding

class ShowState (private val stateId :Int){
    fun loading(homeBinding: FragmentHomeBinding?, followBinding: FragmentFollowBinding?){
        when(stateId){
            1 -> {
                homeBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                homeBinding?.progress?.visibility = View.VISIBLE
                homeBinding?.recyclerHome?.visibility = View.GONE
            }
            2 -> {
                followBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                followBinding?.progress?.visibility = View.VISIBLE
                followBinding?.recylerFollow?.visibility = View.GONE
            }
        }
    }

    fun success(homeBinding: FragmentHomeBinding?, followBinding: FragmentFollowBinding?){
        when(stateId){
            1 ->{
                homeBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                homeBinding?.progress?.visibility = View.GONE
                homeBinding?.recyclerHome?.visibility = View.VISIBLE
            }

            2 ->{
                followBinding?.errLayout?.mainNotFound?.visibility = View.GONE
                followBinding?.progress?.visibility = View.GONE
                followBinding?.recylerFollow?.visibility = View.VISIBLE
            }
        }
    }

    fun error(homeBinding: FragmentHomeBinding? ,followBinding: FragmentFollowBinding? ,message: String?, resources: Resources){
        when(stateId){
            1 -> {
                homeBinding?.errLayout?.mainNotFound?.visibility = View.VISIBLE
                homeBinding?.errLayout?.emptyText?.text = message ?: resources.getString(R.string.not_found)
                homeBinding?.progress?.visibility = View.GONE
                homeBinding?.recyclerHome?.visibility = View.GONE
            }
            2 -> {
                followBinding?.errLayout?.mainNotFound?.visibility = View.VISIBLE
                followBinding?.errLayout?.emptyText?.text = message ?: resources.getString(R.string.not_found)
                followBinding?.progress?.visibility = View.GONE
                followBinding?.recylerFollow?.visibility = View.GONE
            }
        }
    }
}