package me.SuperRonanCraft.RonanGamesAPI.references.messages;

import me.SuperRonanCraft.RonanGamesAPI.references.messages.lang.*;

public class MessageLanguage {
    private LangArena arena = new LangArena();
    private LangGames game = new LangGames();
    private LangLobby lobby = new LangLobby();
    private LangBasic basic = new LangBasic();
    private LangNots nots = new LangNots();
    private LangUsage usage = new LangUsage();
    private LangHelpCore help = new LangHelpCore();

    public LangArena getArena() {
        return arena;
    }

    public LangGames getGame() {
        return game;
    }

    public LangLobby getLobby() {
        return lobby;
    }

    public LangBasic getBasic() {return basic;}

    public LangNots getNots() {
        return nots;
    }

    public LangUsage getUsage() {return usage;}

    public LangHelpCore getHelp() {return help;}
}
