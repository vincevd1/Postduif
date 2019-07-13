package nl.geslotenkingdom.postduif.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import nl.geslotenkingdom.postduif.commands.PostDuif;
import nl.geslotenkingdom.postduif.listeners.InventoryClickListener;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		new PostDuif(this);
		new InventoryClickListener(this);
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	
}
