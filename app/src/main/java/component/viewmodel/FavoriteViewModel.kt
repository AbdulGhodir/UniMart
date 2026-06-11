package component.viewmodel

import androidx.lifecycle.ViewModel
import model.Barang
import model.FavoriteManager

class FavoriteViewModel : ViewModel() {
    val listFavorit: List<Barang>
        get() = FavoriteManager.daftarFavorit
}