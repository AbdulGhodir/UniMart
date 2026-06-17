package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.blockbusteruwu.unimart.R
import component.ui.RowLayout
import component.ui.SearchInput
import component.viewmodel.BarangViewModel
import component.viewmodel.PencarianViewModel

@Composable
fun Pencarian(modifier: Modifier, navController: NavController, viewModel: PencarianViewModel = viewModel(), kategori : String? = null) {
    var search by remember { mutableStateOf("") }
    var initialKategori by remember { mutableStateOf(kategori) }

    if (search.isNotEmpty() && initialKategori != null) {
        initialKategori = null
    }

    val filteredBarang = if (initialKategori != null) {
        viewModel.semuaBarang.filter { it.kategori.equals(initialKategori, ignoreCase = true) }
    } else {
        viewModel.semuaBarang.filter { it.nama.contains(search, ignoreCase = true) }
    }

    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(PaddingValues(horizontal = 14.dp, vertical = 10.dp)),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SearchInput(search = search, onSearchChange = { search = it })
        }
        Column(modifier = Modifier.weight(1f)
            .padding(horizontal = 14.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val showGrid = (initialKategori != null && filteredBarang.isNotEmpty()) || (search.isNotEmpty() && filteredBarang.isNotEmpty())
            val showNotFound = (initialKategori != null && filteredBarang.isEmpty()) || (search.isNotEmpty() && filteredBarang.isEmpty())

            if (showGrid) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filteredBarang) { i ->
                        RowLayout(barang = i, navController = navController)
                    }
                }
            } else if (showNotFound) {
                val notFoundText = if (initialKategori != null) "Kategori \"$initialKategori\" Tidak ditemukan" else "\"$search\" Tidak ditemukan"
                Row(modifier = Modifier.fillMaxWidth().padding(PaddingValues(vertical = 30.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0x801D3F73), modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = notFoundText, fontSize = 16.sp, color = Color.Gray)
                }
            } else {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Pencarian Terakhir", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Hapus", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                }
                LazyColumn(modifier = Modifier.fillMaxWidth()
                    .border(width = 1.dp, color = Color(0x401D3F73), shape = RoundedCornerShape(15.dp))) {
                    items(listOf("Sepatu","Baju","Topi","Celana")) { i ->
                        Row(modifier = Modifier.fillMaxWidth()
                            .background(Color.White)
                            .padding(PaddingValues(horizontal = 6.dp))) {
                            TextButton(onClick = { search = i }) {
                                Icon(painter = painterResource(R.drawable.ic_history), contentDescription = "Clear", tint = Color(0x801D3F73), modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    Text(text = i, color = Color.Black, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}