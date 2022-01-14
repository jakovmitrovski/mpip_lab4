package com.example.a186010_lab4_mpip.models

import android.os.Parcel
import android.os.Parcelable

data class DisplayStudent(val id: String?, val userId: String?, val index: String?, val name: String?, val surname: String?, val telephoneNumber: String?, val address: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(userId)
        parcel.writeString(index)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(telephoneNumber)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DisplayStudent> {
        override fun createFromParcel(parcel: Parcel): DisplayStudent {
            return DisplayStudent(parcel)
        }

        override fun newArray(size: Int): Array<DisplayStudent?> {
            return arrayOfNulls(size)
        }
    }
}