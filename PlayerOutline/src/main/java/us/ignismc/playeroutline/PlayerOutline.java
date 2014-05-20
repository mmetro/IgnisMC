package us.ignismc.playeroutline;

import java.util.logging.Level;

import nl.lolmewn.stats.api.Stat;
import nl.lolmewn.stats.api.StatsAPI;
import nl.lolmewn.stats.player.StatData;
import nl.lolmewn.stats.player.StatsPlayer;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerOutline extends JavaPlugin {
    private StatsAPI statsAPI;

    public PlayerOutline(){
        
    }
    
    private boolean setupStatsAPI(){
        RegisteredServiceProvider<StatsAPI> stats = getServer().getServicesManager().getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
        if (stats!= null) {
            statsAPI = stats.getProvider();
        }
        return (statsAPI != null);
    }
    
	@Override
	public void onEnable(){
	    if(!setupStatsAPI())
	    {
	        getLogger().log(Level.SEVERE, "Could not get stats API");
	    }

	}
	
	@Override
	public void onDisable(){

	}
	
	private void sendProfileToSender(CommandSender sender, String profileName){
	    sender.sendMessage("===============" + profileName + "'s Profile===============");
	    //TODO show stats
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    Player player = (Player)sender;
	    if (cmd.getName().equalsIgnoreCase("profile")) { 
	        if(args.length > 0){
	            
                if(args[0].equalsIgnoreCase("show")){
                    
                    if(args.length == 1){
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("Please specify a player.");
                        } else {
                            sendProfileToSender(sender, player.getDisplayName());
                        }
                        return true;
                        
                    } else if(args[1].equalsIgnoreCase("titles")){
                        if(args.length == 2){
                            //TODO show titles for sender	
                            return true;
                            
                        } else if (args.length == 3){
                            //TODO  Show titles for player in third argument
                            return true;
                        }
                        
                    } else if (args.length == 2) {
                        sendProfileToSender(sender, args[1]);
                        return true;
                    }      
                    
                    
                } else if(args[0].equalsIgnoreCase("bio")){
                    if (sender instanceof Player) {
                        String bioText = StringUtils.join(args, ' ', 2, args.length);
                        //TODO set sender's bio	            
                        return true;
                    } else {
                        sender.sendMessage("Only players can set their bio.");
                    }
                }
                //TODO let admin change any player's BIO
                
            } 
	    }
	    return false; 
	}
	
 
//            Some stats stuff I was messing with     
	
//            Player player = (Player)sender;
//            StatsPlayer statsPlayer = statsAPI.getPlayer(player);
//            Stat stat = statsAPI.getStatExact("Playtime"); //faster than api.getStat()
//            StatData data = statsPlayer.getGlobalStatData(stat);
//            double playtime = data.getValue(new Object[]{}); //gets the playtime without using variables
//            getLogger().info(String.format("Playtime = %f", playtime));

}
