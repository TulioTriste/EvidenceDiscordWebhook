package rip.veax.evidence.webhook;

import com.google.common.collect.Lists;
import dev.panda.task.TaskManager;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import rip.veax.evidence.Evidence;
import rip.veax.evidence.listeners.PlayerListener;
import rip.veax.evidence.utils.CC;
import rip.veax.evidence.utils.DiscordWebhook;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class EvidenceWebhook {

    private final Evidence plugin = Evidence.get();

    private String getLink(String mode) {
        if (mode.equalsIgnoreCase("guardian")) return this.plugin.getPlayerEvidenceHookGuardian();
        else if (mode.equalsIgnoreCase("teleport")) return this.plugin.getPlayerEvidenceHookTeleport();
        else if (mode.equalsIgnoreCase("ban")) return this.plugin.getPlayerEvidenceHookBan();
        else if (mode.equalsIgnoreCase("mute")) return this.plugin.getPlayerEvidenceHookMute();
        else if (mode.equalsIgnoreCase("warn")) return this.plugin.getPlayerEvidenceHookWarn();
        else if (mode.equalsIgnoreCase("kick")) return this.plugin.getPlayerEvidenceHookKick();
        else if (mode.equalsIgnoreCase("revive")) return this.plugin.getPlayerEvidenceHookRevive();
        else if (mode.equalsIgnoreCase("dtr")) return this.plugin.getPlayerEvidenceHookDTR();
        else if (mode.equalsIgnoreCase("media rank")) return this.plugin.getPlayerEvidenceHookMediaRank();
        else if (mode.equalsIgnoreCase("buycraft")) return this.plugin.getPlayerEvidenceHookBuyCraft();
        else if (mode.equalsIgnoreCase("rollback")) return this.plugin.getPlayerEvidenceHookRollback();
        else if (mode.equalsIgnoreCase("teamspeak")) return this.plugin.getPlayerEvidenceHookTeamSpeak();
        else if (mode.equalsIgnoreCase("unban")) return this.plugin.getPlayerEvidenceHookUnBan();
        return null;
    }

    public void playerExecute(Player player, String userMode, Map<UUID, List<String>> evidences) {
        DiscordWebhook webhook = new DiscordWebhook(getLink(userMode));
        webhook.setAvatarUrl("https://i.imgur.com/jS1OiyA.png");
        webhook.setUsername("Evidence Bot");
        webhook.setTts(false);

        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setAuthor(userMode, null, null);
        embed.setTitle(player.getName());
        embed.setUrl("https://es.namemc.com/profile/" + player.getName());
        embed.setThumbnail("https://www.mc-heads.net/avatar/" + player.getName() + "/64/nohelm");
        embed.setColor(Color.RED);
        List<String> list = evidences.get(player.getUniqueId());
        embed.addField("Evidences", list.toString().replace("[", "").replace("]", "").replace(", ", "\\n"), false);
        embed.setFooter("Veax Evidences Bot", "https://i.imgur.com/PSiPUPB.png");
        webhook.addEmbed(embed);

        int number = PlayerListener.counter.getOrDefault(player.getUniqueId(), 0);
        number++;
        PlayerListener.counter.put(player.getUniqueId(), number);
        new TaskManager(Evidence.get()).runAsync(() -> {
            try {
                webhook.execute();
                player.sendMessage(CC.translate("&aEvidence sent correctly!"));
            } catch (IOException ex) {
                player.sendMessage(CC.translate("&cErroneous Evidence, try again."));
            }
        });
    }

    public void dailyExecute() {
        DiscordWebhook webhook = new DiscordWebhook(this.plugin.getDailyEvidenceHook());
        webhook.setAvatarUrl("https://i.imgur.com/jS1OiyA.png");
        webhook.setUsername("Daily Evidences Bot");
        webhook.setTts(false);

        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setAuthor("Evidences", null, null);
        embed.setColor(Color.RED);
        List<String> list = Lists.newArrayList();
        PlayerListener.counter.keySet().forEach(uuid -> {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            list.add(player.getName() + ": " + PlayerListener.counter.get(uuid));
        });
        embed.addField("Staff Evidences", list.toString().replace("[", "").replace("]", "").replace(", ", "\\n"), false);
        embed.setFooter("Veax Evidences Bot", "https://i.imgur.com/PSiPUPB.png");
        webhook.addEmbed(embed);

        new BukkitRunnable() {
            @SneakyThrows
            @Override
            public void run() {
                webhook.execute();
            }
        }.runTaskAsynchronously(this.plugin);

        PlayerListener.counter.clear();
    }
}
