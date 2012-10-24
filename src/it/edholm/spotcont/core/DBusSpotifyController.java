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

import it.edholm.spotcont.core.models.Song;
import org.freedesktop.DBus;
import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class description goes here
 *
 * @author Emil Edholm
 * @date 10/23/12
 */
public class DBusSpotifyController implements Spotify {

    private static final Logger LOGGER = Logger.getLogger(DBusSpotifyController.class.getName());

    private static final String SPOTIFY_DBUS_INTERFACE_NAME = "org.mpris.MediaPlayer2.Player";
    private static final String SPOTIFY_DBUS_SERVICE_NAME   = "com.spotify.qt";
    private static final String SPOTIFY_DBUS_PATH           = "/org/mpris/MediaPlayer2";

    private DBusConnection  dBusConnection;
    private Player          spotifyMethods;
    private DBus.Properties spotifyProperties;
    private boolean         isConnected;

    @Override
    public Song getSong() {
        if (!connect()) return null;

        Map<String, Variant> allProps = spotifyProperties.GetAll(SPOTIFY_DBUS_INTERFACE_NAME);
        Variant metadata = allProps.get("Metadata");
        Map<String, Variant> metaMap = (Map<String, Variant>) metadata.getValue();

        Iterator<Map.Entry<String, Variant>> iterator = metaMap.entrySet().iterator();
        Map<String, Object> spotifyMetadata = new HashMap<String, Object>(metaMap.size());

        Map.Entry<String, Variant> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();


            Object value = entry.getValue().getValue();
            spotifyMetadata.put(entry.getKey(), value);
        }


        disconnect();
        return Song.valueOf(spotifyMetadata);
    }

    @Override
    public void toggle() {
        if (!connect()) return;

        boolean possible = canToggle();
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Toggle possible: " + possible);
        }
        if (possible) {
            spotifyMethods.PlayPause();
            LOGGER.log(Level.INFO, "LOG00050: Toggling playback");
        }

        disconnect();
    }

    @Override
    public void play() {
        if (connect() && canPlay()) {
            spotifyMethods.Play();
            LOGGER.log(Level.INFO, "LOG00040: Starting playback");
        }

        disconnect();
    }

    @Override
    public void pause() {
        if (connect() && canPause()) {
            spotifyMethods.Stop();
            LOGGER.log(Level.INFO, "LOG00030: Pausing playback");
        }

        disconnect();
    }

    @Override
    public void next() {
        if (connect() && canGoNext()) {
            spotifyMethods.Next();
            LOGGER.log(Level.INFO, "LOG00060: Playing next song");
        }
        
        disconnect();
    }

    @Override
    public void previous() {
        if (connect() && canGoPrevious()) {
            spotifyMethods.Previous();
            LOGGER.log(Level.INFO, "LOG00070: Playing previous song");
        }
        
        disconnect();
    }

    @Override
    public boolean isPlaying() {
        if (!connect()) return false;

        String status = getPlaybackStatus();
        disconnect();
        
        switch (status) {
            case "Playing":
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isRunning() {
        return isConnected;
    }

    /** @return true if connection successfull or already connected, else false */
    private boolean connect() {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Attempting to connect to DBus");
        }
        if(dBusConnection != null && isConnected) {
            if (LOGGER.isLoggable(Level.FINE)) { LOGGER.log(Level.FINE, "DBus already connected."); }
            
            return true;
        }
        
        try {
            dBusConnection = DBusConnection.getConnection(DBusConnection.SESSION);
            spotifyMethods = dBusConnection.getRemoteObject(SPOTIFY_DBUS_SERVICE_NAME, SPOTIFY_DBUS_PATH, Player.class);
            spotifyProperties = (DBus.Properties) dBusConnection.getRemoteObject(SPOTIFY_DBUS_SERVICE_NAME, SPOTIFY_DBUS_PATH);
            isConnected = true;
        } catch (DBusException e) {
            isConnected = false;
            LOGGER.log(Level.WARNING, "LOG00020: connect()", e);
            disconnect();
        }
        
        return isConnected;
    }

    private void disconnect() {
        dBusConnection.disconnect();

        dBusConnection = null;
        spotifyMethods = null;
        spotifyProperties = null;

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Disconnecting dbus");
        }
    }

    private boolean canGoNext() {
        return readProp("CanGoNext");
    }

    private boolean canGoPrevious() {
        return readProp("CanGoPrevious");
    }

    private boolean canPause() {
        return readProp("CanPause");
    }

    private boolean canPlay() {
        return readProp("CanPlay");
    }

    public boolean canToggle() {
        return canPlay() || canPause();
    }

    public String getPlaybackStatus() {
        return readProp("PlaybackStatus");
    }

    private <A> A readProp(String propName) {
        return spotifyProperties.Get(SPOTIFY_DBUS_INTERFACE_NAME, propName);
    }


    /** This interface matches the DBus Spotify interface */
    @DBusInterfaceName(SPOTIFY_DBUS_INTERFACE_NAME)
    private interface Player extends DBusInterface, DBus.Properties {

        void Next();

        void Previous();

        void Play();

        void PlayPause();

        void Stop();

        // Variant Metadata();
    }
}
