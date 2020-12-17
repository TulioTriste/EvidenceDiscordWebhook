package rip.veax.evidence.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class CC {

    public static String translate(String n) {
        return ChatColor.translateAlternateColorCodes('&', n);
    }

    public static List<String> translate(List<String> n) {
        for (int i = 0; i < n.size(); ++i) {
            n.set(i, translate(n.get(i)));
        }
        return n;
    }
}
