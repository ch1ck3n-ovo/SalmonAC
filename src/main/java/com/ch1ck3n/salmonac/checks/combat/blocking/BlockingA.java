package com.ch1ck3n.salmonac.checks.combat.blocking;

import com.ch1ck3n.salmonac.checks.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BlockingA extends Check {
    public BlockingA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Attack");
        this.setSubCategory("Blocking");
        this.setVlPerFail(5.0f);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if( e.getDamager() instanceof Player ) {
            Player player = ((Player)e.getDamager()).getPlayer();

            // Type A
            if( player.isBlocking() ) {
                flag( player, "Blocking = " + player.isBlocking() );
            }
        }
    }
}
