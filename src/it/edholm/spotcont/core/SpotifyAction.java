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
package it.edholm.spotcont.core;

import it.edholm.spotcont.core.utilities.IFactory;
import it.edholm.spotcont.core.models.Song;

/**
 * Used to simplify doing an action parsed from the command line.
 *
 * @author Emil Edholm
 * @date 2012-10-23
 */
public enum SpotifyAction {
    PRINT_SONG {
        @Override
        public void doAction() {
            Song song = spotifyController.getSong();
            System.out.print(song != null ? song.toString() : "No song playing\n");
        }
    },
    PLAY {
        @Override
        public void doAction() {
            spotifyController.play();
        }
    },
    PAUSE {
        @Override
        public void doAction() {
            spotifyController.pause();
        }
    },
    TOGGLE {
        @Override
        public void doAction() {
            spotifyController.toggle();
        }
    },
    NOTHING {
        @Override
        public void doAction() { /* Do nothing. Useful to avoid null checks */ }
    };

    private static final Spotify spotifyController;

    static {
        IFactory<Spotify> factory = new SpotifyControllerFactory();
        spotifyController = factory.create();
    }

    public abstract void doAction();
}
