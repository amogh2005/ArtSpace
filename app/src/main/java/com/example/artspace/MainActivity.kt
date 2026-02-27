package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp() {

    val artworks = listOf(
        Artwork(R.drawable.art1, "Agentblazer", "Amogh", "2026"),
        Artwork(R.drawable.art2, "DoneRight", "Business Logo", "2021"),
        Artwork(R.drawable.art3, "Indian Map", "Administrative", "2026")
    )

    var currentIndex by remember { mutableStateOf(0) }

    val currentArt = artworks[currentIndex]

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🖼️ Image Section
            Image(
                painter = painterResource(id = currentArt.imageRes),
                contentDescription = currentArt.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 📝 Text Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentArt.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${currentArt.artist} (${currentArt.year})",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🎮 Button Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
                    }
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}