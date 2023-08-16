package com.ch1ck3n.salmonac.checks.combat.reach;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ReachA extends Check {
    public ReachA(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(A)");
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( (Player) e.getDamager() );
        Player player = salmonPlayer.getPlayer();

        // Type A
        if( salmonPlayer.getRespawnTick() < 20 ) return;
        if( salmonPlayer.getAttackTick() > 1 ) return;
        if( salmonPlayer.getEntityAttacked() == null ) return;

        // Check
        double distance = e.getDamager().getLocation().distance(e.getEntity().getLocation()) - 0.4;
        double maxDistance = 3.1 + Math.abs(Math.hypot(salmonPlayer.getDeltaX(), salmonPlayer.getDeltaZ())) + Math.round(salmonPlayer.getPing() / 50) * 0.1f;
        if (player.getGameMode() == GameMode.CREATIVE) maxDistance += 3;
        if (distance > maxDistance) {
            salmonPlayer.reachABuffer.onTick();
            if (salmonPlayer.reachABuffer.getTick() > 1) {
                this.setVlPerFail(MathUtil.getVlFromDouble(maxDistance - distance));
                flag(player, "ActualDistance = " + String.format("%.10f", distance) +
                        "\nMaxDistance = " + maxDistance);
                if( this.getResponse() == Response.CANCEL ) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
