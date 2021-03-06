package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesCore implements MessagesMain {
    RELOAD("Reload"),
    NOPERMISSION("NoPermission"),
    INVALID("Invalid"),
    ;

    String section;

    MessagesCore(String section) {
        this.section = section;
    }

    @Override
    public String getPrefix() {
        return "Messages.";
    }

    @Override
    public String getSection() {
        return section;
    }
}
