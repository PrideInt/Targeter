package me.prride.storage;

import com.google.gson.Gson;
import me.prride.targeter.Targeter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TargetCache {
	private static final File TARGETS_JSON = new File(Targeter.instance.getDataFolder().getAbsolutePath() + File.separator + "targets.json");
	private static final Map<String, Set<String>> TARGETS = new ConcurrentHashMap<>();

	public static void init() {
		File dataFolder = Targeter.instance.getDataFolder();

		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}
		if (!TARGETS_JSON.exists()) {
			try {
				System.out.println("Creating targets.json");
				TARGETS_JSON.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		query();
	}

	public static void query() {
		Gson gson = new Gson();

		try {
			Reader reader = new FileReader(TARGETS_JSON);
			Map<String, Set<String>> map = gson.fromJson(reader, Map.class);
			System.out.println("Reading targets.json");

			TARGETS.putAll(map);
		} catch (Exception e) { }
	}
	public static void update() {
		Gson gson = new Gson();

		try {
			Writer writer = new FileWriter(TARGETS_JSON, false);
			gson.toJson(TARGETS, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) { }
	}
	public static Map<String, Set<String>> targets() {
		return TARGETS;
	}
	public static void removeTargetsFromCache(String player, String entities) {
		TARGETS.computeIfPresent(player, (plyr, set) -> { set.remove(entities); return set; });
	}
	public static void addTargetsToCache(String player, String entities) {
		if (TARGETS.containsKey(player)) {
			TARGETS.get(player).add(entities);
		} else {
			Set<String> list = new HashSet<>();
			list.add(entities);
			TARGETS.put(player, list);
		}
	}
}
