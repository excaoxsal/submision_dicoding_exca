package com.example.bfaa2_exca.connect

import android.os.Parcelable
import com.example.bfaa2_exca.model.PersonModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchReply(
    val count: String,
    val partial_results: Boolean? = null,
    val items: List<PersonModel>
) : Parcelable
