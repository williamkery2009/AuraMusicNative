package com.aura.music.player.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

/**
 * AURA MUSIC: High-Performance Offline Relational Room structural Database Module
 */

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val songId: String,
    val folderId: String,
    val filePath: String,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val releaseYear: Int,
    val trackNumber: Int,
    val durationSeconds: Float,
    val format: String,
    val sampleRate: Int,
    val bitDepth: Int,
    val fileSizeBytes: Long,
    val playCount: Int,
    val rating: Int,
    val isFavorite: Boolean,
    val artworkCoverPath: String?,
    val dateAdded: Long
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val playlistId: String,
    val title: String,
    val description: String,
    val type: String, // 'MANUAL' | 'SMART' | 'FOLDER'
    val songIdsJSON: String,
    val isPinned: Boolean,
    val dateCreated: Long
)

@Dao
interface AuraMusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSong(song: SongEntity)

    @Query("SELECT * FROM songs ORDER BY title ASC")
    fun getAllSongsAlpha(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs ORDER BY playCount DESC LIMIT 10")
    fun getMostPlayedSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE isFavorite = 1 ORDER BY title ASC")
    fun getFavoriteSongs(): Flow<List<SongEntity>>

    @Query("UPDATE songs SET playCount = playCount + 1 WHERE songId = :songId")
    suspend fun incrementPlayCount(songId: String)

    @Query("DELETE FROM songs")
    suspend fun purgeEntireMuseum()
}

@Database(entities = [SongEntity::class, PlaylistEntity::class], version = 1, exportSchema = false)
abstract class AuraMusicDatabase : RoomDatabase() {
    abstract fun musicDao(): AuraMusicDao
}
