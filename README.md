Overview
----------
eXPra allows players to be rewarded experience orbs ("XP") for other tasks than just killing monsters.

Currently, players are only rewarded XP from slain enemies (and other players). By contrast, with eXPra, players will earn XP through various activities other than killing ranging from building houses to fishing to exploring untouched areas and everything in between.

Rewards
---------
Rewards are configurable, but by default the following activities will drop XP in addition to XP that already drops from killing monsters. This allows for additional XP gain for people that enjoy building or helping the server community by harvesting, fishing, crafting, or exploring.

* Breaking or digging blocks
* Placing blocks (building!)
* Planting saplings and seeds
* Exploring (lighting unlit areas)
* Going fishing
* Taming wolves
* Breeding animals (coming soon)
* Crafting items at a workbench or forge (delayed)

Death
------
Death XP handling can be configured to either drop experience from players as usual, not at all, or to fine tune the amount of percentage of experience lost upon death.

Usage
-------
There really is nothing special a player needs to do to use eXPra; simply carry on as usual.

The `/expra` or `/xp` command will give players information about their experience and level.

Also, if you are using the PermissionsEx plugin, players with the `expra.adjust` permission can set/adjust player levels with the following commands:

* `/expra(xp) set(s) playername(blank for self) level(blank for 0)`
* `/expra(xp) adjust(a|adj|add) playername(blank for self) (-)levels(blank for 0)`

Permissions
-------------
If you are using PermissionsEx, you can (need to) assign the following permissions to their respective groups and users.

* `expra.adjust`
    This is for administrators and moderators only! Use with caution. Allows the user to set their own, and others' level.
* `expra.award.*`
    This will allow the user to receive all addition XP rewards that eXPra offers.
* `expra.award.break`
    Reward the user for breaking blocks as per the configuration file.
* `expra.award.place`
    Reward the user for placing blocks as per the configuration file.
* `expra.award.fish`
    Reward the user for fishing as per the configuration file.
* `expra.award.explore`
    Reward the user for exploring (lighting) as per the configuration file.
* `expra.award.tame`
    Reward the user for taming wolves as per the configuration file.
* `expra.death.noloss`
    This will allow the user to keep all XP upon death regardless of configuration.
* `expra.death.loss`
    This will allow the user to only lose a configurable amount of experience. _Without this or `expra.death.noloss`, the user will lose all levels and XP as per standard MC loss._

