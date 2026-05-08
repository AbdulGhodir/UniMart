package api
import model.Barang
import retrofit2.http.GET

interface ApiService {
    @GET("barang_unimart")
    suspend fun getPosts(): List<Barang>
}