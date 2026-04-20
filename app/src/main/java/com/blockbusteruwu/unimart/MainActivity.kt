package com.blockbusteruwu.unimart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blockbusteruwu.unimart.ui.theme.PrimaryColor
import com.blockbusteruwu.unimart.ui.theme.SurfaceColor
import com.blockbusteruwu.unimart.ui.theme.UniMartTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import model.Barang
import model.BarangSource
import java.text.NumberFormat
import java.util.Locale

//import component.pages.Dashboard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = PrimaryColor.hashCode()
            ),

            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.White.hashCode(),
                darkScrim = Color.White.hashCode()
            )
        )
        setContent {
            UniMartTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = { Navbar(navController) },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { },
                            shape = CircleShape,
                            modifier = Modifier
                                .size(60.dp)
                                .offset(y = 60.dp)
                                .border(width = 3.dp, color = Color.White, shape = CircleShape),
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,

                            ) { Icon(Icons.Filled.Add, contentDescription = "Pembayaran") }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding) , navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Dashboard(
                modifier = modifier,
                navController = navController
            )
        }

        composable("search") {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Halaman Pencarian Belum Dibuat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        composable("history") {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Halaman Riwayat Belum Dibuat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        composable("profile") {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Halaman Profil Belum Dibuat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun Navbar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 30.dp,
                spotColor = Color.Black,
            )
            .background(Color.White),

        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "home",
                    modifier = Modifier
                        .size(24.dp)
                        .offset(y = 4.dp)
                )
            },
            label = {
                Text(
                    text = "UniMart",
                    fontWeight = if (currentRoute == "home") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .offset(y = (-5).dp)
                )
            },

            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                unselectedTextColor = MaterialTheme.colorScheme.onSecondary
            ),
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    modifier = Modifier
                        .size(24.dp)
                        .offset(y = 4.dp)
                )
            },

            label = {
                Text(
                    text = "Pencarian",
                    fontWeight = if (currentRoute == "search") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.offset(y = (-5).dp)
                )
            },
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                unselectedTextColor = MaterialTheme.colorScheme.onSecondary
            ),
        )

        Spacer(modifier = Modifier.weight(1f))

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "history",
                    modifier = Modifier
                        .size(24.dp)
                        .offset(y = 4.dp)
                )
            },
            label = {
                Text(
                    text = "Riwayat",
                    fontWeight = if (currentRoute == "history") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.offset(y = (-5).dp)
                )
            },
            selected = currentRoute == "history",
            onClick = {
                navController.navigate("history") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                unselectedTextColor = MaterialTheme.colorScheme.onSecondary
            ),
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "profile",
                    modifier = Modifier
                        .size(24.dp)
                        .offset(y = 4.dp)
                )
            },
            label = {
                Text(
                    text = "Profil",
                    fontWeight = if (currentRoute == "profile") FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.offset(y = (-5).dp)
                )
            },
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = Color.Transparent,

                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                unselectedTextColor = MaterialTheme.colorScheme.onSecondary
            ),
        )
    }
}

@Composable
fun Dashboard(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                    modifier = Modifier.size(26.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "chat",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Makanan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cup),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Minuman", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shirt),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Pakaian", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Aksesoris", fontSize = 12.sp)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Buku", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Perlengkapan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Perawatan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Elektronik", fontSize = 12.sp)
                        }
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Produk Terbaru", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Lihat Semua", fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End, color = MaterialTheme.colorScheme.primary)
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(BarangSource.daftarBarang) { barang ->
                        DetailScreen(barang = barang)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Murah Meriah < 50k", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Lihat Semua", fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End, color = MaterialTheme.colorScheme.primary)
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(BarangSource.daftarBarang) { barang ->
                        DetailScreen(barang = barang)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Terakhir Dilihat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Lihat Semua", fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End, color = MaterialTheme.colorScheme.primary)
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(BarangSource.daftarBarang) { barang ->
                        DetailScreen(barang = barang)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun DetailScreen(barang: Barang) {
    Card(
        modifier = Modifier.width(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(id = barang.gambar),
                contentDescription = barang.nama,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
                    .height(150.dp)
                    .shadow(
                        elevation = 50.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    ),
                contentScale = ContentScale.Crop,
            )
            Text(text = "${barang.nama}", fontSize = 12.sp, fontWeight = FontWeight.Medium, minLines = 2, maxLines = 2, lineHeight = 16.sp)
            Text(text = "Rp ${barang.harga.formatRibuan()}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}


fun Int.formatRibuan(): String {
    val localeID = Locale("id", "ID")
    val formatter = NumberFormat.getNumberInstance(localeID)

    return formatter.format(this)
}

@Composable
fun PreviewApp() {
    UniMartTheme {
        val previewNavbarController = rememberNavController()

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = { Navbar(previewNavbarController) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    shape = CircleShape,
                    modifier = Modifier
                        .size(60.dp)
                        .offset(y = 55.dp)
                        .border(width = 3.dp, color = Color.White, shape = CircleShape),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,

                    ) { Icon(Icons.Filled.Add, contentDescription = "Pembayaran") }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            AppNavigation(modifier = Modifier.padding(innerPadding) , navController = previewNavbarController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    UniMartTheme {
        UniMartTheme {
            PreviewApp()
        }
    }
}