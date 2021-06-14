package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesHelp implements MessagesMain {

    CORE_HEADER("Core.Header"),
    CORE_FOOTER("Core.Footer"),
    CORE_HELP("Core.Help"),
    CORE_LIST("Core.List"),
    CORE_EXPANSION("Core.Expansion"),
    //ARENA
    ARENA_HEADER("Arena.Header"),
    ARENA_FOOTER("Arena.Footer"),
    ARENA_HELP("Arena.Help"),
    ARENA_CREATE("Arena.Create"),
    ARENA_ENABLE("Arena.Enable"),
    ARENA_DISABLE("Arena.Disable"),
    ARENA_DELETE("Arena.Delete"),
    ARENA_JOIN("Arena.Joine"),
    ARENA_LIST("Arena.List"),
    ARENA_SAVE("Arena.Save"),
    ARENA_WAND("Arena.Wand"),
    ARENA_SET("Arena.Set.Command"),
    ARENA_SET_HELP("Arena.Set.Help"),
    ARENA_SET_PROTECTION("Arena.Set.Protection"),
    ARENA_SET_GAMEMODE("Arena.Set.Gamemode"),
    ARENA_SET_TEAMS("Arena.Set.Teams"),
    //EXPANSION
    EXPANSION_HEADER("Expansion.Header"),
    EXPANSION_FOOTER("Expansion.Footer"),
    EXPANSION_HELP("Expansion.Help"),
    EXPANSION_VERSION("Expansion.Version"),
    EXPANSION_AUTHOR("Expansion.Author"),
    ;

    String section;

    MessagesHelp(String section) {
        this.section = section;
    }

    @Override
    public String getPrefix() {
        return "Help.";
    }

    @Override
    public String getSection() {
        return section;
    }
}
