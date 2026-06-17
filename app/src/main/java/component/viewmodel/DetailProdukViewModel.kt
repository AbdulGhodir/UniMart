package component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import model.FavoriteManager
import repository.FirestoreRepository

class DetailProdukViewModel : ViewModel() {
    fun toggleFavorite(barang: Barang) {
        FavoriteManager.toggleFavorit(barang = barang)
    }
}