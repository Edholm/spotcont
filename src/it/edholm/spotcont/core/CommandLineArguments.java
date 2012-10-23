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
 * @date   2012-10-23
 */
class CommandLineArguments {
    @Parameter(names = { "-h", "--help" }, description = "Shows this message")
    public boolean usage;

    @Parameter(names = {"-t", "--toggle"}, description = "Toggle playback")
    public boolean toggle;
    
    @Parameter(names = {"-l", "--list"}, description = "Show the current playing song")
    public boolean list = true;
    
    @Parameter(names = {"-p", "--play"}, description = "Start playback")
    public boolean play ;
    
    @Parameter(names = {"-s", "--stop"}, description = "Stop playback")
    public boolean stop;
}
