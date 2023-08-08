# GamePass Save Converter

Tool to convert saves from Xbox GamePass games (or other Microsoft Store games) into a more standard and readable format
(ie. compatible with other sources like Steam, Epic Game Store or GoG).

Most of the time, saves from MS packages are just the same as for other stores with a specific renaming and index files.
The original file names can be retrieved by parsing the containers.index and container.* files. As for now, GPSC only
parse these files and rename the saves.

This project has been partly inspired by [GP Save Converter](https://github.com/Fr33dan/GPSaveConverter), written in C#
with a more user friendly interface for Windows, but which was unable to convert saves for games not listed in the
application.

## Build

With a proper Java installation available, run:
```
./gradlew assemble
```

The application will be available into ```cli/build/distributions```.

## Usage

GPSC require Java 12 or higher (JDK or JRE).

The command line tool have two commands :
* _list_ will display details about all the files found in the save directory
* _convert_ will copy and rename all the files with their more readable name

Options:
* _--path (or -p)_: Path to containers.index, or a directory with this file (default is current directory)
* _--destination (or -d)_: Directory to write converted save, only for _convert_ command (default is ./output/)

Example:
```
gpsc convert -p ./SomethingWeMade.TOEM_3b9evzcrg4em8/SystemAppData/wgs/000900000223B718_0000000000000000000000007E270A5A -d ./toem_save/
```

## FAQ

### Where can I find the save for my Microsoft Store game ?
Saves for Microsoft Store games are located under ```%USERPROFILE%\AppData\Local\Packages```. This directory contains a
sub directory for each game, usually with a name composed of the editor, the game name and an identifying sequence
(ie. ```SomethingWeMade.TOEM_3b9evzcrg4em8``` for TOEM). The save itself is located in a subdirectory under
```SystemAppData\wgs```, in a directory with a long UID composed of numbers and letters.

### Where can I find the save for my Steam/GoG/Epic version of the game ?
It depends of the game since each one have its own way of storing save files. Have a look at the page of the game on
[PC Gaming Wiki](https://www.pcgamingwiki.com/wiki/Home), or the "cloud saves" infos on [SteamDB](https://steamdb.info).

### The converted save didn't worked with my Steam version of the game
Many games keep a similar file format between Microsoft Store and other stores, but some have different naming and even
sometimes different binary format, so using the save converted by GPSC might not work.

### Will the use of the converted save unlock my Steam achievements ?
It depends on how the game handle achievements. For some games, using a save with unlocked achievements and start the
game will unlock all of them in a few seconds, for others, they unlock only at the moment you meet the expected
requirements.

### Why isn't there a GUI for GPSC ?
I plan to work on a multiplatform GUI (using Compose Desktop) in the near future. For another application with a GUI,
have a look at [GP Save Converter](https://github.com/Fr33dan/GPSaveConverter)
