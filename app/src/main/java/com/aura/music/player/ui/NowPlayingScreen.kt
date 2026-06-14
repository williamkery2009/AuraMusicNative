package com.aura.music.player.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
importコイル.request.ImageRequest
import coil.request.ImageRequest

/**
 * AURA MUSIC: High-Fidelity Android Jetpack Compose Now Playing Screen
 * 
 * 1. Apple Media Blur Background: Cures Android standard solid layout templates by scaling up the exact user album artwork (`activeSong.artworkCover`) with deep UI blurs.
 * 2. Curation Controls Architecture: Displays two two flawless execution control horizontal rows.
 */

@Composable
fun NowPlayingScreen(
    songTitle: String = "Belong Together",
    artistName: String = "Mark Ambor",
    albumName: String = "Belong Together - Single",
    artworkUrl: String = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?auto=format&fit=crop&w=800&q=80",
    isPlaying: Boolean = true,
    isHiRes: Boolean = true,
    onTogglePlay: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onClose: () -> Unit,
    onLaunchStudioEqualizer: () -> Unit
) {
    var isFavorited by remember { mutableStateOf(true) }
    var activeMode by remember { mutableStateOf("HERO") } // "HERO" | "LYRICS"
    var currentSliderPos by remember { mutableFloatStateOf(0.35f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // 1. Moving Breathtaking Exact Artwork Blurry Backdrop
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artworkUrl)
                .crossfade(true)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.4f)
                .blur(90.dp)
        )

        // Dark diffuse material mesh
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x7A000000))
        )

        // 2. Master Structural Stacking Structure
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.ExpandMore,
                        contentDescription = "Collapse Sanctuary",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "PLAYING FROM MASTER VAULT",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF64D2FF)
                    )
                    Text(
                        text = albumName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(onClick = onLaunchStudioEqualizer) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Studio Equalizer Workbench",
                        tint = Color(0xFF0A84FF),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Central Exhibits Viewport
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (activeMode == "HERO") {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = songTitle,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(32.dp))
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = songTitle,
                            style = MaterialTheme.typography.displayLarge.copy(fontSize = 36.sp),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        
                        Text(
                            text = artistName,
                            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp),
                            color = Color(0xFFD1D1D6),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                } else {
                    // eLRC Character Simple Karaoke Mode
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(vertical = 32.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "I know sleep is friends with death",
                            style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp, fontWeight = FontWeight.Black),
                            color = Color(0x66FFFFFF),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                        
                        // Active Phrase
                        Text(
                            text = "But maybe I should get some rest",
                            style = MaterialTheme.typography.displayMedium.copy(fontSize = 36.sp, fontWeight = FontWeight.Black),
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        
                        Text(
                            text = "'Cause I've been out here working all damn day",
                            style = MaterialTheme.typography.displayMedium.copy(fontSize = 32.sp, fontWeight = FontWeight.Black),
                            color = Color(0x66FFFFFF),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }

            // Mode Selector Console Pill
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0x33FFFFFF), RoundedCornerShape(24.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (activeMode == "HERO") Color.White else Color.Transparent)
                        .clickable { activeMode = "HERO" }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ARTWORK",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black),
                        color = if (activeMode == "HERO") Color.Black else Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (activeMode == "LYRICS") Color(0xFF64D2FF) else Color.Transparent)
                        .clickable { activeMode = "LYRICS" }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "KARAOKE SYNC",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black),
                        color = if (activeMode == "LYRICS") Color.Black else Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Absolute Anti-Clipping 2-Tier Transport Console
            Column(modifier = Modifier.fillMaxWidth()) {
                
                // Tier 1: Sub Actions & technical Hi-Res / Codec normal Info
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { isFavorited = !isFavorited }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Like Recording",
                            tint = if (isFavorited) Color(0xFFFA233B) else Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // Lossless Plaque
                    Box(
                        modifier = Modifier
                            .background(Color(0x33FFFFFF), RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0x660A84FF), RoundedCornerShape(16.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isHiRes) "FLAC • 24B / 192K • LOSSLESS" else "FLAC • 16B / 44.1K • LOSSLESS",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }

                    IconButton(onClick = { /* Launch Credits Plaque */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "Complete Exhibits Museum Quick Credits",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                // Precision Live Interactive Slider
                Spacer(modifier = Modifier.height(12.dp))
                Slider(
                    value = currentSliderPos,
                    onValueChange = { currentSliderPos = it },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.White,
                        inactiveTrackColor = Color(0x40FFFFFF)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Tier 2: The Core Transport Execution Console
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /* Toggle Shuffle */ }) {
                        Icon(imageVector = Icons.Default.Shuffle, contentDescription = "Shuffle", tint = Color.White, modifier = Modifier.size(28.dp))
                    }

                    IconButton(onClick = onPrev) {
                        Icon(imageVector = Icons.Default.SkipPrevious, contentDescription = "Prev", tint = Color.White, modifier = Modifier.size(40.dp))
                    }

                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable(onClick = onTogglePlay),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.Black,
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    IconButton(onClick = onNext) {
                        Icon(imageVector = Icons.Default.SkipNext, contentDescription = "Next", tint = Color.White, modifier = Modifier.size(40.dp))
                    }

                    IconButton(onClick = { /* Toggle Repeat */ }) {
                        Icon(imageVector = Icons.Default.Repeat, contentDescription = "Repeat", tint = Color.White, modifier = Modifier.size(28.dp))
                    }
                }

            }

        }
    }
}
