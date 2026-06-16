package model

import androidx.annotation.DrawableRes
import com.blockbusteruwu.unimart.R

data class User(
    var namaLengkap: String = "",
    var username: String = "",
    var email: String = "",
    var noTelp: String = "",
    var isPremium: Boolean = false,
    var gambar: Any = R.drawable.ic_zap,
)