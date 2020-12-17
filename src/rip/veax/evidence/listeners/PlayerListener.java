package rip.veax.evidence.listeners;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rip.veax.evidence.Evidence;
import rip.veax.evidence.utils.CC;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final Evidence plugin = Evidence.getInstance();
    public static Map<UUID, String> mode = Maps.newConcurrentMap();
    public static Map<UUID, String> intern = Maps.newConcurrentMap();
    public static Map<UUID, List<String>> evidences = Maps.newConcurrentMap();
    public static Map<UUID, Integer> counter = Maps.newConcurrentMap();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR) || event.getSlotType() == null) return;
        if (event.getClickedInventory() == null || event.getInventory() != event.getClickedInventory()) return;

        if (event.getInventory().getTitle().equals(CC.translate("&bEvidence GUI"))) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            switch (event.getRawSlot()) {
                case 9:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Guardian");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Banned..."));
                    break;
                case 10:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Teleport");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Teleported..."));
                    break;
                case 16:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "TeamSpeak");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Moved in TeamSpeak..."));
                    break;
                case 17:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "UnBan");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser UnBanned..."));
                    break;
                case 18:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Ban");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Banned..."));
                    break;
                case 19:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Mute");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Muted..."));
                    break;
                case 20:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Warn");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Warned..."));
                    break;
                case 21:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Kick");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Kicked..."));
                    break;
                case 22:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Revive");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Revived..."));
                    break;
                case 23:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "DTR");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bDTR Refunded..."));
                    break;
                case 24:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Media Rank");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Given Rank..."));
                    break;
                case 25:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Buycraft");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Giftcard..."));
                    break;
                case 26:
                    player.closeInventory();
                    mode.put(player.getUniqueId(), "Rollback");
                    intern.put(player.getUniqueId(), "ign");
                    player.sendMessage(CC.translate("&bPlease follow the instructions below."));
                    player.sendMessage(CC.translate("&8[&9!&8] &bUser Faction Rollback..."));
                    break;
            }
        }
    }

    @EventHandler
    public void onChatWrite(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (mode.containsKey(player.getUniqueId())) {
            String message = event.getMessage();
            if (message.equalsIgnoreCase("cancel")) {
                event.setCancelled(true);
                player.sendMessage(CC.translate("&cYou have canceled this operation."));
                mode.remove(player.getUniqueId());
                intern.remove(player.getUniqueId());
                evidences.remove(player.getUniqueId());
                return;
            }
            String userMode = mode.get(player.getUniqueId());
            String interMode = intern.get(player.getUniqueId());
            if (userMode.equalsIgnoreCase("Guardian")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bLogs..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "logs");
                }
                else if (interMode.equalsIgnoreCase("logs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Logs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "logs");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "history");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    intern.remove(player.getUniqueId(), "reason");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Teleport")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("TeamSpeak")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("UnBan")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "history");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Ban")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "history");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Mute")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "history");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Warn")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "history");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Kick")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bHistory..."));

                    intern.remove(player.getUniqueId(), "proofs");
                    intern.put(player.getUniqueId(), "history");
                }
                else if (interMode.equalsIgnoreCase("history")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("History: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "history");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Revive")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("DTR")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bFaction..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "faction");
                }
                else if (interMode.equalsIgnoreCase("faction")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Faction: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bDTR Added..."));

                    intern.remove(player.getUniqueId(), "faction");
                    intern.put(player.getUniqueId(), "dtr+");
                }
                else if (interMode.equalsIgnoreCase("dtr+")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("DTR+: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "dtr+");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    player.sendMessage(CC.translate("&aEvidence sended correctly!"));
                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Media Rank")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bRank..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "rank");
                }
                else if (interMode.equalsIgnoreCase("rank")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Rank: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "rank");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    intern.remove(player.getUniqueId(), "dtr+");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Buycraft")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    intern.remove(player.getUniqueId(), "dtr+");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
            else if (userMode.equalsIgnoreCase("Rollback")) {
                event.setCancelled(true);
                if (interMode.equalsIgnoreCase("ign")) {
                    List<String> test = Lists.newArrayList();
                    test.add("IGN: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bFaction..."));

                    intern.remove(player.getUniqueId(), "ign");
                    intern.put(player.getUniqueId(), "faction");
                }
                else if (interMode.equalsIgnoreCase("faction")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Faction: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bServer..."));

                    intern.remove(player.getUniqueId(), "faction");
                    intern.put(player.getUniqueId(), "server");
                }
                else if (interMode.equalsIgnoreCase("server")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Server: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bReason..."));

                    intern.remove(player.getUniqueId(), "server");
                    intern.put(player.getUniqueId(), "reason");
                }
                else if (interMode.equalsIgnoreCase("reason")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Reason: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));
                    player.sendMessage("");
                    player.sendMessage(CC.translate("&8[&9!&8] &bProofs..."));

                    intern.remove(player.getUniqueId(), "reason");
                    intern.put(player.getUniqueId(), "proofs");
                }
                else if (interMode.equalsIgnoreCase("proofs")) {
                    List<String> test = evidences.get(player.getUniqueId());
                    test.add("Proofs: " + message);
                    evidences.put(player.getUniqueId(), test);

                    player.sendMessage(CC.translate("&8[&c!&8] &f" + message));

                    intern.remove(player.getUniqueId(), "proofs");

                    this.plugin.getPlayerEvidenceWebhook().playerExecute(player, userMode, evidences);

                    evidences.remove(player.getUniqueId());
                    mode.remove(player.getUniqueId());
                    intern.remove(player.getUniqueId());
                }
            }
        }
    }
}
