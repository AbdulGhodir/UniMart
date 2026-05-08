package model

import com.google.gson.annotations.SerializedName


data class Barang(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val nama: String,

    @SerializedName("price")
    val harga: Int,

    @SerializedName("description")
    val deskripsi: String,

    @SerializedName("category")
    val kategori: String,

    @SerializedName("image")
    val gambar: String,
)
