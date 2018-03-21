package com.jagex;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Configuration {

    private Configuration() {

    }

    public static final BigInteger RSA_MODULUS = new BigInteger(
            "158524451491299768481335406949632722930001467869157605890403538922079064140031175070843389751771070828946840977662555424706722104406478161144373150173642644879920983864580051507542828646901725343022346327871558268510881848702090892750832077808168455305660457441192814181290396639012661208328185464354643376401");

    public static final BigInteger RSA_EXPONENT = new BigInteger("65537");

    /**
     * Sends client-related debug messages to the client output stream
     */
    public static boolean client_debug = true;

    /**
     * The address of the server that the client will be connecting to
     */

    public static boolean is_live = false;

    public static String server_address = is_live ? "" : "127.0.0.1";

    public static final Path CACHE_PATH = Paths.get("./Cache/");

    /**
     * The port of the server that the client will be connecting to
     */
    public static int server_port = 43594;

    public static boolean useJaggrab = false;

    /**
     * Toggles a security feature called RSA to prevent packet sniffers
     */
    public static final boolean ENABLE_RSA = true;

    /**
     * The url that the users will get redirected to after clicking "New User"
     */
    public static final String REGISTER_ACCOUNT = "";

    /**
     * A string which indicates the Client's name.
     */
    public static final String CLIENT_NAME = "Astral";

    /**
     * Dumps map region images when new regions are loaded.
     */
    public static boolean dumpMapRegions = false;

    /**
     * Displays debug messages on loginscreen and in-game
     */
    public static boolean clientData = false;

    /**
     * Enables the use of music played through the client
     */
    public static boolean enableMusic = true;

    /**
     * Toggles the ability for a player to see roofs in-game
     */
    public static boolean enableRoofs = true;

    /**
     * Used for change worlds button on login screen
     */
    public static boolean worldSwitch = false;

    /**
     * Enables extra frames in-between animations to give the animation a smooth
     * look
     * <p>
     * Use false if there are gfx flickering
     */
    public static boolean enableTweening = false;

    /**
     * Shows the ids of items, objects, and npcs on right click
     */
    public static boolean enableIds = false;

    /**
     * Used to merge all the OS Buddy XP Drops so the counter doesn't get too
     * big if you are training a lot of different skills
     */
    public static boolean xp_merge = true;

    /**
     * Enables fog effects
     * <p>
     * Doesn't render properly after HD textures
     */
    public static boolean enableFog = false;

    /**
     * npcBits can be changed to what your server's bits are set to.
     */
    public static final int npcBits = 16;

    /**
     * Displays health above entities heads
     */
    public static boolean hpAboveHeads = false;

    /**
     * Displays names above entities
     */
    public static boolean namesAboveHeads = false;

    /**
     * Displays OS Buddy orbs on HUD
     */
    public static boolean enableOrbs = true;

    /**
     * Enables/Disables Revision 554 hitmarks
     */
    public static boolean hitmarks554 = false;

    /**
     * Enables/Disables Revision 554 health bar
     */
    public static boolean hpBar554 = false;

    /**
     * Enables the HUD to display 10 X the amount of hitpoints
     */
    public static boolean tenXHp = false;

}
