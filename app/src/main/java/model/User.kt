package model

import androidx.annotation.DrawableRes

data class User(
    var namaLengkap: String,
    var username: String,
    var email: String,
    var noTelp: String,
    var isPremium: Boolean,
    @DrawableRes var gambar: Int,
)