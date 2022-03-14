package me.SuperRonanCraft.RonanGamesAPI.perexpansion.arena;

public enum RonanGamesGamemode {
    FFA("FreeForAll"),
    TDM("TeamDeathmatch");

    private String desc;

    RonanGamesGamemode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
