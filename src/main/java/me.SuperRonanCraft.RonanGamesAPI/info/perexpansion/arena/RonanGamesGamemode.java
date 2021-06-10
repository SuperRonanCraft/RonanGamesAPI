package me.SuperRonanCraft.RonanGamesAPI.info.perexpansion.arena;

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
