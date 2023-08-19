package com.ch1ck3n.salmonac.checks.movement.sprinting;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class SprintingD extends Check {
    public SprintingD(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("(D)");
        this.setVlPerFail(4.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        // Type D
        if( PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.BLINDNESS) != 0 && e.getPlayer().isSprinting() ) {
            flag(e.getPlayer(), "Blinded = " + (PlayerUtil.getAmplifier(e.getPlayer(), PotionEffectType.BLINDNESS) != 0 )+
                    "\nSprinting = " + e.getPlayer().isSprinting());
        }
    }
}
