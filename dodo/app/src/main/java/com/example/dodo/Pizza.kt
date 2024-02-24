package com.example.dodo

import android.os.Parcel
import android.os.Parcelable

data class Pizza (
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageResource: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeInt(imageResource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pizza> {
        override fun createFromParcel(parcel: Parcel): Pizza {
            return Pizza(parcel)
        }

        override fun newArray(size: Int): Array<Pizza?> {
            return arrayOfNulls(size)
        }
    }
}
