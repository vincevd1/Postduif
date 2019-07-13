package nl.geslotenkingdom.postduif.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import nl.geslotenkingdom.postduif.main.Main;
import nl.geslotenkingdom.postduif.ui.GUI;
import nl.geslotenkingdom.postduif.utils.Utils;

public class PostDuif implements CommandExecutor {

    private HashMap<Player, Integer> returnTime = new HashMap<Player, Integer>();
    private HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
    public static HashMap<Player, Player> postduif = new HashMap<Player, Player>();
    public static HashMap<Player, ItemStack> message = new HashMap<Player, ItemStack>();

    private Main plugin;

    public PostDuif(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("postduif").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cJe kan dit command niet uitvoeren!"));
            return true;
        }

        Player p = (Player) sender;
//Dit werkt meestal alleen bij strings, niet bij volledige methodes.
        //Het beste kan je ermee spelen, en als die errors gewoon
        //De .jar ervan erindoen :P

        //TODO: Bug fixen dat als je 2 boekjes stuurt (ontvanger heeft het niet gelezen) dat die dan boekje nog laat pakken
        // CTRL + ALT + L (Instellingen veranderen) (Moet slapen)
        if (p.hasPermission("postduif.use")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("open")) {
                    if (args.length > 1) {
                        Player from = plugin.getServer().getPlayer(args[1]);
                        if (from != null) {
                            if (postduif.containsKey(from) && p.equals(postduif.get(from))) {
                                p.openInventory(GUI.GUI(from, p));
                                postduif.put(from, null);
                            } else
                                p.sendMessage(Utils.chat("&cJe hebt geen bericht ontvangen van &4" + args[1] + "&c!"));
                        } else
                            p.sendMessage(Utils.chat("&cEen speler genaamd &4" + args[1] + " &cbestaat niet of is niet online!"));
                    } else
                        p.sendMessage(Utils.chat("&cGeef alstjeblieft een speler op waarvan je het bericht wilt lezen als je die hebt ontvangen"));
                } else {
                    //Je ziet dan info van bepaalde variablelen
                    if (cooldown.containsKey(p)) {
                        long secondsleft = ((cooldown.get(p) / 1000) + returnTime.get(p)) - (System.currentTimeMillis() / 1000);
                        if (secondsleft >= 0) { //Is meer dan of is 0
                            p.sendMessage(Utils.chat("&cJe postduif is nog niet teruggekeerd wacht nog &4" + secondsleft + " &cseconden"));
                        } else {
                            if (p.getInventory().getItemInHand().getType().equals(Material.WRITTEN_BOOK) || p.getInventory().getItemInHand().getType().equals(Material.BOOK_AND_QUILL)) {
                                Player target = plugin.getServer().getPlayer(args[0]);
                                if (target != null) {
                                    if (!p.equals(target)) {
                                        // Pytagoras om de diagonale lengte uit te rekenen tussen de twee personen en
                                        // dat gedeelt door 10 om de tijd uit te rekenen
                                        int returnTimeNumber = (int) Math.round(Math.sqrt(Math.pow(target.getLocation().getX() - p.getLocation().getX(), 2) + Math.pow(target.getLocation().getZ() - p.getLocation().getZ(), 2)) / 50);
                                        if (returnTimeNumber < 0) returnTimeNumber *= -1;
                                        returnTime.put(p, returnTimeNumber * 2);
                                        cooldown.put(p, System.currentTimeMillis());
                                        p.sendMessage(Utils.chat("&aJe bericht is succesvol verzonden!"));
                                        message.put(p, p.getInventory().getItemInHand());
                                        p.getInventory().setItemInHand(null);
                                        sendArrival(p, target, returnTimeNumber);
                                    } else p.sendMessage(Utils.chat("&cJe kan geen postduif naar jezelf sturen!"));
                                } else
                                    p.sendMessage(Utils.chat("&cEen speler genaamd '" + args[0] + "' bestaat niet of is niet online!"));
                            } else
                                p.sendMessage(Utils.chat("&cJe hebt een gesigned book of een book and quill met het bericht in je hand nodig om het bericht te kunnen versturen"));
                        }

                    } else {
                        if (p.getInventory().getItemInHand().getType().equals(Material.WRITTEN_BOOK) || p.getInventory().getItemInHand().getType().equals(Material.BOOK_AND_QUILL)) {
                            Player target = plugin.getServer().getPlayer(args[0]);
                            if (target != null) {
                                if (!p.equals(target)) {
                                    // Pytagoras om de diagonale lengte uit te rekenen tussen de twee personen en
                                    // dat gedeelt door 10 om de tijd uit te rekenen
                                    int returnTimeNumber = (int) Math.round(Math.sqrt(Math.pow(target.getLocation().getX() - p.getLocation().getX(), 2) + Math.pow(target.getLocation().getZ() - p.getLocation().getZ(), 2)) / 50);
                                    if (returnTimeNumber < 0) returnTimeNumber *= -1;
                                    returnTime.put(p, returnTimeNumber * 2);
                                    cooldown.put(p, System.currentTimeMillis());
                                    p.sendMessage(Utils.chat("&aJe bericht is succesvol verzonden!"));
                                    message.put(p, p.getInventory().getItemInHand());
                                    p.getInventory().setItemInHand(null);
                                    sendArrival(p, target, returnTimeNumber);
                                } else p.sendMessage(Utils.chat("&cJe kan geen postduif naar jezelf sturen!"));
                            } else
                                p.sendMessage(Utils.chat("&cEen speler genaamd '" + args[0] + "' bestaat niet of is niet online!"));
                        } else
                            p.sendMessage(Utils.chat("&cJe hebt een gesigned book of een book and quill met het bericht in je hand nodig om het bericht te kunnen versturen"));
                    }
                }
            } else
                p.sendMessage(Utils.chat("&cGeef alstjeblieft een speler op waar je het bericht naar wilt sturen of open een bericht met '/postduif open <naam>' als je een bericht hebt ontvangen"));
        } else p.sendMessage(Utils.chat("&cJe kan dit command niet uitvoeren!"));

        return false;
    }

    public void sendArrival(final Player from, final Player to, int timeInSeconds) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                postduif.put(from, to);
                TextComponent tc = new TextComponent();
                tc.setText(Utils.chat("&aJe hebt een bericht ontvangen van &2" + from.getName() + "&a! Klik op dit &abericht om het te lezen"));
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat("&aKlik op dit bericht om het bericht van &2" + from.getName() + " &ate lezen")).create()));
                tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/postduif open " + from.getName()));
                to.spigot().sendMessage(tc);
                to.sendActionBar(Utils.chat("&aJe hebt een bericht ontvangen van " + from.getName() + "!"));
            }
        }, timeInSeconds * 20);
    }

}
