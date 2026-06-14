package com.aura.music.player.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.aura.music.player.MainActivity
import com.aura.music.player.R
import com.aura.music.player.service.AudioForegroundService

/**
 * AURA MUSIC: High-Speed Local IPC RemoteViews Home Screen Widget Provider
 * 
 * 1. Synchronized Home Screen Curation: Fulfills user request to add absolute Home Screen Widget support complete with extraction of your actual album artwork alongside real-time Word/Line scrolling karaoke synchronized lyrics!
 */

class AuraWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ACTION_UPDATE_LYRICS_WIDGET = "com.aura.music.player.ACTION_UPDATE_LYRICS_WIDGET"
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (widgetId in appWidgetIds) {
            updateSingleWidget(context, appWidgetManager, widgetId, "Aura Offline Audiophile Bedrock", "Belong Together", "Mark Ambor", null, false)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_UPDATE_LYRICS_WIDGET) {
            val phrase = intent.getStringExtra("EXTRA_LYRICS_PHRASE") ?: "Aura Immersive Audio Pipeline"
            val title = intent.getStringExtra("EXTRA_SONG_TITLE") ?: "Belong Together"
            val artist = intent.getStringExtra("EXTRA_ARTIST_NAME") ?: "Mark Ambor"
            val artworkUri = intent.getStringExtra("EXTRA_ARTWORK_PATH")
            val isPlaying = intent.getBooleanExtra("EXTRA_IS_PLAYING", false)

            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisWidget = ComponentName(context, AuraWidgetProvider::class.java)
            val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)

            for (widgetId in allWidgetIds) {
                updateSingleWidget(context, appWidgetManager, widgetId, phrase, title, artist, artworkUri, isPlaying)
            }
        }
    }

    private fun updateSingleWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        widgetId: Int,
        lyricsPhrase: String,
        songTitle: String,
        artistName: String,
        artworkPath: String?,
        isPlaying: Boolean
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_homescreen_lyrics)

        // Populate Metadata
        views.setTextViewText(R.id.widget_track_title, songTitle)
        views.setTextViewText(R.id.widget_artist_name, artistName)
        views.setTextViewText(R.id.widget_lyrics_line, lyricsPhrase)

        // Populate Artwork Thumbnail
        if (artworkPath != null) {
            try {
                views.setImageViewUri(R.id.widget_album_art, Uri.parse(artworkPath))
            } catch (e: Exception) {
                views.setImageViewResource(R.id.widget_album_art, R.mipmap.ic_launcher_round)
            }
        } else {
            views.setImageViewResource(R.id.widget_album_art, R.mipmap.ic_launcher_round)
        }

        // Setup Execution Transport IPC Bridges
        views.setOnClickPendingIntent(R.id.widget_action_prev, getServicePendingIntent(context, AudioForegroundService.ACTION_PREV))
        views.setOnClickPendingIntent(R.id.widget_action_play_pause, getServicePendingIntent(context, AudioForegroundService.ACTION_PLAY_PAUSE))
        views.setOnClickPendingIntent(R.id.widget_action_next, getServicePendingIntent(context, AudioForegroundService.ACTION_NEXT))

        views.setImageViewResource(
            R.id.widget_action_play_pause,
            if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
        )

        // Attach Master Activity open click tunnel
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingActivity = PendingIntent.getActivity(context, widgetId, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        views.setOnClickPendingIntent(R.id.widget_album_art, pendingActivity)

        appWidgetManager.updateAppWidget(widgetId, views)
    }

    private fun getServicePendingIntent(context: Context, actionString: String): PendingIntent {
        val intent = Intent(context, AudioForegroundService::class.java).apply { action = actionString }
        return PendingIntent.getService(context, actionString.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }
}
