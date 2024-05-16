# Targeter
Gives you the ability to choose which entities target you.

## Versions

> [!NOTE]
> Works for Minecraft Spigot version `1.20.1`. Recommended versions `1.17` and up.

## Commands

### /targetadd

The command `/targetadd` takes in two parameters. The first one being the player name, the second being the `EntityType` in lowercase.
An example of the command: `/targetadd Pride spider`. This will add `"spider"` to the list of mobs that won't target user Pride anymore, and so
Spiders in game will no longer target that user even if they hit them.

### /targetremove

The command `/targetremove` also takes in two parameters. Again, the first one being the player name and the second being the `EntityType` in lowercase.
An example of the command: `/targetremove Pride spider`. This will remove `"spider"` to the list of mobs that won't target user Pride anymore, and so
Spiders in game target that user normally again.

## `EntityType` "hostile"

If you're too lazy to or you want to simulate a "peaceful" gamemode, you can also choose `hostile` when you're typing in the second parameter.
This will make all hostile mobs no longer target the user.

## Permissions

```
targeter.command.add
```

```
targeter.command.remove
```
