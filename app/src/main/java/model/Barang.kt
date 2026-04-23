package model
import androidx.annotation.DrawableRes


data class Barang(
    val id: Int,
    val nama: String,
    val harga: Int,
    @DrawableRes val gambar: Int,
)
