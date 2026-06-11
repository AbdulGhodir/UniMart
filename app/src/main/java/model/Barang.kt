package model

import com.google.gson.annotations.SerializedName
import com.google.firebase.firestore.PropertyName

data class Barang(
    @SerializedName("id")
    @get:PropertyName("id")
    @PropertyName("id")
    val id: Int = 0,

    @SerializedName("title")
    @get:PropertyName("title")
    @PropertyName("title")
    val nama: String = "",

    @SerializedName("price")
    @get:PropertyName("price")
    @PropertyName("price")
    val harga: Int = 0,

    @SerializedName("description")
    @get:PropertyName("description")
    @PropertyName("description")
    val deskripsi: String = "",

    @SerializedName("category")
    @get:PropertyName("category")
    @PropertyName("category")
    val kategori: String = "",

    @SerializedName("image")
    @get:PropertyName("image")
    @PropertyName("image")
    val gambar: String = "",

    @SerializedName("status")
    @get:PropertyName("status")
    @PropertyName("status")
    val status: String = "",

    @SerializedName("sellerId")
    @get:PropertyName("sellerId")
    @PropertyName("sellerId")
    val sellerId: String = "",
)
