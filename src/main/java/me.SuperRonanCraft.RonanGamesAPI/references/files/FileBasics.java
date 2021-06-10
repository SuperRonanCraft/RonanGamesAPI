package me.SuperRonanCraft.RonanGamesAPI.references.files;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileBasics {

    List<FILETYPE> types = new ArrayList<>();

    public void load() {
        types.clear();
        for (FILETYPE type : FILETYPE.values()) {
            type.load();
            types.add(type);
        }
    }

    public enum FILETYPE {
        CONFIG("config"),
        BOARD("boards");

        private String fileName;
        private YamlConfiguration config = new YamlConfiguration();
        private File file = null;

        FILETYPE(String str) {
            this.fileName = str + ".yml";
        }

        //PUBLIC
        public String getString(String path) {
            if (config.isString(path))
                return config.getString(path);
            return "SOMETHING WENT WRONG";
        }

        public boolean getBoolean(String path) {
            return config.getBoolean(path);
        }

        public int getInt(String path) {
            return config.getInt(path);
        }

        @SuppressWarnings("all")
        public List<String> getStringList(String path) {
            if (config.isList(path))
                return config.getStringList(path);
            return Arrays.asList("SOMETHING WENT WRONG!");
        }

        public ConfigurationSection getConfigurationSection(String path) {
            return config.getConfigurationSection(path);
        }

        public boolean isString(String path) {
            return config.isString(path);
        }

        public boolean isList(String path) {
            return config.isList(path);
        }

        public List<Map<?, ?>> getMapList(String path) {
            return config.getMapList(path);
        }

        public YamlConfiguration getFile() {
            return config;
        }

        //PROCESSING
        private void load() {
            RonanGamesCorePlugin pl = RonanGamesCorePlugin.getInstance();
            if (file == null) {
                file = new File(pl.getDataFolder(), fileName);
                if (!file.exists()) {
                    pl.saveResource(fileName, false);
                    config = YamlConfiguration.loadConfiguration(file);
                } else {
                    try {
                        config.load(file);
                        final InputStream defConfigStream = RonanGamesCorePlugin.getInstance().getResource(fileName);
                        if (defConfigStream != null) {
                            config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream)));
                            config.options().copyDefaults(true);
                        }
                        config.save(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (!file.exists())
                    pl.saveResource(fileName, false);
                config = YamlConfiguration.loadConfiguration(file);
            }
        }
    }
}
