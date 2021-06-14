package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesUsage implements MessagesMain {
    ARENA_CREATE("Arena.Create"),
    ARENA_SET("Arena.Set"),
    ARENA_ENABLE("Arena.Enable"),
    ARENA_DISABLE("Arena.Disable"),
    ;

    String section;

    MessagesUsage(String section) {
        this.section = section;
    }

    @Override
    public String getPrefix() {
        return "Usage.";
    }

    @Override
    public String getSection() {
        return section;
    }
}
