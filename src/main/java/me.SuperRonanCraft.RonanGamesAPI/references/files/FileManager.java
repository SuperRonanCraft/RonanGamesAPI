package me.SuperRonanCraft.RonanGamesAPI.references.files;

public class FileManager {
    private FileLang lang = new FileLang();
    private FileBasics basics = new FileBasics();
    //private FileDatabase localDatabase = new FileDatabase();

    public FileLang getLang() {
        return lang;
    }

    public FileBasics.FILETYPE getType(FileBasics.FILETYPE type) {
        return basics.types.get(basics.types.indexOf(type));
    }

    //public FileDatabase getLocalDatabase() {return localDatabase;}

    public void loadAll() {
        lang.load();
        basics.load();
        //localDatabase.load();
    }
}
