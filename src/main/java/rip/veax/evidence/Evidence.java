package rip.veax.evidence;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import rip.veax.evidence.commands.EvidenceCommand;
import rip.veax.evidence.listeners.PlayerListener;
import rip.veax.evidence.manager.GUIManager;
import rip.veax.evidence.utils.DataFile;
import rip.veax.evidence.webhook.EvidenceWebhook;

import java.util.UUID;

@Getter
public class Evidence extends JavaPlugin {

    private GUIManager guiManager;
    private EvidenceWebhook playerEvidenceWebhook;
    private final String playerEvidenceHookGuardian = getConfig().getString("player-evidence-webhook-guardian");
    private final String playerEvidenceHookTeleport = getConfig().getString("player-evidence-webhook-teleport");
    private final String playerEvidenceHookBan = getConfig().getString("player-evidence-webhook-ban");
    private final String playerEvidenceHookMute = getConfig().getString("player-evidence-webhook-mute");
    private final String playerEvidenceHookWarn = getConfig().getString("player-evidence-webhook-warn");
    private final String playerEvidenceHookKick = getConfig().getString("player-evidence-webhook-kick");
    private final String playerEvidenceHookRevive = getConfig().getString("player-evidence-webhook-revive");
    private final String playerEvidenceHookDTR = getConfig().getString("player-evidence-webhook-dtr");
    private final String playerEvidenceHookMediaRank = getConfig().getString("player-evidence-webhook-media-rank");
    private final String playerEvidenceHookBuyCraft = getConfig().getString("player-evidence-webhook-buycraft");
    private final String playerEvidenceHookRollback = getConfig().getString("player-evidence-webhook-rollback");
    private final String playerEvidenceHookTeamSpeak = getConfig().getString("player-evidence-webhook-teamspeak");
    private final String playerEvidenceHookUnBan = getConfig().getString("player-evidence-webhook-unban");
    private final String dailyEvidenceHook = getConfig().getString("daily-evidence-webhook");

    @Override
    public void onEnable() {
        registerManagers();
        DataFile.getConfig().getKeys(false).forEach(uuid -> {
            PlayerListener.counter.put(UUID.fromString(uuid), DataFile.getConfig().getInt(uuid));
            DataFile.getConfig().set(uuid, null);
        });
        DataFile.getConfig().saveAll();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        PlayerListener.counter.keySet().forEach(uuid -> {
            DataFile.getConfig().set(uuid.toString(), PlayerListener.counter.get(uuid));
            DataFile.getConfig().saveAll();
        });
    }

    private void registerManagers() {
        this.guiManager = new GUIManager();
        this.playerEvidenceWebhook = new EvidenceWebhook();
    }

    private void registerCommands() {
        new EvidenceCommand();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public static Evidence get() {
        return getPlugin(Evidence.class);
    }
}
