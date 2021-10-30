package rip.veax.evidence.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import rip.veax.evidence.utils.CC;
import rip.veax.evidence.utils.ItemCreator;

public class GUIManager {

    public void getEvidenceInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, CC.translate("&bEvidence GUI"));
        ItemStack nothing = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(3).setName("&r").get();
        ItemStack guardian = new ItemCreator(Material.INK_SACK).setDurability(14).setName("&bGuardian").get();
        ItemStack tp = new ItemCreator(Material.INK_SACK).setDurability(2).setName("&bTeleport").get();
        ItemStack ban = new ItemCreator(Material.INK_SACK).setDurability(12).setName("&bBan").get();
        ItemStack mute = new ItemCreator(Material.INK_SACK).setDurability(11).setName("&bMute").get();
        ItemStack warn = new ItemCreator(Material.INK_SACK).setDurability(9).setName("&bWarn").get();
        ItemStack kick = new ItemCreator(Material.INK_SACK).setDurability(13).setName("&bKick").get();
        ItemStack revive = new ItemCreator(Material.INK_SACK).setDurability(1).setName("&bRevive").get();
        ItemStack dtr = new ItemCreator(Material.INK_SACK).setDurability(6).setName("&bDTR").get();
        ItemStack mediaRank = new ItemCreator(Material.QUARTZ).setName("&bMedia Rank").get();
        ItemStack buyCraft = new ItemCreator(Material.NETHER_STAR).setName("&bBuyCraft").get();
        ItemStack rollBack = new ItemCreator(Material.INK_SACK).setDurability(10).setName("&bRollBack").get();
        ItemStack teamSpeak = new ItemCreator(Material.INK_SACK).setDurability(4).setName("&bTeamSpeak").get();
        ItemStack unBan = new ItemCreator(Material.INK_SACK).setDurability(8).setName("&bUnBan").get();

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, nothing);
        }

        inv.setItem(9, guardian);
        inv.setItem(10, tp);
        inv.setItem(16, teamSpeak);
        inv.setItem(17, unBan);
        inv.setItem(18, ban);
        inv.setItem(19, mute);
        inv.setItem(20, warn);
        inv.setItem(21, kick);
        inv.setItem(22, revive);
        inv.setItem(23, dtr);
        inv.setItem(24, mediaRank);
        inv.setItem(25, buyCraft);
        inv.setItem(26, rollBack);

        player.sendMessage(CC.translate("&aOpening Evidence Inventory!"));
        player.openInventory(inv);
    }
}
