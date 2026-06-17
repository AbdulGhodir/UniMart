package component.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import model.User
import repository.FirestoreRepository

class IsiChatViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    var penjual by mutableStateOf<User?>(null)
    var isBuying by mutableStateOf(false)

    fun beliSekarang(barang: Barang, buyerEmail: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            isBuying = true
            val updatedBarang = barang.copy(
                isTerjual = true,
                buyerId = buyerEmail,
                boughtAt = System.currentTimeMillis()
            )
            val success = repository.updateProduct(updatedBarang)

            onComplete(success)
        }
    }

    fun getDataSeller(sellerId: String) {
        viewModelScope.launch {
            penjual = repository.getUser(sellerId)
        }
    }
}