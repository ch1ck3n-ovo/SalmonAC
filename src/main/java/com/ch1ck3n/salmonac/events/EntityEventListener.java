package com.ch1ck3n.salmonac.events;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityEventListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if ( e.getEntity() instanceof Player ) {
            SalmonPlayer salmonPlayer = getCustomPlayer( ((Player)e.getEntity()) );
            if (salmonPlayer == null) return;

            salmonPlayer.damageTick = 0;

            if( e.getCause() == EntityDamageEvent.DamageCause.FALL ){
                salmonPlayer.lastFallDamage = e.getDamage();
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if ( e.getEntity() instanceof Player ) {
            SalmonPlayer salmonPlayer = getCustomPlayer( ((Player)e.getEntity()) );
            if (salmonPlayer == null) return;

            salmonPlayer.damageTick = 0;
        }

        if ( e.getDamager() instanceof Player ) {
            SalmonPlayer salmonPlayer = getCustomPlayer( ((Player)e.getDamager()) );
            if (salmonPlayer == null) return;

            salmonPlayer.attackTick = 0;
            salmonPlayer.entityAttacked = e.getEntity();
        }
    }

    public SalmonPlayer getCustomPlayer(Player player){
        return SalmonAC.getInstance().getPlayerManager().getPlayer( player );
    }
}
