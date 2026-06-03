package model

import androidx.compose.runtime.mutableStateListOf

object FavoriteManager {
    val daftarFavorit = mutableStateListOf<Barang>()

    fun toggleFavorit(barang: Barang) {
        val barangSudahAda = daftarFavorit.find { it.id == barang.id }

        if (barangSudahAda != null) {
            daftarFavorit.remove(barangSudahAda)
        } else {
            daftarFavorit.add(barang)
        }
    }

    fun isFavorit(id: Int): Boolean {
        return daftarFavorit.any { it.id == id }
    }
}