package model

object PengajuanPremiumSource {
    val daftarPengajuan = listOf(
        PengajuanPremium(email = "abdul@gmail.com", status = "PENDING"),
        PengajuanPremium(email = "rara@gmail.com", status = "PENDING"),
        PengajuanPremium(email = "surya@gmail.com", status = "REJECTED")
    )

    fun getStatus(email: String): String {
        return daftarPengajuan.find { it.email == email }?.status ?: "NOT_SUBMITTED"
    }
}