package com.ch1ck3n.salmonac.checks.combat.critical;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CriticalC extends Check {
    public CriticalC(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("C");
        this.setSubCategory("Critical");
        this.setVlPerFail(3.0f);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player){
            Player player = ((Player)e.getDamager()).getPlayer();
            SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer(player);

            // Type C
            // Crits with bad GroundState
            if( salmonPlayer.getRespawnTick() < 20 ) return;

            if( salmonPlayer.lastDeltaY == 0 ) return;

            // Check
            double expectedDamage = (PlayerUtil.getWeaponDamage(player.getItemInHand().getType()) + (PlayerUtil.getEnchantmentLevel(player, Enchantment.DAMAGE_ALL) * 1.25));
            if( salmonPlayer.getFallDistance() != 0 && salmonPlayer.isServerGround() && salmonPlayer.isClientGround() ){
                salmonPlayer.criticalCBuffer.onTick();
                if(salmonPlayer.criticalCBuffer.getTick() > 1){
                    flag( player,"ExpectedDamage = " + expectedDamage +
                            "\nActualDamage = " + e.getDamage() +
                            "\nFallDistance = " + MathUtil.getInfoFromDouble10(salmonPlayer.getFallDistance()) +
                            "\nServerGround = " + salmonPlayer.isServerGround() +
                            "\nClientGround = " + salmonPlayer.isClientGround() +
                            "\nItemInHand = " + player.getItemInHand().getType() );
                }
            }else {
                salmonPlayer.criticalCBuffer.reduceTick();
            }
        }
    }
}
