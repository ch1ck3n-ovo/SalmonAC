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
    public ReachA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("A");
        this.setSubCategory("Reach");
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
        double distance = player.getLocation().distance(e.getEntity().getLocation()) - 0.4;
        double maxDistance = 3.1d + Math.max(Math.abs(Math.hypot(salmonPlayer.getDeltaX(), salmonPlayer.getDeltaZ())), 0.3d) + Math.round(salmonPlayer.getPing() / 50f) * 0.1f;
        if (player.getGameMode() == GameMode.CREATIVE) maxDistance += 3;
        if (distance > maxDistance) {
            salmonPlayer.reachABuffer.onTick();
            if (salmonPlayer.reachABuffer.getTick() > 1) {
                this.setVlPerFail(MathUtil.getVlFromDouble(maxDistance - distance) * 20);
                flag(player, "ActualDistance = " + MathUtil.getInfoFromDouble10(distance) +
                        "\nMaxDistance = " + MathUtil.getInfoFromDouble10(maxDistance));
            }
        }
    }
}
