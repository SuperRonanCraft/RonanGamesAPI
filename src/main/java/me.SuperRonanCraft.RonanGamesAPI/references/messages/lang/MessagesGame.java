package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesGame implements MessagesMain {
    GAME_INFO("Info"),
    GAME_LIST("List.Games"),
    GAME_LIST_NONE("List.None"),
    ;

    String section;

    MessagesGame(String section) {
        this.section = section;
    }

    @Override
    public String getPrefix() {
        return "Game.";
    }

    @Override
    public String getSection() {
        return section;
    }
}
