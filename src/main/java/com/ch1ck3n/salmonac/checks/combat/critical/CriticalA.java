package com.ch1ck3n.salmonac.checks.combat.critical;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CriticalA extends Check {
    public CriticalA(String name, Check.Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
        this.setType("(A)");
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player){
            Player player = ((Player)e.getDamager()).getPlayer();
            SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer(player);

            // Type A
            // Crits with 0 FallDistance
            if( salmonPlayer.getRespawnTick() < 20 ) return;
            if( salmonPlayer.getItemChangeTick() < 1 ) return;

            // Check
            double expectedDamage = (PlayerUtil.getWeaponDamage(player.getItemInHand().getType()) + (PlayerUtil.getEnchantmentLevel(player, Enchantment.DAMAGE_ALL) * 1.25));
            if ( salmonPlayer.getFallDistance() == 0 && e.getDamage() != expectedDamage ) {
                salmonPlayer.criticalABuffer.onTick();
                if(salmonPlayer.criticalABuffer.getTick() > 1) {
                    this.setVlPerFail(MathUtil.getVlFromDouble(Math.abs(e.getDamage() - expectedDamage)));
                    flag(player, "ExpectedDamage = " + expectedDamage +
                            "\nActualDamage = " + e.getDamage() +
                            "\nFallDistance = " + String.format("%.10f", salmonPlayer.getFallDistance()) +
                            "\nServerGround = " + salmonPlayer.isServerGround() +
                            "\nClientGround = " + salmonPlayer.isClientGround() +
                            "\nItemInHand = " + player.getItemInHand().getType() +
                            (this.getResponse() == Response.FIX ? "\n\nFix damage to " + expectedDamage : "") +
                            (this.getResponse() == Response.CANCEL ? "\n\nEvent cancelled" : ""));
                    if (this.getResponse() == Response.FIX) {
                        e.setDamage(expectedDamage);
                    } else if (this.getResponse() == Response.CANCEL) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
