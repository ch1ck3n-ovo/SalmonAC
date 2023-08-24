package com.ch1ck3n.salmonac.checks.movement.speed.prediction;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;

public class Prediction1B extends Check {
    public Prediction1B(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Prediction.Water");
        this.setSubCategory("Speed");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        if( e.getRespawnTick() < 20 ) return;
        if( e.getWaterTick() < 5 ) return;

        if( e.getLilyAround() != 0 && !e.isInWater()) return;

        float f1 = 0.8F;
        float f2 = 0.02F;
        float f3 = PlayerUtil.getBootsEnchantmentLevel(e.getPlayer(), Enchantment.DEPTH_STRIDER);

        if (f3 > 3.0F) f3 = 3.0F;
        if (!e.isServerGround()) f3 *= 0.5F;
        if (f3 > 0.0F) {
            f1 += (0.54600006F - f1) * f3 / 3.0F;
            f2 += (0.16277136F * 1.0F - f2) * f3 / 3.0F;
        }

        double prediction = e.getLastDeltaXZ() * f1 + f2;

        if ( e.getDeltaXZ() - prediction > 0.001 && e.isTouchingWater() &&
                (e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.WATER ||
                        e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_WATER)) {
            e.getSalmonPlayer().prediction1BBuffer.onTick();
            if ( e.getSalmonPlayer().prediction1BBuffer.getTick() > 2 ) {
                this.setVlPerFail(MathUtil.getVlFromDouble(e.getDeltaXZ() - prediction) * 5);
                flag(e.getPlayer(), "DeltaXZ = " + MathUtil.getInfoFromDouble10(e.getDeltaXZ()) +
                        "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
            }
        }else { e.getSalmonPlayer().prediction1BBuffer.reduceTick(); }
    }
}
