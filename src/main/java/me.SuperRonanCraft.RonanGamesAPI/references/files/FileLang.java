package me.SuperRonanCraft.RonanGamesAPI.references.files;

import me.SuperRonanCraft.RonanGamesAPI.RonanGamesCorePlugin;
import org.apache.commons.io.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileLang {
    private YamlConfiguration config = new YamlConfiguration();
    private File configFile;
    private String file = "lang" + File.separator + "en.yml";
    private String[] defaultLangs = {"en.yml"};

    public void load() {
        RonanGamesCorePlugin pl = RonanGamesCorePlugin.getInstance();
        configFile = new File(pl.getDataFolder(), "lang" + File.separator + pl.getConfig().getString("Language-File"));
        if (!configFile.exists()) {
            configFile = new File(pl.getDataFolder(), file);
            if (!configFile.exists())
                pl.saveResource(file, false);
        }
        loadFile();
        generateDefaults();
    }

    public boolean changeFile(String file) {
        File f = new File(RonanGamesCorePlugin.getInstance().getDataFolder(), "lang" + File.separator + file);
        if (!f.exists())
            return false;
        configFile = f;
        loadFile();
        return true;
    }

    public List<String> getLangList() {
        List<String> list = new ArrayList<>(Arrays.asList(defaultLangs));
        if (!list.contains(configFile.getName()))
            list.add(configFile.getName());
        return list;
    }

    public String getString(String path) {
        if (config.isString(path))
            return config.getString(path);
        return "SOMETHING WENT WRONG";
    }

    public List<String> getStringList(String path) {
        if (config.isList(path))
            return config.getStringList(path);
        return List.of("&7The path &e" + path + " &7was not configured correctly!");
    }

    public List<String> getList(String path) {
        List<String> list = new ArrayList<>();
        if (config.isList(path)) list.addAll(getStringList(path));
        else if (config.isString(path)) list.add(getString(path));
        else return List.of("&7The path &e" + path + " &7was not configured correctly!");
        return list;
    }


    private void loadFile() {
        try {
            config.load(configFile);
            setDefaults();
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDefaults() {
        //Generate allLangs
        for (String yaml : defaultLangs) {
            if (yaml.equals(defaultLangs[0]) && configFile.getName().equals(defaultLangs[0]))
                continue;
            File f = new File(RonanGamesCorePlugin.getInstance().getDataFolder(), "lang" + File.separator + yaml);
            if (!f.exists())
                RonanGamesCorePlugin.getInstance().saveResource("lang" + File.separator + f.getName(), false);
        }
    }

    @SuppressWarnings("all")
    private void setDefaults() {
        InputStream defConfigStream =
                RonanGamesCorePlugin.getInstance().getResource("lang/" + RonanGamesCorePlugin.getInstance().getConfig().getString("Settings.Language-File"));
        if (defConfigStream == null)
            defConfigStream = RonanGamesCorePlugin.getInstance().getResource("lang/en.yml");
        if (defConfigStream == null)
            return;
        config.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream)));
        config.options().copyDefaults(true);
    }
}
