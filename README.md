# `worldmanager`
worldmanager (in lowercase) is an advanced world manager for Minecraft. It can save and restore backups, as well as automatically backup when Minecraft is closed.

**NOT AN OFFICIAL MINECRAFT PROGRAM. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT**

worldmanager does not need to be installed, just simply download the .zip file and run it.

## Features
- Save and restore backups
- Unzip directly to `.minecraft/saves` without manually extracting
- Edit NBT data of your world
- Gives simplified world statistics (days, difficulty, players, advancements made, etc.)
- and more!

## Feature requests
If you want to submit a feature request, don't hesitate to submit a pull request/issue. Your features will get accepted.
## Debugging
If you found bugs or have noticed unusual things, please use the `--verbose` flag to output logs to your console, OR find the worldmanager.log file and send it to us via an issue.
## License
This project is licensed under the MIT License.
## Updates
This project also supports updates, which means you only have to download worldmanager once. You can also specify the repository (defaults to https://github.com/thebest12dev/worldmanager/releases/latest/download/worldmanager.jar) to update from, however make sure you trust the source you are downloading from.
## Building
To build the project, you need the following tools:
- A working JDK (with `jlink`)
- `g++` for C++ compiling
- Maven (to package)

To build, simply run `build.bat` in the project root, and it should build the project.
## Attribution
[Querz/NBT](https://github.com/Querz/NBT) for the NBT library (thanks!)
<br>
[Bootstrap Icons](https://icons.getbootstrap.com) for the icons
<br>
[jopt-simple/jopt-simple](https://github.com/jopt-simple/jopt-simple) for the command-line parser
