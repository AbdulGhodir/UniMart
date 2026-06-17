package repository

import api.RetrofitClient
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import model.Barang
import model.User
import model.PengajuanPremium
import java.util.Locale

class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productsCol = db.collection("products")
    private val usersCol = db.collection("users")
    private val applicationsCol = db.collection("applications")

    suspend fun getProducts(): List<Barang> {
        try {
            val snapshot = productsCol.get().await()
            if (snapshot.isEmpty) {
                seedInitialProducts()
                val newSnapshot = productsCol.get().await()
                return newSnapshot.toObjects(Barang::class.java).filter { !it.isTerjual }
            }
            return snapshot.toObjects(Barang::class.java).filter { !it.isTerjual }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    suspend fun addProduct(barang: Barang): Boolean {
        return try {
            val id = if (barang.id == 0) (System.currentTimeMillis() % 100000000).toInt() else barang.id
            val newBarang = barang.copy(id = id)
            productsCol.document(id.toString()).set(newBarang).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun updateProduct(barang: Barang): Boolean {
        return try {
            productsCol.document(barang.id.toString()).set(barang).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun deleteProduct(id: Int): Boolean {
        return try {
            productsCol.document(id.toString()).delete().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun getProductsByBuyer(buyerEmail: String): List<Barang> {
        return try {
            val snapshot = productsCol
                .whereEqualTo("buyerId", buyerEmail)
                .whereEqualTo("isTerjual", true)
                .get()
                .await()
            snapshot.toObjects(Barang::class.java)
                .sortedByDescending { it.boughtAt }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getProductsBySeller(sellerEmail: String): List<Barang> {
        return try {
            val snapshot = productsCol
                .whereEqualTo("sellerId", sellerEmail)
                .get()
                .await()
            snapshot.toObjects(Barang::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getUser(email: String): User? {
        return try {
            val doc = usersCol.document(email).get().await()
            if (doc.exists()) {
                doc.toObject(User::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun saveUser(user: User): Boolean {
        return try {
            usersCol.document(user.email).set(user).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun listenToPengajuan(email: String, onUpdate: (PengajuanPremium?) -> Unit) {
        applicationsCol.document(email).addSnapshotListener { snapshot, e ->
            if (e != null) {
                e.printStackTrace()
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val pengajuan = snapshot.toObject(PengajuanPremium::class.java)
                onUpdate(pengajuan)
            } else {
                onUpdate(null)
            }
        }
    }

    suspend fun savePengajuan(pengajuan: PengajuanPremium): Boolean {
        return try {
            applicationsCol.document(pengajuan.user.email).set(pengajuan).await()
            if (pengajuan.status == "APPROVED") {
                val user = pengajuan.user.copy(isPremium = true)
                saveUser(user)
            } else {
                val user = pengajuan.user.copy(isPremium = false)
                saveUser(user)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private suspend fun seedInitialProducts() {
        val response = RetrofitClient.instance.getPosts()
        for (barang in response) {
            productsCol.document(barang.id.toString()).set(barang).await()
        }
//        val initialProducts = listOf(
//            Barang(1, "Tas Ransel Laptop", 1800000, "Tas sempurna untuk penggunaan sehari-hari dan jalan-jalan di hutan. Simpan laptop hingga 15 inci dengan aman.", "Pakaian", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"),
//            Barang(2, "Kaos Slim Fit", 365000, "Kaos slim fit ringan dan nyaman dipakai untuk aktivitas sehari-hari.", "Pakaian", "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_t.png"),
//            Barang(3, "Jaket Katun", 910000, "Jaket hangat cocok untuk aktivitas outdoor dan cuaca dingin.", "Pakaian", "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_t.png"),
//            Barang(4, "Celana Slim Fit", 260000, "Celana casual modern dengan model slim fit yang nyaman digunakan.", "Pakaian", "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png"),
//            Barang(5, "Gelang Naga", 11400000, "Gelang elegan dengan desain naga yang melambangkan perlindungan.", "Aksesoris", "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_t.png"),
//            Barang(6, "Cincin Emas", 2750000, "Perhiasan elegan dengan desain mewah dan kualitas premium.", "Aksesoris", "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_t.png"),
//            Barang(7, "Cincin Putih", 165000, "Cincin elegan cocok untuk hadiah spesial dan pertunangan.", "Aksesoris", "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_t.png"),
//            Barang(8, "Anting Rose Gold", 180000, "Anting stainless steel dengan lapisan rose gold yang elegan.", "Aksesoris", "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_t.png"),
//            Barang(9, "Hard Drive WD 2TB", 1050000, "Hard drive portable dengan transfer data cepat dan kapasitas besar.", "Elektronik", "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_t.png"),
//            Barang(10, "SSD SanDisk 1TB", 1780000, "SSD performa tinggi untuk mempercepat sistem komputer.", "Elektronik", "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_t.png"),
//            Barang(11, "SSD Silicon Power", 1780000, "SSD cepat dengan teknologi 3D NAND dan performa stabil.", "Elektronik", "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_t.png"),
//            Barang(12, "Gaming Drive PS4", 1860000, "Hard drive gaming portable khusus PlayStation dengan kapasitas besar.", "Elektronik", "https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_t.png"),
//            Barang(13, "Monitor Acer", 9800000, "Monitor Full HD ultra tipis dengan desain modern dan jernih.", "Elektronik", "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_t.png"),
//            Barang(14, "Monitor Gaming Samsung", 16400000, "Monitor gaming curved super ultrawide dengan refresh rate tinggi.", "Elektronik", "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_t.png"),
//            Barang(15, "Jaket Snowboard", 930000, "Jaket musim dingin multifungsi dengan desain tahan angin.", "Pakaian", "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_t.png"),
//            Barang(16, "Jaket Kulit Wanita", 490000, "Jaket kulit sintetis wanita dengan hoodie modern.", "Pakaian", "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_t.png"),
//            Barang(17, "Jaket Hujan Wanita", 655000, "Jaket hujan ringan dengan desain stylish dan nyaman.", "Pakaian", "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2t.png"),
//            Barang(18, "Kaos Boat Neck", 160000, "Kaos wanita berbahan lembut dan nyaman untuk sehari-hari.", "Pakaian", "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_t.png"),
//            Barang(19, "Kaos Olahraga Wanita", 130000, "Kaos olahraga ringan dengan bahan penyerap keringat.", "Pakaian", "https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_t.png"),
//            Barang(20, "Kaos Casual Wanita", 210000, "Kaos casual berbahan katun lembut dengan desain modern.", "Pakaian", "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_t.png")
//        )
//        for (barang in initialProducts) {
//            productsCol.document(barang.id.toString()).set(barang).await()
//        }
    }
}
