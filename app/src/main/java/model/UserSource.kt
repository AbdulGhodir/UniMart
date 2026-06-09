package model

import com.blockbusteruwu.unimart.R

object UserSource {
    val user = listOf(
        User(
            namaLengkap = "Abdul Ghodir",
            username = "abdul",
            email = "abdul@gmail.com",
            noTelp = "08123456789",
            isPremium = true,
            gambar = R.drawable.ic_zap
        ),
        User(
            namaLengkap = "Annisa Azzahra",
            username = "rara",
            email = "rara@gmail.com",
            noTelp = "08123456789",
            isPremium = false,
            gambar = R.drawable.ic_zap
        ),
        User(
            namaLengkap = "Surya",
            username = "surya",
            email = "surya@gmail.com",
            noTelp = "08123456789",
            isPremium = false,
            gambar = R.drawable.ic_zap
        ),
    )
}