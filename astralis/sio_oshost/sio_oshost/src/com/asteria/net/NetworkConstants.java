package com.asteria.net;

import io.netty.util.AttributeKey;
import io.netty.util.ResourceLeakDetector.Level;

import java.math.BigInteger;

import com.asteria.net.message.InputMessageListener;
import com.google.common.collect.ImmutableList;

/**
 * The class that contains a collection of constants related to the network.
 * This class serves no other purpose than to hold constants.
 *
 * @author lare96 <http://github.org/lare96>
 */
public final class NetworkConstants {

    /**
     * The port that this server will bind to.
     */
    public static final int PORT = 43594;

    /**
     * The input timeout value that determines how long a session can go without
     * reading data from the client in {@code SECONDS}.
     */
    public static final int INPUT_TIMEOUT = 5;

    /**
     * The size of a variable sized packet.
     */
    public static final int VAR_SIZE = -1;

    /**
     * The size of a variable sized short packet.
     */
    public static final int VAR_SIZE_SHORT = -2;

    /**
     * The resource leak detection level when not running the server in debug
     * mode.
     */
    public static final Level RESOURCE_DETECTION = Level.DISABLED;

    /**
     * An array of the message opcodes mapped to their respective listeners.
     */
    public static final InputMessageListener[] MESSAGES = new InputMessageListener[257];

    /**
     * An array of message opcodes mapped to their respective sizes.
     */
    public static final int MESSAGE_SIZES[] = new int[257];

    /**
     * The {@link AttributeKey} value that is used to retrieve the session
     * instance from the attribute map of a {@link Channel}.
     */
    public static final AttributeKey<PlayerIO> SESSION_KEY = AttributeKey.valueOf("session.KEY");

    /**
     * The throttle interval for incoming connections accepted by the
     * {@link ConnectionHandler}.
     */
    public static final long CONNECTION_INTERVAL = 1000;

    /**
     * The maximum amount of connections that can be active at a time, or in
     * other words how many clients can be logged in at once per connection.
     */
    public static final int CONNECTION_AMOUNT = 1;

    /**
     * Determines if RSA should be decoded in the login block.
     */
    public static final boolean DECODE_RSA = true;

	/**
	 * The private RSA modulus value.
	 */
	public static final BigInteger RSA_MODULUS = new BigInteger(
			"158524451491299768481335406949632722930001467869157605890403538922079064140031175070843389751771070828946840977662555424706722104406478161144373150173642644879920983864580051507542828646901725343022346327871558268510881848702090892750832077808168455305660457441192814181290396639012661208328185464354643376401");

	/**
	 * The private RSA exponent value.
	 */
	public static final BigInteger RSA_EXPONENT = new BigInteger(
			"18068841305522212956888101223946113497522177777173311503445602266626952852984312339277051458652820530268900042771858477235137618748438162621854333152221041073570151261267739534657013265105241051273559587745933089288822698802581235318918428986172351596645296040858190395660695176080324899161432348903221384193");

    /**
     * The maximum amount of messages that can be decoded in one sequence.
     */
    public static final int DECODE_LIMIT = 15;

    /**
     * The list of exceptions that are ignored and discarded by the
     * {@link NetworkChannelHandler}.
     */
    public static final ImmutableList<String> IGNORED_EXCEPTIONS = ImmutableList.of(
        "An existing connection was forcibly closed by the remote host",
        "An established connection was aborted by the software in your host machine");

    /**
     * The default constructor.
     *
     * @throws UnsupportedOperationException
     *             if this class is instantiated.
     */
    private NetworkConstants() {
        throw new UnsupportedOperationException("This class cannot be instantiated!");
    }
}
