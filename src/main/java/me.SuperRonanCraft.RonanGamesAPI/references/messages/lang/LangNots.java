package me.SuperRonanCraft.RonanGamesAPI.references.messages.lang;

import org.bukkit.command.CommandSender;

public class LangNots extends Language {

    //NOTIFICATIONS
    public String getNotificationNew(CommandSender sendi, String ticketID, String player, String importance) {
        return colorPre(sendi,
                getLang().getString(preM + "Notifications.New").replaceAll(this.tId, ticketID).replaceAll(this.tPlayer, player).replaceAll("%ticket_importance%", importance));
    }

    public String getNotificationClosedNormal(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Closed.Normal"));
    }

    public String getNotificationClosedWithMsg(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Closed.Message"));
    }

    public String getNotificationReply(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Reply"));
    }

    public String getNotificationOpened(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Opened"));
    }

    public String getNotificationFlagged(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Flagged"));
    }

    public String getNotificationJoinOpened(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Join.Opened"));
    }

    public String getNotificationJoinPlayer(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Join.Player"));
    }

    public String getNotificationJoinFlagged(CommandSender sendi) {
        return colorPre(sendi, getLang().getString(preM + "Notifications.Join.Flagged"));
    }
}
