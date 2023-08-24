package com.ch1ck3n.salmonac.checks.movement.motion.prediction;

import com.ch1ck3n.salmonac.checks.Check;
import com.ch1ck3n.salmonac.events.SalmonMoveEvent;
import com.ch1ck3n.salmonac.utils.MathUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class MotionJ extends Check {
    public MotionJ(String name, Category category, Punishment punishment, String description) {
        super(name, category, punishment, description);
        this.setType("Prediction");
    }

    @EventHandler
    public void onMove(SalmonMoveEvent e) {
        if( e.getPlayer().getGameMode() == GameMode.CREATIVE ) return;

        // Type J (Prediction)
        if( e.getRespawnTick() > -1 ) return;
//        if( e.getWaterTick() < 5 ) return;
//
//        // Check
//        if ( e.isTouchingWater() &&
//                (e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.WATER ||
//                e.getPlayer().getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_WATER) ) {
//            double prediction = 0;
//            if( e.isServerGround() ) {
//                if( e.getDeltaY() != 0 && e.getServerAirTick() == 1 ) {
//                    prediction = -0.02D + 0.03999999910593033D;
//                    if (Math.abs(e.getDeltaY() - prediction) > 0.001)
//                        flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                }
//            }else if( e.isCollidingHorizontally() && e.getPlayer().getLocation().add(0.0d, e.getDeltaY() + 0.30000001192092896D, 0.0d).getBlock().getType() == Material.AIR ) {
//                if( e.getDeltaY() != 0 && e.getServerAirTick() == 1 ) {
//                    prediction = 0.30000001192092896D + 0.03999999910593033D;
//                    if (Math.abs(e.getDeltaY() - prediction) > 0.001)
//                        flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                }
//            }else {
////                prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D;
////                        if (Math.abs(e.getDeltaY() - prediction) > 0.04099999910593033D)
////                            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
////                                    "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                if( e.getLastDeltaY() < 0 ) {
//                    if( e.getDeltaY() < 0 && e.getDeltaYAccel() < 0 ) {
//                        prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D;
//                        if (Math.abs(e.getDeltaY() - prediction) > 0.001)
//                            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                    "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                    }else if( e.getDeltaY() > 0 && e.getDeltaYAccel() > 0 ) {
//                        prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D + 0.03999999910593033D;
//                        if( Math.abs(e.getDeltaY() - prediction) > 0.001 )
//                            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                    "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                    }
//                }else if( e.getLastDeltaY() > 0 ) {
//                    if( e.getDeltaY() < 0 && e.getDeltaYAccel() < 0 ) {
//                        prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D - 0.03999999910593033D;
//                        if (Math.abs(e.getDeltaY() - prediction) > 0.005)
//                            flag(e.getPlayer(), "DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                    "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                    }else if( e.getDeltaY() > 0 && e.getDeltaYAccel() > 0 ) {
//                        prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D + 0.03999999910593033D;
//                        if (Math.abs(e.getDeltaY() - prediction) > 0.005)
//                            flag(e.getPlayer(), "4 DeltaY = " + MathUtil.getInfoFromDouble10(e.getDeltaY()) +
//                                    "\nPrediction = " + MathUtil.getInfoFromDouble10(prediction));
//                    }
//                }
//            }
//
//
////            }else if( e.getDeltaY() < 0 && e.getLastDeltaY() > 0 ) {
////                prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D - 0.03999999910593033D;
////                if( Math.abs(e.getDeltaY() - prediction) > 0.001 )
////                    debug(e.getPlayer(), "Flagged(2)"+"/"+
////                            MathUtil.getInfoFromDouble10(e.getDeltaY()) + "/" +
////                            MathUtil.getInfoFromDouble10(e.getLastDeltaY()) + "/" +
////                            MathUtil.getInfoFromDouble10(prediction) + "/" +
////                            MathUtil.getInfoFromDouble10(e.getDeltaY() - prediction));
////            }else if( !e.isLastServerGround() && ((e.getDeltaY() > 0 && e.getLastDeltaY() > 0) ||
////                    (e.getDeltaY() > 0 && e.getLastDeltaY() < 0)) ) {
////                prediction = e.getLastDeltaY() * 0.800000011920929D - 0.02D + 0.03999999910593033D;
////                if( Math.abs(e.getDeltaY() - prediction) > 0.001 )
////                    debug(e.getPlayer(), "Flagged(3)"+"/"+
////                            MathUtil.getInfoFromDouble10(e.getDeltaY()) + "/" +
////                            MathUtil.getInfoFromDouble10(e.getLastDeltaY()) + "/" +
////                            MathUtil.getInfoFromDouble10(prediction) + "/" +
////                            MathUtil.getInfoFromDouble10(e.getDeltaY() - prediction));
////            }
//        }
    }
}
