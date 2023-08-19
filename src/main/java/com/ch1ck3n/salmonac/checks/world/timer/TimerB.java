package com.ch1ck3n.salmonac.checks.world.timer;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.event.EventHandler;

public class TimerB extends Check {
    public TimerB(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Slow");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        if( e.getRespawnTick() < 40 ) return;
        if( e.getSetBackTick() < 2 ) return;
        if( e.getWebTick() < 10 ) return;

        // Check
        if (e.getDeltaXZ() == 0) e.getSalmonPlayer().timerBBuffer.reduceTick();
        if (50 - (e.getPacketTime() - e.getLastPacketTime()) < -5 && e.getSalmonPlayer().timerSpeed.getTick() / 20f < 1) {
            e.getSalmonPlayer().timerBBuffer.onTick();
            if (e.getSalmonPlayer().timerBBuffer.getTick() > 10) {
                this.setVlPerFail(MathUtil.getVlFromFloat(1 - e.getSalmonPlayer().timerSpeed.getTick() / 20f) * 4);
                flag(e.getPlayer(), "Speed = " + e.getSalmonPlayer().timerSpeed.getTick() / 20f +
                        "\nDiff = " + (50 - (e.getPacketTime() - e.getLastPacketTime())) );
            }
        } else { e.getSalmonPlayer().timerBBuffer.reduceTick(); }
    }
}
