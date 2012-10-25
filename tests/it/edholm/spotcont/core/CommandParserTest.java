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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Unit test of the CommandParser.
 *
 * @author Emil Edholm
 * @date 2012-10-24
 */
public class CommandParserTest {

    private JCommander jCommander;
    private CommandLineArguments arguments;
    
    @BeforeTest
    public void prepareTest() {
        jCommander = new JCommander();
    }
    
    @BeforeMethod
    public void setUp() {
        arguments = new CommandLineArguments();
    }
    
    @Test
    public void testDecideToggle() throws Exception {
        arguments.toggle = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.TOGGLE) : "Action Toggle must exist";
    }
    
    @Test
    public void testPrintSong() {
        arguments.printSong = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.PRINT_SONG) : "Action PRINT_SONG must exist";
    }

    @Test
    public void testPrintUsage() {
        arguments.usage = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
    }

    @Test
    public void testPlay() {
        arguments.play = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.PLAY) : "Action PLAY must exist";
    }

    @Test
    public void testPause() {
        arguments.pause = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.PAUSE) : "Action PAUSE must exist";
    }

    @Test
    public void testNext() {
        arguments.next = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 2 : "Size expected: 2, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.NEXT) : "Action NEXT must exist";
        assert parsedActions.contains(SpotifyAction.PRINT_SONG) : "Action PRINT_SONG must exist";
    }

    @Test
    public void testPrevious() {
        arguments.prev = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 2 : "Size expected: 2, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.PREVIOUS) : "Action PREVIOUS must exist";
        assert parsedActions.contains(SpotifyAction.PRINT_SONG) : "Action PRINT_SONG must exist";
    }

    @Test
    public void testIsPlaying() {
        arguments.isPlaying = true;
        Set<Action> parsedActions = getActions();

        assert parsedActions.size() == 1 : "Size expected: 1, actual: " + parsedActions.size();
        assert parsedActions.contains(SpotifyAction.IS_PLAYING) : "Action IS_PLAYING must exist";
    }

    @Test
    public void testQuiet() {
        arguments.quiet = true;
        
        // TODO: Complete testQuiet() 
    }
    
    @Test
    public void testCorrectNumberOfTests() {
        // This test counts the number of declared Test methods in this class
        // and compares it to the number of declared fields in the CommandLineArguments.

        // Decrement with 1 since this test should be counted
        int testMethods = countTestMethods() - 1;
        int fields = countFields(CommandLineArguments.class);
        
        assert testMethods == fields : 
                "Expected " + fields + " methods, found: " + testMethods;
    }
    
    /** Counts the number of test methods in this class */
    private int countTestMethods() {
        Method[] methods = this.getClass().getMethods();
        
        int testCount = 0;
        for(Method m : methods) {
            if(m.getAnnotation(Test.class) != null) {
                testCount++;
            }
        }
        
        return testCount;
    }
    
    /** Searches for non-static fields in the specified class */
    private int countFields(Class<?> classToSearch) {
        Field[] fields = classToSearch.getFields();
        
        int fieldCount = 0;
        for(Field f : fields) {
            // Count all non-static fields.
            if(!Modifier.isStatic(f.getModifiers())) {
                fieldCount++;
            }
        }
        
        return fieldCount;
    }
    
    private Set<Action> getActions() { 
        return new CommandParser(arguments, jCommander).decideAction(); 
    }
}
