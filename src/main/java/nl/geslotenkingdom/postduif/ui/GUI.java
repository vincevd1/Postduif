package nl.geslotenkingdom.postduif.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import nl.geslotenkingdom.postduif.commands.PostDuif;
import nl.geslotenkingdom.postduif.utils.Utils;

public class GUI {

		public static String inventory_name;
		public static int inv_rows = 5 * 9;
		public static Inventory inv = Bukkit.createInventory(null, inv_rows);

		public static Inventory GUI(Player from, Player to) {
			inventory_name = Utils.chat("Bericht van " + from.getName());
			Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);
			
			inv.setItem(22, PostDuif.message.get(from));
			Utils.createItem(inv, 288, 1, 1, "");
			Utils.createItemByte(inv, 35, 8, 1, 2, "");
			Utils.createItemByte(inv, 35, 8, 1, 3, "");
			Utils.createItemByte(inv, 35, 8, 1, 4, "");
			Utils.createItemByte(inv, 35, 7, 1, 5, "");
			Utils.createItem(inv, 288, 1, 6, "");
			Utils.createItemByte(inv, 35, 7, 1, 7, "");
			Utils.createItemByte(inv, 35, 7, 1, 8, "");
			Utils.createItem(inv, 288, 1, 9, "");
			Utils.createItem(inv, 288, 1, 10, "");
			Utils.createItemByte(inv, 35, 7, 1, 11, "");
			Utils.createItemByte(inv, 35, 8, 1, 12, "");
			Utils.createItemByte(inv, 35, 7, 1, 13, "");
			Utils.createItemByte(inv, 35, 8, 1, 15, "");
			Utils.createItem(inv, 288, 1, 16, "");
			Utils.createItem(inv, 288, 1, 17, "");
			Utils.createItemByte(inv, 35, 7, 1, 18, "");
			Utils.createItemByte(inv, 35, 7, 1, 19, "");
			Utils.createItemByte(inv, 35, 7, 1, 20, "");
			Utils.createItem(inv, 288, 1, 21, "");
			Utils.createItemByte(inv, 35, 8, 1, 26, "");
			Utils.createItemByte(inv, 35, 8, 1, 27, "");
			Utils.createItemByte(inv, 35, 8, 1, 28, "");
			Utils.createItemByte(inv, 35, 7, 1, 29, "");
			Utils.createItemByte(inv, 35, 8, 1, 30, "");
			Utils.createItemByte(inv, 35, 8, 1, 31, "");
			Utils.createItemByte(inv, 35, 7, 1, 33, "");
			Utils.createItem(inv, 288, 1, 34, "");
			Utils.createItemByte(inv, 35, 8, 1, 35, "");
			Utils.createItem(inv, 288, 1, 36, "");
			Utils.createItemByte(inv, 35, 8, 1, 37, "");
			Utils.createItemByte(inv, 35, 8, 1, 38, "");
			Utils.createItem(inv, 288, 1, 39, "");
			Utils.createItemByte(inv, 35, 8, 1, 40, "");
			Utils.createItem(inv, 288, 1, 41, "");
			Utils.createItemByte(inv, 35, 7, 1, 42, "");
			Utils.createItem(inv, 288, 1, 43, "");
			Utils.createItemByte(inv, 35, 7, 1, 44, "");
			Utils.createItemByte(inv, 35, 7, 1, 45, "");

			
			toReturn.setContents(inv.getContents());
			return toReturn;
		}
}
