package com.example.bfaa2_exca.model

import android.os.Parcel
import android.os.Parcelable

data class PersonModel (
    val id: Int,
    val status: String,
    val name: String?,
    val location: String?,
    val type: String?,
    val repository: Int,
    val followers: Int,
    val following: Int,
    val img_link: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(status)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(type)
        parcel.writeInt(repository)
        parcel.writeInt(followers)
        parcel.writeInt(following)
        parcel.writeString(img_link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonModel> {
        override fun createFromParcel(parcel: Parcel): PersonModel {
            return PersonModel(parcel)
        }

        override fun newArray(size: Int): Array<PersonModel?> {
            return arrayOfNulls(size)
        }
    }
}