package de.ph1b.audiobook.mediaplayer


import android.content.Context
import android.media.MediaPlayer

import java.io.IOException


/**
 * Basic interface defining our interaction with the media player implementation.
 */
interface MediaPlayerInterface {

    fun release()

    fun start()

    fun reset()

    @Throws(IOException::class)
    fun prepare()

    var currentPosition: Int

    fun pause()

    var playbackSpeed: Float

    @Throws(IOException::class)
    fun setDataSource(source: String)

    fun setOnErrorListener(onErrorListener: MediaPlayer.OnErrorListener)

    fun setOnCompletionListener(onCompletionListener: OnCompletionListener)

    fun setWakeMode(context: Context, mode: Int)

    val duration: Int

    interface OnCompletionListener {
        fun onCompletion()
    }
}