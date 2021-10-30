package rip.veax.evidence.commands;

import dev.panda.command.BaseCommand;
import dev.panda.command.Command;
import dev.panda.command.CommandArgs;
import org.bukkit.entity.Player;
import rip.veax.evidence.Evidence;
import rip.veax.evidence.utils.CC;

public class EvidenceCommand extends BaseCommand {

    private final Evidence plugin = Evidence.get();

    @Command(name = "evidence", aliases = {"evidences"}, permission = "evidences")
    @Override
    public void onCommand(CommandArgs commandArgs) {
        Player player = commandArgs.getPlayer();
        String[] args = commandArgs.getArgs();

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("update")) {
                this.plugin.getPlayerEvidenceWebhook().dailyExecute();
                player.sendMessage(CC.translate("&cDaily Evidences correctly sent to the Discord!."));
            }
            return;
        }
        this.plugin.getGuiManager().getEvidenceInventory(player);
    }
}
