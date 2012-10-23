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

import java.util.EnumSet;

/**
 * Class description goes here
 *
 * @author Emil Edholm
 * @date 2012-10-23
 */
public class SpotCont {

    public static void main(String[] argv) {
        argv = new String[]{"-p", "-l"};

        init(argv);
    }
    
    private static void init(String[] argv) {
        CommandLineArguments cmdArgs = new CommandLineArguments();
        JCommander jCom = new JCommander(cmdArgs, argv);

        ApplicationInfo ai = ApplicationInfo.getInstance();
        jCom.setProgramName(ai.getApplicationName());

        switch (argv.length) {
            case 0:
                jCom.usage();
                break;
            default:
                CommandParser parseOptions = new CommandParser(cmdArgs, jCom);
                EnumSet<SpotifyAction> actions = parseOptions.decideAction();

                for(SpotifyAction act : actions) {
                    act.doAction();
                }
                break;
        }
    }
}
