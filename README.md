spotcont
=======
A Spotify controller using DBus and written in Java.

_Note that this project is deprecated and probably doesn't work anymore... :)_

Requires
------------
 * [Spotify >= 0.4.8.282][1]
 * [jdk >= 1.7][2]
 * [jcommander][3]
 * [dbus-java][4]
 * [TestNG][7] - Only for unit tests


License
-----------
Copyright (C) 2012 Emil Edholm 

This software is licensed under the [GPLv3][5]


Build
------
At the moment (v1.0-beta) there exists no ant build file, but may be included in a future release. 

*To build the project*

 * `# git clone git://github.com/Edholm/spotcont.git`
 * Import your project into your favorite IDE. I used [IntelliJ IDEA][6].
 * Use `insert IDE here` to build the project.

Example usage
----------------------
If we assume you have a shell script that launches spotcont.

`# spotcont --print-song`

Will for example print: `Kavinsky - Nightcall (Nightcall (2010))`


For more options:
`spotcont -h` or `spotcont --help`


[1]: http://www.spotify.com/se/download/previews/                        "Spotify preview"
[2]: http://www.oracle.com/technetwork/java/javase/downloads/index.html  "Oracle jdk"
[3]: http://jcommander.org/                                              "jCommander"
[4]: http://dbus.freedesktop.org/doc/dbus-java/                          "dbus-java"
[5]: http://www.gnu.org/licenses/gpl.txt                                 "GPLv3 license"
[6]: http://www.jetbrains.com/idea/                                      "IntelliJ IDEA"
[7]: http://testng.org/                                                  "TestNG"
