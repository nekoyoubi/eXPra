Overview
----------
eXPra allows players to be rewarded experience orbs ("XP") for other tasks than just killing monsters.

Currently, players are only rewarded XP from slain enemies (and other players). By contrast, with eXPra, players will earn XP through various activities other than killing ranging from building houses to fishing to exploring untouched areas and everything in between.

Usage
-------
There really is nothing special a player needs to do to use eXPra; simply carry on as usual.

The `/expra` or `/xp` command will give players information about their experience and level.

Also, if you are using the PermissionsEx plugin, players with the `expra.adjust` permission can set/adjust player levels with the following commands:

* `/expra(xp) set(s) playername(blank for self) level(blank for 0)`
* `/expra(xp) adjust(a|adj|add) playername(blank for self) (-)levels(blank for 0)`

Rewards
---------
Rewards are configurable, but by default the following activities will drop XP in addition to XP that already drops from killing monsters. This allows for additional XP gain for people that enjoy building or helping the server community by harvesting, fishing, crafting, or exploring.

* Breaking or digging blocks
* Placing blocks (building!)
* Planting saplings and seeds
* Lighting unlit areas
* Fishing
* Crafting items at a workbench or forge (delayed)

Death
------
Death XP handling can be configured to either drop experience from players as usual or not at all, and to fine tune the amount of percentage of experience lost upon death.

_Note: The configurable amount of loss does not translate to the amount dropped. The normal Minecraft drop will still occur if the option is enabled._