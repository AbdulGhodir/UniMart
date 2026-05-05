package component.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.blockbusteruwu.unimart.R
import com.blockbusteruwu.unimart.formatRibuan
import model.Barang

@Composable
fun RowLayout(barang: Barang, navController: NavController) {
    Card(
        modifier = Modifier.width(180.dp)
            .clickable { navController.navigate("detailProduk/${barang.id}") },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AsyncImage(
                model = barang.gambar,
                contentDescription = barang.nama,
                placeholder = painterResource(id = R.drawable.img_barang1),
                error = painterResource(id = R.drawable.img_barang2),
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .fillMaxSize()
                    .height(150.dp)
                    .shadow(
                        elevation = 50.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    ),

                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(end = 10.dp, start = 10.dp, bottom = 6.dp)) {
                Text(text = barang.nama, fontSize = 12.sp, fontWeight = FontWeight.Medium, minLines = 2, maxLines = 2, lineHeight = 16.sp)
                Text(text = "Rp ${barang.harga.formatRibuan()}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}