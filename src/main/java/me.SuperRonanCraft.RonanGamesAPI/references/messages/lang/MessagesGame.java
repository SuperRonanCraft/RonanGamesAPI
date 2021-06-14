package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

public enum MessagesGame implements MessagesMain {
    GAME_VERSION("Version"),
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
