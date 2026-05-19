package com.blockbusteruwu.unimart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blockbusteruwu.unimart.ui.theme.PrimaryColor
import com.blockbusteruwu.unimart.ui.theme.UniMartTheme
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import api.RetrofitClient
import component.pages.DaftarObrolan
import component.pages.DaftarPenjual
import java.text.NumberFormat
import java.util.Locale
import component.pages.Dashboard
import component.pages.DetailProduk
import component.pages.History
import component.pages.Pencarian
import component.pages.Profile
import component.pages.EditProfile
import component.pages.KelolaProduk
import component.pages.Favorite
import component.pages.JualBarang
import component.pages.Register
import component.pages.WelcomePage
import component.pages.Login
import component.pages.SplashScreen
import component.pages.PesananMasuk
import component.pages.DaftarProduk
import component.pages.ProdukTerjual
import model.Barang

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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: "splashScreen"
                val noNavBar = listOf("splashScreen", "welcomePage", "login", "register", "daftarObrolan", "detailProduk/{id}", "favorite", "jualProduk")

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = { if (currentRoute !in noNavBar){ Navbar(navController) } },
                    floatingActionButton = {
                        if (currentRoute !in noNavBar){
                            FloatingActionButton(
                                onClick = { navController.navigate("jualProduk")  },
                                shape = CircleShape,
                                modifier = Modifier
                                    .size(60.dp)
                                    .offset(y = 60.dp)
                                    .border(width = 3.dp, color = Color.White, shape = CircleShape),
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White,

                                ) { Icon(Icons.Filled.Add, contentDescription = "Pembayaran") }
                        }
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
        startDestination = "splashScreen"
    ) {
        composable("splashScreen") {
            SplashScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable("welcomePage") {
            WelcomePage(
                modifier = modifier,
                navController = navController
            )
        }

        composable("register") {
            Register(
                modifier = modifier,
                navController = navController
            )
        }

        composable("login") {
            Login(
                modifier = modifier,
                navController = navController
            )
        }


        composable("home") {
            Dashboard(
                modifier = modifier,
                navController = navController
            )
        }

        composable("search") {
            Pencarian(
                modifier = modifier,
                navController = navController
            )
        }

        composable("jualProduk") {
            JualBarang(
                modifier = modifier,
                navController = navController
            )
        }

        composable("history") {
            History(
                modifier = modifier,
                navController = navController
            )
        }

        composable("profile") {
            Profile(
                modifier = modifier,
                navController = navController
            )
        }

        composable("daftarPenjual") {
            DaftarPenjual(
                modifier = modifier,
                navController = navController
            )
        }

        composable("pesananMasuk") {
            PesananMasuk(
                modifier = modifier,
                navController = navController
            )
        }

        composable("daftarProduk") {
            DaftarProduk(
                modifier = modifier,
                navController = navController
            )
        }

        composable("produkTerjual") {
            ProdukTerjual(
                modifier = modifier,
                navController = navController
            )
        }

        composable("editProfile") {
            EditProfile(
                modifier = modifier,
                navController = navController
            )
        }

        composable("kelolaProduk") {
            KelolaProduk(
                modifier = modifier,
                navController = navController
            )
        }

        composable("favorite") {
            Favorite(
                modifier = modifier,
                navController = navController
            )
        }

        composable("daftarObrolan"){
            DaftarObrolan(
                modifier = modifier,
                navController = navController
            )
        }

        composable("detailProduk/{id}") { backStackEntry ->
            val idProduk = backStackEntry.arguments?.getString("id")
            var isLoading by remember { mutableStateOf(true) }
            var barang by remember { mutableStateOf<Barang?>(null) }

            LaunchedEffect(Unit) {
                try {
                    val posts =
                        RetrofitClient.instance.getPosts()

                    barang =
                        posts.find {
                            it.id.toString() == idProduk
                        }
                } catch (e: Exception) {
                    barang = null
                } finally {
                    isLoading = false
                }

            }

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                barang != null -> {
                    DetailProduk(
                        barang = barang!!,
                        modifier = modifier,
                        navController = navController
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Data Produk Tidak Ditemukan! ID: $idProduk",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
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
                    text = "Cari",
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

fun Int.formatRibuan(): String {
    val localeID = Locale("id", "ID")
    val formatter = NumberFormat.getNumberInstance(localeID)

    return formatter.format(this)
}

@Composable
fun PreviewApp() {
    UniMartTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: "welcomePage"
        val noNavBar = listOf("splashScreen", "welcomePage", "login", "register", "daftarObrolan", "detailProduk/{id}", "favorite", "jualProduk")

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = { if (currentRoute !in noNavBar){ Navbar(navController) } },
            floatingActionButton = {
                if (currentRoute !in noNavBar){
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
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            AppNavigation(modifier = Modifier.padding(innerPadding) , navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    UniMartTheme {
        PreviewApp()
    }
}