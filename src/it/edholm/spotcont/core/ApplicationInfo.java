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

/**
 * Singleton for getting various application information such as name, version etc.
 *
 * @author Emil Edholm
 * @date 2012-10-23
 */
public class ApplicationInfo {

    private static ApplicationInfo ourInstance;

    private ApplicationInfo() { }

    public static ApplicationInfo getInstance() {
        if (ourInstance == null) {
            ourInstance = new ApplicationInfo();
        }
        return ourInstance;
    }

    public String getApplicationName() {
        return "SpotCont";
    }

    public String getVersion() {
        return "V0.1-alpha";
    }

    public Spotify getSpotifyController() {
        // TODO: Return a concrete implementation when that has been written.
        return null;
    }
}
