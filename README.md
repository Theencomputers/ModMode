# ModMode
Moderator tools for 1.7 servers: Featuring Modinfo, spectating mode, and vanish mode.

## Features
  - Complete Vanish, hides your name from tab list, hides join and leave messages, and does not effect collision allowing mods and arrows to go throught you
  - Mining alerts including special alerts when a player mines an unexposed ore
  - PvP Alerts
  - Clickable alerts that teleport you to events
  - Inventory viewer including armor slots
  - Random teleporter item
  - Fly speed changer Item
## Commands
 <br> **Vanish Alias: /v  Permission: modmode.staff** <br>
   - /vanish   - Togles vanish mode
   - /v        - Vanish Alias
 ##
**ModMode** <br>
   - /modmode on [player]  - Enables modmode for self or player
   - /modmode off [player] - Disables modmode for self or player
   - /modmode toggle [player] - toggles modmode for self or player
   - /modmode list  - lists all players in modmode
##
**spec Permission: none (must be in modmode)** <br>
   - /spec <player>  - Teleports moderator to player
##
  **tppos Permission: non (must be in modmode)**<br>
  - /tppos <world> <x> <y> <z> - Teleports moderator to a specific location (used for clickable alerts)
# Demonstration
  ##
**Mining Alerts**
  <br>![Unexposed Diamond Alert](/readme_assets/miningalert.png)
  <br> Shown: Unexposed ore was mined by player, Theen, which then mined a 4 vein of diamonds, Theen has only mined one diamond
  <br> Alerts are not sent for every ore only for every vein to reduce spam
 ##
**PvP Alerts**
  <br> ![PvP Alert](/readme_assets/pvpalert.png)
  <br> Shown: Player theencomputers dealt 1.0 damage (half heart) to player Theen
##
**Inventory Viewer**
  <br> ![Inventory Viewer](/readme_assets/invsee.png)
  <br> Upon right cliking the book and quil the moderator will be shown the inventory of the  nearest player including their armor
  <br> Shown: The moderator is viewing the inventory of Theen
##
**Random Teleport**
  <br> ![Random Teleporter](/readme_assets/rtp.png)
  <br> Upon right clicking the blaze rod the moderator will be teleported to a random player
  <br> Shown: The moderator has been spamming the teleporter and being teleported between Theen and NotTheen
##
**Clickable Alerts**
  <br> ![Clcikable Mining Alert](/readme_assets/clickabletext.png)
  <br> Shown: Hover message over mining alert upon clinking the moderator will be teleported to the event using the /tppos command

 
