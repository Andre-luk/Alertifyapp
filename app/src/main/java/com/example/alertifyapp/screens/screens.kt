@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.alertifyapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Spacer
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("welcome") { popUpTo("splash") { inclusive = true } }
    }

    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp))
                        Spacer(Modifier.height(20.dp))
                        Text("AlertifyApp", style = MaterialTheme.typography.displaySmall)
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold { padding ->
        Box(Modifier.fillMaxSize().padding(padding), Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp))
                Spacer(Modifier.height(25.dp))
                Text("Bienvenue", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(40.dp))
                NavigationButtons(navController)
            }
        }
    }
}

@Composable
private fun NavigationButtons(navController: NavController) {
    Column {
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.width(200.dp)
        ) {
            Icon(Icons.Default.Login, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Connexion")
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.width(200.dp)
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Inscription")
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScreenTemplate(
        title = "Connexion",
        navController = navController,
        buttonText = "Se connecter"
    ) {
        AuthInputFields(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it }
        )
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenTemplate(
        title = "Inscription",
        navController = navController,
        buttonText = "S'inscrire"
    ) {
        AuthInputFields(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onConfirmPasswordChange = { confirmPassword = it }
        )
    }
}

@Composable
private fun AuthScreenTemplate(
    title: String,
    navController: NavController,
    buttonText: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(padding),
            verticalArrangement = Arrangement.Center
        ) {
            content()
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { navController.navigate("user_home") },
                Modifier.fillMaxWidth()
            ) {
                Text(buttonText)
            }
        }
    }
}

@Composable
private fun AuthInputFields(
    email: String,
    password: String,
    confirmPassword: String = "",
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit = {}
) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        PasswordField("Mot de passe", password, onPasswordChange)

        if (confirmPassword.isNotEmpty()) {
            Spacer(Modifier.height(20.dp))
            PasswordField("Confirmation", confirmPassword, onConfirmPasswordChange)
        }
    }
}

@Composable
private fun PasswordField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UserHomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accueil") },
                actions = {
                    IconButton({ navController.navigate("profile") }) {
                        Image(
                            painter = painterResource(R.drawable.ic_logo),
                            contentDescription = "Profil",
                            modifier = Modifier.size(40.dp))
                    }
                }
            )
        }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding), Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = "Utilisateur",
                    modifier = Modifier.size(120.dp))
                Spacer(Modifier.height(20.dp))
                Text("Bienvenue !", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil") },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Profil",
                modifier = Modifier.size(100.dp))
            Spacer(Modifier.height(30.dp))
            ProfileInfoField("Nom", "Jean Dupont")
            ProfileInfoField("Email", "jean.dupont@alertify.com")
        }
    }
}

@Composable
private fun ProfileInfoField(label: String, value: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}