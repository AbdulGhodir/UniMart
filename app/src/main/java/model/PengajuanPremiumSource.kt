package model

object PengajuanPremiumSource {
    val daftarPengajuan = listOf(
        PengajuanPremium(user = UserSource.user[0] , status = "APPROVED"),
        PengajuanPremium(user = UserSource.user[1], status = "PENDING"),
        PengajuanPremium(user = UserSource.user[2], status = "REJECTED")
    )

    fun getStatus(user: User): String {
        return daftarPengajuan.find { it.user == user }?.status ?: "NOT_SUBMITTED"
    }
}