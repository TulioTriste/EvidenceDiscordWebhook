package rip.veax.evidence.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.veax.evidence.Evidence;
import rip.veax.evidence.utils.CC;

import java.util.Collections;

public class EvidenceCommand extends Command {

    private final Evidence plugin = Evidence.getInstance();

    public EvidenceCommand() {
        super("evidence");
        this.setAliases(Collections.singletonList("evidences"));
        this.setPermission("veax.command.evidence");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&cThis command is only executable for Players."));
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(CC.translate("&cYou do not have permissions for use this command!"));
            return true;
        }
        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("update")) {
                this.plugin.getPlayerEvidenceWebhook().dailyExecute();
                player.sendMessage(CC.translate("&cDaily Evidences correctly sent to the Discord!."));
            }
            return true;
        }
        this.plugin.getGuiManager().getEvidenceInventory(player);
        return false;
    }
}
