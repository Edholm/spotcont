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

import com.beust.jcommander.JCommander;

import java.util.Set;
import java.util.TreeSet;

/**
 * Parses and constructs a list of actions to take based on the command line
 * arguments.
 *
 * @author Emil Edholm
 * @date 10/23/12
 */
class CommandParser {

    private final CommandLineArguments   options;
    private final JCommander             jCommander;
    private       Set<Action> action;

    CommandParser(CommandLineArguments options, JCommander jCommander) {
        this.options = options;
        this.jCommander = jCommander;
        action = new TreeSet<Action>();
    }

    public Set<Action> decideAction() {
        action.clear();
        
        if (options.usage) {
            jCommander.usage();
            action.add(SpotifyAction.NOTHING);
        }
        if (options.printSong) {
            action.add(SpotifyAction.PRINT_SONG);
        }
        if (options.play) {
            action.add(SpotifyAction.PLAY);
        }
        if (options.pause) {
            action.add(SpotifyAction.PAUSE);
        }
        if (options.toggle) {
            action.add(SpotifyAction.TOGGLE);
        }
        if(options.next) {
            action.add(SpotifyAction.NEXT);
        }
        if(options.prev) {
            action.add(SpotifyAction.PREVIOUS);
        }
        if(options.isPlaying) {
            action.add(SpotifyAction.IS_PLAYING);
        }
        
        return action;
    }
}
