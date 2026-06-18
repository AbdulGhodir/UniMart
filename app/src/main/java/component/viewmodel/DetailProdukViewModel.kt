package component.viewmodel

import androidx.lifecycle.ViewModel
import model.Barang
import model.FavoriteManager

class DetailProdukViewModel : ViewModel() {
    fun toggleFavorite(barang: Barang) {
        FavoriteManager.toggleFavorit(barang = barang)
    }
}