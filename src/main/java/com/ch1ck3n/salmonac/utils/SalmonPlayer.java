package com.ch1ck3n.salmonac.utils;

import com.ch1ck3n.salmonac.utils.buffers.Counter;
import com.ch1ck3n.salmonac.utils.buffers.SampleList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SalmonPlayer {

    /* ----- Buffer ----- */
    public static Counter aim0ABuffer = new Counter();
    public static Counter aim0BBuffer = new Counter();
    public static Counter aim0CBuffer = new Counter();
    public static Counter aim1ABuffer = new Counter();
    public static Counter aim1BBuffer = new Counter();
    public static Counter aim1CBuffer = new Counter();
    public static Counter criticalABuffer = new Counter();
    public static Counter criticalCBuffer = new Counter();
    public static Counter reachABuffer = new Counter();

    // Movement.InvMove
    public static Counter invMoveDBuffer = new Counter();

    // Movement.Jesus
    public static Counter jesusBBuffer = new Counter();
    public static Counter jesusCBuffer = new Counter();

    // Movement.Motion
    public static Counter motionEBuffer = new Counter();
    public static Counter motionHBuffer = new Counter();
    public static Counter motionIBuffer = new Counter();

    // Movement.Speed
    public static Counter speedCBuffer = new Counter();
    public static Counter speedEBuffer = new Counter();
    public static Counter speedFBuffer = new Counter();
    public static SampleList speedFSampleList = new SampleList(100);
    public static Counter speedGBuffer = new Counter();
    public static SampleList speedHSampleList = new SampleList(100);
    public static Counter speedIBuffer = new Counter();

    // Player.GroundSpoof
    public static Counter groundSpoofCBuffer = new Counter();

    // World.Timer
    public static Counter timerABuffer = new Counter();
    public static Counter timerBBuffer = new Counter();
    public static Counter timerCBuffer = new Counter();
    public static Counter timerSpeed = new Counter();
    public static SampleList timerCSampleList = new SampleList(20);

    /* ----- Player -----*/
    Player player;
    public Player getPlayer(){
        return player;
    }
    public SalmonPlayer(Player player){
        this.player = player;
    }
    public int getPing() {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Class<?> entityPlayerClass = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
            Object entityPlayer = craftPlayerClass.getMethod("getHandle").invoke(craftPlayer);
            int ping = (int) entityPlayerClass.getField("ping").get(entityPlayer);
            return ping;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if the ping cannot be obtained
    }

    /* ----- Verbose ----- */
    boolean verbose = true;
    public boolean getVerboseStatus(){
        return verbose;
    }
    public void setVerboseStatus(boolean bool){
        this.verbose = bool;
    }

    /* ----- Packet ----- */
    public long packetTime;
    public long lastPacketTime;
    public long getPacketTime(){
        return packetTime;
    }
    public long getLastPacketTime(){
        return lastPacketTime;
    }

    /* ----- VL -----*/
    public float totalVL;
    public float getTotalVL(){
        return totalVL;
    }
    public HashMap<String,Float> checksVL = new HashMap<>();

    public float getCheckVL(String check){
        if( checksVL.get( check ) == null ){
            checksVL.put( check , 0f );
            return 0;
        }
        return checksVL.get( check );
    }

    @Deprecated
    public float onFailed(String check){
        totalVL++;
        float newVL = getCheckVL( check ) + 1;
        checksVL.put( check , newVL );
        return newVL;
    }

    @Deprecated
    public float onFailed(String check, float vl){
        totalVL += vl;
        float newVL = getCheckVL( check ) + vl;
        checksVL.put( check , newVL );
        return newVL;
    }

    public void resetCheckVL(String check) {
        if( checksVL.get( check ) == null ){
            checksVL.put( check , 0f );
            return;
        }
        checksVL.replace(check, 0f);
    }

    /* ----- Location -----*/
    Location lastLocation;
    public Location getLastLocation(){
        return lastLocation;
    }
    public void updateLastLocation(Location location){
        this.lastLocation = location;
    }

    /* ----- Motion -----*/
    public double deltaX, deltaY, deltaZ;
    public double getDeltaX() {
        return deltaX;
    }
    public double getDeltaY() {
        return deltaY;
    }
    public double getDeltaZ() {
        return deltaZ;
    }
    public double lastDeltaX, lastDeltaY, lastDeltaZ;
    public double getLastDeltaX() {
        return lastDeltaX;
    }
    public double getLastDeltaY() {
        return lastDeltaY;
    }
    public double getLastDeltaZ() {
        return lastDeltaZ;
    }
    public double deltaXZAccel, lastDeltaXZAccel;
    public double getDeltaXZAccel() {
        return deltaXZAccel;
    }
    public double getLastDeltaXZAccel() {
        return lastDeltaXZAccel;
    }
    public double deltaYAccel, lastDeltaYAccel;
    public double getDeltaYAccel() {
        return deltaYAccel;
    }
    public double getLastDeltaYAccel() {
        return lastDeltaYAccel;
    }

    /* ----- Velocity ----- */
    public double velocityX, velocityY, velocityZ, velocityH;
    public int velocityTick;
    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    public double getVelocityZ() {
        return velocityZ;
    }
    public double getVelocityH() {
        return velocityH;
    }
    public int getVelocityTick(){
        return velocityTick;
    }
    public double lastVelocityX, lastVelocityY, lastVelocityZ, lastVelocityH;
    public double getLastVelocityX() {
        return lastVelocityX;
    }
    public double getLastVelocityY() {
        return lastVelocityY;
    }
    public double getLastVelocityZ() {
        return lastVelocityZ;
    }
    public double getLastVelocityH() {
        return lastVelocityH;
    }

    /* ----- Rotation -----*/
    public float deltaYaw, deltaPitch;
    public float getDeltaYaw() {
        return deltaYaw;
    }
    public float getDeltaPitch() {
        return deltaPitch;
    }
    public float lastDeltaYaw, lastDeltaPitch;
    public float getLastDeltaYaw() {
        return lastDeltaYaw;
    }
    public float getLastDeltaPitch() {
        return lastDeltaPitch;
    }
    public float deltaYawAccel, deltaPitchAccel;
    public float getDeltaYawAccel() {
        return deltaYawAccel;
    }
    public float getDeltaPitchAccel() {
        return deltaPitchAccel;
    }
    public float lastDeltaYawAccel, lastDeltaPitchAccel;
    public float getLastDeltaYawAccel() {
        return lastDeltaYawAccel;
    }
    public float getLastDeltaPitchAccel() {
        return lastDeltaPitchAccel;
    }


    /* ----- Ground -----*/
    public boolean fuzzyServerGround, littleFuzzyServerGround, lastFuzzyServerGround, superFuzzyServerGround,
            mathGround, lastMathGround,
            clientGround, lastClientGround,
            serverGround, lastServerGround;
    public boolean isFuzzyServerGround(){
        return fuzzyServerGround;
    }
    public boolean isLittleFuzzyServerGround(){
        return littleFuzzyServerGround;
    }
    public boolean isLastFuzzyServerGround(){
        return fuzzyServerGround;
    }
    public boolean isSuperFuzzyServerGround(){
        return superFuzzyServerGround;
    }
    public boolean isMathGround(){
        return mathGround;
    }
    public boolean isLastMathGround(){
        return lastMathGround;
    }
    public boolean isClientGround(){
        return clientGround;
    }
    public boolean isLastClientGround(){
        return lastClientGround;
    }
    public boolean isServerGround(){
        return serverGround;
    }
    public boolean isLastServerGround(){
        return lastServerGround;
    }
    public Location lastGroundLocation;
    public Location getLastGroundLocation() { return lastGroundLocation; }

    /* ----- Tick(Ground) -----*/
    public int serverAirTick = 0, clientAirTick = 0;
    public int getServerAirTick(){
        return serverAirTick;
    }
    public int getClientAirTick(){
        return clientAirTick;
    }

    /* ----- Fall -----*/
    public double fallDistance, lastFallDamage;
    public double getFallDistance(){
        return fallDistance;
    }
    public void setFallDistance(double d){
        fallDistance = d;
    }
    public double getLastFallDamage(){
        return lastFallDamage;
    }
    public void setLastFallDamage(double d){
        lastFallDamage = d;
    }

    /* ----- Tick(Block) -----*/
    public int iceTick = 0, slimeTick = 0, waterTick = 0, webTick = 0;
    public int getIceTick(){
        return iceTick;
    }
    public int getSlimeTick(){
        return slimeTick;
    }
    public int getWaterTick(){
        return waterTick;
    }
    public int getWebTick(){
        return webTick;
    }

    /* ----- Tick(Entity) -----*/
    public int attackTick = 0, damageTick = 0;
    public int getAttackTick(){
        return attackTick;
    }
    public int getDamageTick(){
        return damageTick;
    }

    /* ----- Tick(Inventory) -----*/
    public int guiClickedTick = 0;
    public int getGuiClickedTick(){
        return guiClickedTick;
    }
    public static Counter guiClickedCounter = new Counter();

    /* ----- Cuboid things ----- */
    public boolean collidingHorizontally, collidingVerticallyUp, fuzzyCollidingHorizontally,
            pistonAround, slimeGround, snowAround,
            touchingClimbable, touchingLava, touchingLiquid, lastTouchingLiquid,
            touchingStair, touchingSlab, touchingWater;
    public int lilyAround;
    public boolean isCollidingHorizontally() {
        return collidingHorizontally;
    }
    public boolean isCollidingVerticallyUp() {
        return collidingVerticallyUp;
    }
    public boolean isFuzzyCollidingHorizontally() {
        return fuzzyCollidingHorizontally;
    }
    public int getLilyAround() {
        return lilyAround;
    }
    public boolean isPistonAround() {
        return pistonAround;
    }
    public boolean isSlimeGround() {
        return slimeGround;
    }
    public boolean isSnowAround() {
        return snowAround;
    }
    public boolean isTouchingClimbable() {
        return touchingClimbable;
    }
    public boolean isTouchingLava() {
        return touchingLava;
    }
    public boolean isTouchingLiquid() {
        return touchingLiquid;
    }
    public boolean isLastTouchingLiquid() {
        return lastTouchingLiquid;
    }
    public boolean isTouchingSlab() {
        return touchingSlab;
    }
    public boolean isTouchingStair() {
        return touchingStair;
    }
    public boolean isTouchingWater() {
        return touchingWater;
    }

    /* ----- Tick(Player) -----*/
    public int collidingHorizontallyTick = 0, collidingVerticallyUpTick = 0,
            climbTick = 0, hungryTick = 0, itemChangeTick = 0, jumpTick = 0, placeBlockTick = 0,
            teleportTick = 0, respawnTick = 0, setBackTick = 0, swingTick = 0;
    public int getCollidingHorizontallyTick() {
        return collidingHorizontallyTick;
    }
    public int getCollidingVerticallyUpTick() {
        return collidingVerticallyUpTick;
    }
    public int getClimbTick(){
        return climbTick;
    }
    public int getHungryTick(){
        return hungryTick;
    }
    public int getItemChangeTick(){
        return itemChangeTick;
    }
    public int getJumpTick(){
        return jumpTick;
    }
    public int getPlaceBlockTick(){
        return placeBlockTick;
    }
    public int getTeleportTick(){
        return teleportTick;
    }
    public int getRespawnTick(){
        return respawnTick;
    }
    public int getSetBackTick(){
        return setBackTick;
    }
    public int getSwingTick(){
        return swingTick;
    }

    /* ----- SetBack -----*/
    public boolean setBack;
    public boolean doSetBack(){
        return setBack;
    }

    /* ----- Others ----- */
    public boolean flag1, flag2; // For SpeedF
    public boolean flag3, flag4; // For SpeedH
    public boolean jumping;
    public boolean isJumping() {
        return jumping;
    }
    public boolean jumpUpwards;
    public boolean isJumpUpwards() {
        return jumpUpwards;
    }
    public boolean canJump, lastCanJump;
    public boolean isCanJump() {
        return canJump;
    }
    public boolean isLastCanJump() {
        return lastCanJump;
    }
    public Entity entityAttacked;
    public Entity getEntityAttacked(){
        return entityAttacked;
    }

}
