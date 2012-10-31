/*
 * Copyright (c) 2012. Emil Edholm
 *     
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.edholm.spotcont.core.api;

import it.edholm.spotcont.core.models.Song;

/**
 * Class description goes here
 *
 * @author Emil Edholm
 * @date 2012-10-23
 */
public interface Spotify {

    /**
     * Returns the current song playing.
     * @return returns the current song playing on Spotify, or null if no song
     *         playing or some error occurred.
     */
    Song getSong();

    /** 
     * Toggles the playback. If Spotify is playing the playback will be paused
     * and vice versa.
     */
    void toggle();

    /** Resume playback */
    void play();

    /** Pause playback. */
    void pause();
    
    /** Play the next song */
    void next();
    
    /** Play the previous song */
    void previous();
    
    /** @returns true if spotify is running and playing a song */
    boolean isPlaying();
    
    /** @returns true if spotify is running, else false */
    boolean isRunning();
}
