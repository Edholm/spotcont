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

import com.beust.jcommander.Parameter;

/**
 * Describes the available command line options.
 *
 * @author Emil Edholm
 * @date 2012-10-23
 */
class CommandLineArguments {

    public static final String CMD_USAGE = "--help";
    @Parameter(names = {"-h", CMD_USAGE}, description = "Shows this message")
    public boolean usage;

    public static final String CMD_TOGGLE = "--toggle";
    @Parameter(names = {"-t", CMD_TOGGLE}, description = "Toggle playback")
    public boolean toggle;

    public static final String CMD_PRINT_SONG = "--print-song";
    @Parameter(names = {"-l", CMD_PRINT_SONG}, description = "Print the currently playing song")
    public boolean printSong;

    public static final String CMD_PLAY = "--play";
    @Parameter(names = {"-p", CMD_PLAY}, description = "Start playback")
    public boolean play;

    public static final String CMD_PAUSE = "--pause";
    @Parameter(names = {"-s", CMD_PAUSE}, description = "Pause playback")
    public boolean pause;

    public static final String CMD_NEXT = "--next";
    @Parameter(names = {CMD_NEXT}, description = "Play next song")
    public boolean next;

    public static final String CMD_PREV = "--prev";
    @Parameter(names = {CMD_PREV}, description = "Play previous song")
    public boolean prev;

    public static final String CMD_IS_PLAYING = "--is-playing";
    @Parameter(names = {CMD_IS_PLAYING}, description = "Prints whether or not spotify is playing")
    public boolean isPlaying;
    
    public static final String CMD_QUIET = "--quiet";
    @Parameter(names = {CMD_QUIET}, description = "Disable logging and unnecessary console output")
    public boolean quiet;
}
