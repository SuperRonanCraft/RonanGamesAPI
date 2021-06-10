package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesHelp {

    PREFIX("Prefix"),
    HELP("Help"),
    RELOAD("Reload"),
    GEAR("Gear"),
    CATALOG("Catalog"),
    FRIEND("Friend"),
    ;

    String section;

    MessagesHelp(String section) {
        this.section = section;
    }

    private static final String pre = "Help.";

    public String get() {
        return Message.getLang().getString(pre + section);
    }
}
