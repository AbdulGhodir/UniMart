package model

data class PengajuanPremium(
    val user: User = User(),
    val status: String = "NOT_SUBMITTED",
    val npm: String = "",
    val fakultas: String = "",
    val prodi: String = "",
    val alamat: String = "",
    val noTelp: String = ""
)
