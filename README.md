# JoinMessagePlus
![Build and Test](https://github.com/4ndyZ/JoinMessagePlus/workflows/Build%20and%20Test/badge.svg) ![Release](https://github.com/4ndyZ/JoinMessagePlus/workflows/Release/badge.svg)

[JoinMessagePlus](https://dev.bukkit.org/projects/join-message-plus/) is a Paper/BungeeCord Plugin which allows you to change or turn off the join and quit messages of Minecraft.

Original by MrKinau.

## Example
Join-Message:

![Join-Message](https://i.imgur.com/oRt4ljY.png)

Leave-Message:

![Leave-Message](https://i.imgur.com/ooRjyOS.png)

## Features
Supports the following plugins [PlaceHolderAPI](https://www.spigotmc.org/wiki/placeholderapi-placeholders/), [AuthMeReloaded](https://dev.bukkit.org/projects/authme-reloaded) and [BungeeCord](https://ci.md-5.net/job/BungeeCord/).

## Installation
[Download](https://dev.bukkit.org/projects/join-message-plus/files) the lastest version of the plugin and put it in the `plugin` directory of your Bukkit/Spigot or BungeeCord server. Reload or restart your Minecraft server.

## Configuration
Paper:

The configuration file is located under `plugins/JoinMessagePlus/config-bukkit.yml`.
```yaml
Syntax: "minimessage" # can be vanilla or minimessage.
BungeeSupport:
  Enabled: false
AuthMeSupport:
  Enabled: false
JoinMessage:
  Enabled: true
  Message: '<#FFAA00>[<#55FF55>+</#55FF55>] <player_name> </#FFAA00>'
QuitMessage:
  Enabled: true
  Message: '<#FFAA00>[<#AA0000>-</#AA0000>] <player_name> </#FFAA00>'
```
BungeeCord:

The configuration file is located under `plugins/JoinMessagePlus/config-bungee.yml`.
```yaml
Syntax: "minimessage" # can be vanilla or minimessage.
GlobalJoinMessage:
  Enabled: true
  Message: '<#FFAA00>[<#55FF55>+</#55FF55>] [<#55FFFF>GLOBAL</#55FFFF>] <player_name> </#FFAA00>'
GlobalQuitMessage:
  Enabled: true
  Message: '<#FFAA00>[<#AA0000>-</#AA0000>] [<#55FFFF>GLOBAL</#55FFFF>] <player_name> </#FFAA00>'
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GPL-3.0](https://github.com/4ndyZ/JoinMessagePlus/blob/main/COPYING)