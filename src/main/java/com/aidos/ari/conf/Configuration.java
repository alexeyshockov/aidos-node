package com.aidos.ari.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

	private static final Logger log = LoggerFactory.getLogger(Configuration.class);

	private static final Map<String, String> conf = new ConcurrentHashMap<>();

	public enum DefaultConfSettings {
		API_PORT, API_HOST, MESH_RECEIVER_PORT, CORS_ENABLED, PEERS, LOCAL, // not used yet
		REMOTEAPI, REMOTEWALLET, DEBUG, EXPERIMENTAL, TESTNET // experimental features.
	}
	
	public static final int CONNECTION_TIMEOUT = 3000; // in ms

	public static final int CONNECTION_TIMEOUT = 3000; // in ms

	static {
		// defaults
		conf.put(DefaultConfSettings.API_PORT.name(), "15555");
		conf.put(DefaultConfSettings.API_HOST.name(), "localhost");
		conf.put(DefaultConfSettings.MESH_RECEIVER_PORT.name(), "15777");
		conf.put(DefaultConfSettings.CORS_ENABLED.name(), "*");
		conf.put(DefaultConfSettings.DEBUG.name(), "false");
		// only remote allow: ping addPeer and getPeerAddresses
		conf.put(DefaultConfSettings.REMOTEAPI.name(), "ping addPeer getPeerAddresses");
		conf.put(DefaultConfSettings.REMOTEWALLET.name(), "attachToMesh interruptAttachingToMesh");
		conf.put(DefaultConfSettings.EXPERIMENTAL.name(), "false");
		conf.put(DefaultConfSettings.LOCAL.name(), "");
		conf.put(DefaultConfSettings.TESTNET.name(), "false");
	}

	public static String allSettings() {
		final StringBuilder settings = new StringBuilder();
		conf.keySet()
				.forEach(t -> settings.append("Set '").append(t).append("'\t -> ").append(conf.get(t)).append("\n"));
		return settings.toString();
	}

	public static void put(final String k, final String v) {
		log.debug("Setting {} with {}", k, v);
		conf.put(k, v);
	}

	public static void put(final DefaultConfSettings d, String v) {
		log.debug("Setting {} with {}", d.name(), v);
		conf.put(d.name(), v);
	}

	public static String string(String k) {
		return conf.get(k);
	}

	public static float floating(String k) {
		return Float.parseFloat(conf.get(k));
	}

	public static double doubling(String k) {
		return Double.parseDouble(conf.get(k));
	}

	public static int integer(String k) {
		return Integer.parseInt(conf.get(k));
	}

	public static boolean booling(String k) {
		return Boolean.parseBoolean(conf.get(k));
	}

	public static String string(final DefaultConfSettings d) {
		return string(d.name());
	}

	public static int integer(final DefaultConfSettings d) {
		return integer(d.name());
	}

	public static boolean booling(final DefaultConfSettings d) {
		return booling(d.name());
	}
}
