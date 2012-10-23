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
package it.edholm.spotcont.core.models;

/**
 * Represents a song (Artist, title, album and year).
 *
 * @author Emil Edholm
 * @date   2012-10-23
 */
public class Song {
    private final String artist, title, album;
    private final int year;

    public Song(String artist, String title, String album, int year) {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.year = year;
    }
    
    /** The name of the artist(s) */
    public String getArtist() {
        return artist;
    }
    /** The song title */
    public String getTitle() {
        return title;
    }
    
    /** Album name */
    public String getAlbum() {
        return album;
    }
    
    /** The year the album was released */
    public int getYear() {
        return year;
    }

    /**
     * @return returns the {@code Song} in the format of <i>Artist - Title (Album (year)) </i>
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%s (%s))", artist, title, album, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Song song = (Song) o;

        if (year != song.year) return false;
        if (album != null ? !album.equals(song.album) : song.album != null)
            return false;
        if (artist != null ? !artist.equals(song.artist) : song.artist != null)
            return false;
        
        return !(title != null ? !title.equals(song.title) : song.title != null);
    }

    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }
}
