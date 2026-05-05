package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import api.RetrofitClient
import androidx.navigation.compose.rememberNavController
import com.blockbusteruwu.unimart.R
import component.ui.ColumnLayout
import model.Barang

@Composable
fun History(modifier: Modifier = Modifier, navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    var posts by remember { mutableStateOf(emptyList<Barang>()) }

    LaunchedEffect(Unit) {
        try {
            posts = RetrofitClient.instance.getPosts()
            isLoading = false
        } catch (e: Exception) {
            isLoading = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 20.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
                        .size(45.dp),
                ) {
                    Text(text = "U", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.align(Alignment.Center))
                }

                Text(text = "UniMart", fontSize = 22.sp, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "favorite",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable{ navController.navigate("favorite") }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "chat",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable{ navController.navigate("daftarObrolan") }
                )
            }
        }

        if(isLoading) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                CircularProgressIndicator()
                Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 25.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Text(text = "Riwayat Pembelian", fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                items(posts) { barang ->
                    ColumnLayout(barang = barang, navController)
                }
        }
    }
}