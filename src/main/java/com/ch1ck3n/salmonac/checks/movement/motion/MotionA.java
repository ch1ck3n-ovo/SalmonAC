package com.ch1ck3n.salmonac.checks.movement.motion;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffectType;

public class MotionA extends Check {
    public MotionA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("AirJump");
        this.setVlPerFail(2.0f);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type A (AirJump)
        // Disable if player just placed a block 6 ticks ago
        if( e.getRespawnTick() < 20 ) return;
        if( e.isFuzzyServerGround() ) return;
        if( e.getPlaceBlockTick() < 6 ) return;
        if( e.getLilyAround() != 0 ) return;
        if( e.isTouchingSlab() ) return;

        // Check
        if( e.isJumpUpwards() && !e.isServerGround() && !e.isLastServerGround() ){
            flag( e.getPlayer() ,"JumpUpwards = " + e.isJumpUpwards() +
                    "\nServerGround = " + e.isServerGround() +
                    "\nLastServerGround = " + e.isLastServerGround() );
        }
    }
}
