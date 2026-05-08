package component.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blockbusteruwu.unimart.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.UserSource

@Composable
fun Register(modifier: Modifier = Modifier, navController: NavController){
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 100.dp)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = "Welcome",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Create your account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                ) {
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(10.dp),
                                spotColor = Color.Black.copy(alpha = 0.8f),
                                ambientColor = Color.Black.copy(alpha = 0.8f)

                            ),
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = "Username",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(text = "Username") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )

                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(10.dp),
                                spotColor = Color.Black.copy(alpha = 0.8f),
                                ambientColor = Color.Black.copy(alpha = 0.8f)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = "email",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(text = "Email") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )

                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(10.dp),
                                spotColor = Color.Black.copy(alpha = 0.8f),
                                ambientColor = Color.Black.copy(alpha = 0.8f)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "password",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = "seen",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(text = "Password") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(10.dp),
                                spotColor = Color.Black.copy(alpha = 0.8f),
                                ambientColor = Color.Black.copy(alpha = 0.8f)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "password",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_eye),
                                contentDescription = "seen",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = { Text(text = "Konfirmasi Password") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            delay(2000)
                            isLoading = false

                            if (!username.isEmpty() || !email.isEmpty()) {
                                UserSource.user.username = username
                                UserSource.user.email = email
                            }

                            launch {
                                snackbarHostState.showSnackbar("Registrasi berhasil!")
                            }

                            delay(1000)

                            navController.navigate("login") {
                                popUpTo("register") { inclusive = true }
                            }
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onSecondary,
                            strokeWidth = 2.dp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Memproses...")
                    } else {
                        Text(
                            text = "Daftar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = "- or sign in with -",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable(
                                onClick = { }
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .weight(1f)
                            .padding(13.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Sign in with Google",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 2.dp,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable(
                                onClick = { }
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .weight(1f)
                            .padding(13.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.ic_github),
                            contentDescription = "Sign in with Github",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        )
    }
}