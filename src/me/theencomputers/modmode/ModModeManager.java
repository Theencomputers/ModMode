/*
	Author: Theencomputers
	Title: ModModeManager.java
*/
package me.theencomputers.modmode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ModModeManager {
    public static ArrayList<Player> inVanishList = new ArrayList<Player>();                                     
    public static ArrayList<Player> inModMode = new ArrayList<Player>();                                        //List that contains all players in modmode
    public static ArrayList<Location> unexposedLocations = new ArrayList<Location>();                           //List that contains all unexposed ore locations
    public static ArrayList<Location> diamondVeiLocations = new ArrayList<Location>();                          //List that contains diamond vein locations
    public static ArrayList<Location> goldVeinLocations = new ArrayList<Location>();                            //List that contains gold vein locations
    public static Map<Player, Integer> playerDiamondCount = new HashMap<Player, Integer>(10);    //Map that contains player as key and number of diamonds as value
    public static Map<Player, Integer> playerGoldCount = new HashMap<Player, Integer>(10);       //Map that contains Player as key and number of gold as value

}
