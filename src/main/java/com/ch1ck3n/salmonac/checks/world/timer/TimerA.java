package com.ch1ck3n.salmonac.checks.world.timer;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.event.EventHandler;

public class TimerA extends Check {
    public TimerA(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Fast");
        this.setSubCategory("Timer");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {

        if( e.getRespawnTick() < 40 ) return;
        if( e.getSetBackTick() < 2 ) return;

        // Check
        if (e.getDeltaXZ() == 0) e.getSalmonPlayer().timerABuffer.reduceTick();
        if (50 - (e.getPacketTime() - e.getLastPacketTime()) > 5 && e.getSalmonPlayer().timerSpeed.getTick() / 20f > 1) {
            e.getSalmonPlayer().timerABuffer.onTick();
            if (e.getSalmonPlayer().timerABuffer.getTick() > 8) {
                this.setVlPerFail(MathUtil.getVlFromFloat(e.getSalmonPlayer().timerSpeed.getTick() / 20f - 1) * 4);
                flag(e.getPlayer(), "Speed = " + e.getSalmonPlayer().timerSpeed.getTick() / 20f +
                        "\nDiff = " + (50 - (e.getPacketTime() - e.getLastPacketTime())) );
            }
        } else { e.getSalmonPlayer().timerABuffer.reduceTick(); }
    }
}
