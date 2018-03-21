package com.asteria.game.item.impl;

import java.util.Arrays;
import java.util.Optional;

import com.asteria.game.character.Animation;
import com.asteria.game.character.combat.ranged.CombatRangedAmmo;
import com.asteria.game.character.player.Player;
import com.asteria.game.character.player.skill.Skills;
import com.asteria.game.item.Item;
import com.asteria.game.item.ItemDefinition;
import com.asteria.game.location.Position;
import com.asteria.utility.Stopwatch;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

/**
 * Teleport tablet click handler
 *
 * @author trees
 */
public class TeleportTablet {

    /**
     * The starting location of your server.
     */
    public static final int START_LOCATION_X = 3088;
    public static final int START_LOCATION_Y = 3505;

    /**
     * The re-spawn point of when someone dies.
     */
    public static final int RESPAWN_X = 3088;
    public static final int RESPAWN_Y = 3505;

    /**
     * The re-spawn point of when a duel ends.
     */
    public static final int DUELING_RESPAWN_X = 3362;
    public static final int DUELING_RESPAWN_Y = 3263;

    /**
     * Glory locations.
     */
    public static final int EDGEVILLE_X = 3087;
    public static final int EDGEVILLE_Y = 3493;

    public static final int AL_KHARID_X = 3293;
    public static final int AL_KHARID_Y = 3176;

    public static final int KARAMJA_X = 2925;
    public static final int KARAMJA_Y = 3173;

    public static final int DRAYNOR_X = 3079;
    public static final int DRAYNOR_Y = 3250;

    public static final int MAGEBANK_X = 2538;
    public static final int MAGEBANK_Y = 4716;

    /*
     * Modern spells
     */
    public static final int VARROCK_X = 3210;
    public static final int VARROCK_Y = 3424;

    public static final int LUMBY_X = 3222;
    public static final int LUMBY_Y = 3218;

    public static final int FALADOR_X = 2964;
    public static final int FALADOR_Y = 3378;

    public static final int CAMELOT_X = 2757;
    public static final int CAMELOT_Y = 3477;

    public static final int ARDOUGNE_X = 2662;
    public static final int ARDOUGNE_Y = 3305;

    public static final int WATCHTOWER_X = 2549;
    public static final int WATCHTOWER_Y = 3112;

    public static final int TROLLHEIM_X = 2888;
    public static final int TROLLHEIM_Y = 3676;

    /*
     * Ancient spells
     */
    public static final int PADDEWWA_X = 3098;
    public static final int PADDEWWA_Y = 9884;

    public static final int SENNTISTEN_X = 3322;
    public static final int SENNTISTEN_Y = 3336;

    public static final int KHARYRLL_X = 3492;
    public static final int KHARYRLL_Y = 3471;

    public static final int LASSAR_X = 3006;
    public static final int LASSAR_Y = 3471;

    public static final int DAREEYAK_X = 2966;
    public static final int DAREEYAK_Y = 3695;

    public static final int CARRALLANGAR_X = 3156;
    public static final int CARRALLANGAR_Y = 3666;

    public static final int ANNAKARL_X = 3288;
    public static final int ANNAKARL_Y = 3886;

    public static final int GHORROCK_X = 2977;
    public static final int GHORROCK_Y = 3873;

    /**
     * Lunar spells
     */
    public static final int MOONCLAN_X = 2111;
    public static final int MOONCLAN_Y = 3915;

    public static final int OURANIA_X = 2469;
    public static final int OURANIA_Y = 3245;

    public static final int WATERBIRTH_X = 2550;
    public static final int WATERBIRTH_Y = 3755;

    public static final int BARBARIAN_X = 2544;
    public static final int BARBARIAN_Y = 3569;

    public static final int KHAZARD_X = 2634;
    public static final int KHAZARD_Y = 3168;

    public static final int FISHING_GUILD_X = 2614;
    public static final int FISHING_GUILD_Y = 3381;

    public static final int CATHERBY_X = 2804;
    public static final int CATHERBY_Y = 3434;

    public static final int ICE_PLATEU_X = 2951;
    public static final int ICE_PLATEU_Y = 3936;

    public enum TabType {
        HOME(8013, RESPAWN_X, RESPAWN_Y),
        ANNAKARL(12775, ANNAKARL_X, ANNAKARL_Y),
        CARRALLANGER(12776, CARRALLANGAR_X, CARRALLANGAR_Y),
        DAREEYAK(12777, DAREEYAK_X, DAREEYAK_Y),
        GHORROCK(12778, GHORROCK_X, GHORROCK_Y),
        KHARYRLL(12779, KHARYRLL_X, KHARYRLL_Y),
        LASSAR(12780, LASSAR_X, LASSAR_Y),
        PADDEWWA(12781, PADDEWWA_X, PADDEWWA_Y),
        SENNTISTEN(12782, SENNTISTEN_X, SENNTISTEN_Y),
        WILDY_RESOURCE(12409, 3184, 3945),
        PIRATE_HUT(12407, 3045, 3956),
        MAGE_BANK(12410, 2538, 4716),
        CALLISTO(12408, 3325, 3849),
        KBD_LAIR(12411, 2271, 4681),

        //City teleports
        VARROCK(8007, VARROCK_X, VARROCK_Y),
        LUMBRIDGE(8008, LUMBY_X, LUMBY_Y),
        FALADOR(8009, FALADOR_X, FALADOR_Y),
        CAMELOT(8010, CAMELOT_X, CAMELOT_Y),
        ARDOUGNE(8011, ARDOUGNE_X, ARDOUGNE_Y),
        TROLLHEIM(11747, TROLLHEIM_X, TROLLHEIM_Y);


        private int tab;
        private int x;
        private int y;

        public int getTabId() {
            return tab;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        TabType(int tab, int x, int y) {
            this.tab = tab;
            this.x = x;
            this.y = y;
        }
    }

    ;

    /**
     * Operates the teleport tab
     *
     * @param player
     * @param item
     */
    public static boolean operate(final Player player, int item) {
        for (TabType type : TabType.values()) {
            if (type.getTabId() == item && player.getInventory().contains(item)) {
                player.animation(new Animation(4731));
                player.getInventory().remove(new Item(item, 1));
                player.getMessages().sendGraphic(678, player.getPosition(), 0);
             //   player.move(new Position(type.getX(), type.getY()));
               // player.setPlayer_teleport_location_x( type.getX());
              //  player.setPlayer_teleport_location_y( type.getY());
                player.getMessages().sendMessage("You break the teleport tablet.");

                return true;
                //   if (!player.getPA().canTeleport("modern")) {
                //       return;
                //    }
             /*   player.teleporting = true;
                player.getItems().deleteItem(type.getTabId(), 1);
                player.lastTeleport = System.currentTimeMillis();
                player.startAnimation(4731);
                player.gfx0(678);
                final int x = type.getX();
                final int y = type.getY();
                CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {

                    @Override
                    public void execute(CycleEventContainer container) {
                        player.teleportToX = x;
                        player.teleportToY = y;
                        player.heightLevel = 0;
                        player.teleporting = false;
                        player.gfx0(-1);
                        player.startAnimation(65535);
                        container.stop();
                    }

                }, 3);*/
            }
        }
        return false;
    }
}
