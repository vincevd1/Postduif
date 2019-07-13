package nl.geslotenkingdom.postduif.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import nl.geslotenkingdom.postduif.main.Main;
import nl.geslotenkingdom.postduif.ui.GUI;

public class InventoryClickListener implements Listener {

	private Main plugin;

	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getInventory().getTitle();
		if (title.equals(GUI.inventory_name)) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.WRITTEN_BOOK) || e.getCursor().getType().equals(Material.WRITTEN_BOOK))
				return;
			else
				e.setCancelled(true);
		}

	}

}
