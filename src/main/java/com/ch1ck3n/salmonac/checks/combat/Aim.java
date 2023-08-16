package com.ch1ck3n.salmonac.checks.combat;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Aim extends Check {
    public Aim(String name, Response response, Punishment punishment, String description) {
        super(name, response, punishment, description);
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        // Type A
        if( e.getRespawnTick() < 20 || e.getDeltaPitch() == 0 || e.getLastDeltaPitch() == 0 ) {} else {
            if (Math.abs(e.getDeltaPitch() - e.getLastDeltaPitch()) < 0.000001) {
                e.getSalmonPlayer().aim1ABuffer.onTick();
                if (e.getSalmonPlayer().aim1ABuffer.getTick() > 12) {
                    this.setType("(A)");
                    this.setVlPerFail(1.0f);
                    flag(e.getPlayer(), "DeltaPitch = " + e.getDeltaPitch() +
                            "\nLastDeltaPitch = " + e.getLastDeltaPitch());
                }
            }
        }

        if( e.getRespawnTick() < 20 || e.getDeltaYaw() == 0 || e.getLastDeltaYaw() == 0 ) {} else {
            if( Math.abs(e.getDeltaYaw() - e.getLastDeltaYaw()) < 0.000001 ){
                e.getSalmonPlayer().aim0ABuffer.onTick();
                if( e.getSalmonPlayer().aim0ABuffer.getTick() > 12) {
                    this.setType("(A)");
                    this.setVlPerFail(1.0f);
                    flag(e.getPlayer(), "DeltaYaw = " + e.getDeltaYaw() +
                            "\nLastDeltaYaw = " + e.getLastDeltaYaw()  );
                }
            }
        }

        // Type B
        if( e.getRespawnTick() < 60 || e.getAttackTick() > 1 ||
                Float.isNaN(e.getDeltaPitchAccel()) || Float.isNaN(e.getLastDeltaPitchAccel()) ||
                Float.isInfinite(e.getDeltaPitchAccel()) || Float.isInfinite(e.getLastDeltaPitchAccel()) ||
                e.getDeltaPitchAccel() == 0 || e.getLastDeltaPitchAccel() == 0 ||
                e.getDeltaPitch() == 0 || e.getLastDeltaPitch() == 0) {} else {
            if (Math.abs(e.getDeltaPitchAccel() - e.getLastDeltaPitchAccel()) < 0.0001) {
                e.getSalmonPlayer().aim1BBuffer.onTick();
                if (e.getSalmonPlayer().aim1BBuffer.getTick() > 1) {
                    this.setType("(B)");
                    this.setVlPerFail(1.0f);
                    flag(e.getPlayer(), "DeltaPitchAccel = " + e.getDeltaPitchAccel() +
                            "\nLastDeltaPitchAccel = " + e.getLastDeltaPitchAccel());
                }
            }
        }

        if( e.getRespawnTick() < 60 || e.getAttackTick() > 1 ||
                Float.isNaN(e.getDeltaYawAccel()) || Float.isNaN(e.getLastDeltaYawAccel()) ||
                Float.isInfinite(e.getDeltaYawAccel()) || Float.isInfinite(e.getLastDeltaYawAccel()) ||
                e.getDeltaYawAccel() == 0 || e.getLastDeltaYawAccel() == 0 ||
                e.getDeltaYaw() == 0 || e.getLastDeltaYaw() == 0) {} else {
            if (Math.abs(e.getDeltaYawAccel() - e.getLastDeltaYawAccel()) < 0.0001) {
                e.getSalmonPlayer().aim0BBuffer.onTick();
                if (e.getSalmonPlayer().aim0BBuffer.getTick() > 1) {
                    this.setType("(B)");
                    this.setVlPerFail(1.0f);
                    flag(e.getPlayer(), "DeltaYawAccel = " + e.getDeltaYawAccel() +
                            "\nLastDeltaYawAccel = " + e.getLastDeltaYawAccel());
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if( !(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player) ) return;
        SalmonPlayer salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer((Player) e.getDamager());
        Player player = salmonPlayer.getPlayer();

        // Type C
        if( player.getLocation().distance( e.getEntity().getLocation() ) < 0.6 ) {} else {
            float min = PlayerUtil.getPitchMinAndMax(e.getEntity(), player)[0] % 360;
            float max = PlayerUtil.getPitchMinAndMax(e.getEntity(), player)[1] % 360;
            float pitch = player.getLocation().getPitch();
            if (min < pitch && pitch < max) {
            } else {
                salmonPlayer.aim1CBuffer.onTick();
                if (salmonPlayer.aim1CBuffer.getTick() > 3) {
                    this.setType("(C)");
                    this.setVlPerFail(2.0f);
                    flag(((Player) e.getDamager()), "MinPitch = " + min +
                            "\nYaw = " + pitch +
                            "\nMaxPitch = " + max);
                }
            }
        }

        if( player.getLocation().distance( e.getEntity().getLocation() ) < 0.6 ) {} else {
            float min = PlayerUtil.getYawMinAndMax(e.getEntity(), player)[0] % 360;
            float max = PlayerUtil.getYawMinAndMax(e.getEntity(), player)[1] % 360;
            float yaw = player.getLocation().getYaw() % 360;
            if (Math.abs(min - max) > 300) {
                if (yaw < 10) yaw += 360;
                float temp = min + 360;
                min = max;
                max = temp;
            }
            if (Math.abs(yaw - min) > 300 && Math.abs(yaw - max) > 300) yaw += 360;
            if (min < yaw && yaw < max) {
            } else {
                salmonPlayer.aim0CBuffer.onTick();
                if (salmonPlayer.aim0CBuffer.getTick() > 3) {
                    this.setType("(C)");
                    this.setVlPerFail(2.0f);
                    flag(((Player) e.getDamager()), "MinYaw = " + min +
                            "\nYaw = " + yaw +
                            "\nMaxYaw = " + max);
                }
            }
        }
    }
}
