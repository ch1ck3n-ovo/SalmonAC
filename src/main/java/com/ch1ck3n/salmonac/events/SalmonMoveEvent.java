package com.ch1ck3n.salmonac.events;

import com.ch1ck3n.salmonac.SalmonAC;
import com.ch1ck3n.salmonac.utils.Cuboid;
import com.ch1ck3n.salmonac.utils.CustomLocation;
import com.ch1ck3n.salmonac.utils.SalmonPlayer;
import com.ch1ck3n.salmonac.utils.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffectType;


public class SalmonMoveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /* ----- Player ----- */
    Player player;
    public Player getPlayer(){
        return player;
    }
    SalmonPlayer salmonPlayer;
    public SalmonPlayer getSalmonPlayer(){
        return salmonPlayer;
    }

    /* ----- Packet ----- */
    public long getPacketTime(){
        return salmonPlayer.getPacketTime();
    }
    public long getLastPacketTime(){
        return salmonPlayer.getLastPacketTime();
    }

    /* --- Locations --- */
    Location to, from;
    public Location getTo(){
        return to;
    }
    public Location getFrom(){
        return from;
    }

    /* ----- MotionA ----- */
    double deltaX, deltaY, deltaZ, deltaXZ;
    public double getDeltaX() {
        return deltaX;
    }
    public double getDeltaY() {
        return deltaY;
    }
    public double getDeltaZ() {
        return deltaZ;
    }
    public double getDeltaXZ(){
        return deltaXZ;
    }
    double lastDeltaX, lastDeltaY, lastDeltaZ, lastDeltaXZ;
    public double getLastDeltaX() {
        return lastDeltaX;
    }
    public double getLastDeltaY() {
        return lastDeltaY;
    }
    public double getLastDeltaZ() {
        return lastDeltaZ;
    }
    public double getLastDeltaXZ(){
        return lastDeltaXZ;
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

    /* Angle */
    float deltaYaw, deltaPitch;
    public float getDeltaYaw(){
        return deltaYaw;
    }
    public float getDeltaPitch(){
        return deltaPitch;
    }
    float lastDeltaYaw, lastDeltaPitch;
    public float getLastDeltaYaw(){
        return lastDeltaYaw;
    }
    public float getLastDeltaPitch(){
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

    /* ----- Ground ----- */
    public boolean isMathGround(){
        return salmonPlayer.isMathGround();
    }
    public boolean isLastMathGround(){
        return salmonPlayer.isLastMathGround();
    }
    public boolean isFuzzyServerGround(){
        return salmonPlayer.isFuzzyServerGround();
    }
    public boolean isLittleFuzzyServerGround(){
        return salmonPlayer.isLittleFuzzyServerGround();
    }
    public boolean isLastFuzzyServerGround(){
        return salmonPlayer.isLastFuzzyServerGround();
    }
    public boolean isSuperFuzzyServerGround(){
        return salmonPlayer.isSuperFuzzyServerGround();
    }
    public boolean isClientGround(){
        return salmonPlayer.isClientGround();
    }
    public boolean isLastClientGround(){
        return salmonPlayer.isLastClientGround();
    }
    public boolean isServerGround(){
        return salmonPlayer.isServerGround();
    }
    public boolean isLastServerGround(){
        return salmonPlayer.isLastServerGround();
    }
    public boolean isLastLastServerGround(){
        return salmonPlayer.isLastLastServerGround();
    }

    /* ----- Tick(Ground) ----- */
    public int getServerAirTick(){
        return salmonPlayer.getServerAirTick();
    }
    public int getClientAirTick(){
        return salmonPlayer.getClientAirTick();
    }

    /* ----- Fall -----*/
    public double getFallDistance(){
        return salmonPlayer.getFallDistance();
    }
    public void setFallDistance(double d){
        salmonPlayer.setFallDistance(d);
    }
    public double getLastFallDamage(){
        return salmonPlayer.getLastFallDamage();
    }
    public void setLastFallDamage(double d){
        salmonPlayer.setLastFallDamage(d);
    }

    /* ----- Tick(Block) ----- */
    public int getIceTick(){
        return salmonPlayer.getIceTick();
    }
    public int getSlimeTick(){
        return salmonPlayer.getSlimeTick();
    }
    public int getLavaTick(){
        return salmonPlayer.getLavaTick();
    }
    public int getWaterTick(){
        return salmonPlayer.getWaterTick();
    }
    public int getWebTick(){
        return salmonPlayer.getWebTick();
    }

    /* ----- Tick(Entity) -----*/
    public int getAttackTick(){
        return salmonPlayer.getAttackTick();
    }
    public int getDamageTick(){
        return salmonPlayer.getDamageTick();
    }

    /* ----- Tick(Player) ----- */
    public int getHungryTick(){
        return salmonPlayer.getHungryTick();
    }
    public int getItemChangeTick(){
        return salmonPlayer.getItemChangeTick();
    }
    public int getJumpTick(){
        return salmonPlayer.getJumpTick();
    }
    public int getMoveTick(){
        return salmonPlayer.getMoveTick();
    }
    public int getPlaceBlockTick(){
        return salmonPlayer.getPlaceBlockTick();
    }
    public int getTeleportTick(){
        return salmonPlayer.getTeleportTick();
    }
    public int getRespawnTick(){
        return salmonPlayer.getRespawnTick();
    }
    public int getSetBackTick(){
        return salmonPlayer.getSetBackTick();
    }
    public int getSwingTick(){
        return salmonPlayer.getSwingTick();
    }

    /* ----- Cuboid things ----- */
    public int getClimbTick(){
        return salmonPlayer.getClimbTick();
    }
    public boolean isCollidingHorizontally() {
        return salmonPlayer.isCollidingHorizontally();
    }
    public boolean isCollidingVerticallyUp() {
        return salmonPlayer.isCollidingVerticallyUp();
    }
    public int getCollidingHorizontallyTick() {
        return salmonPlayer.getCollidingHorizontallyTick();
    }
    public int getCollidingVerticallyUpTick() {
        return salmonPlayer.getCollidingVerticallyUpTick();
    }
    public boolean isFuzzyCollidingHorizontally() {
        return salmonPlayer.isFuzzyCollidingHorizontally();
    }
    public int getCarpetAround() {
        return salmonPlayer.getCarpetAround();
    }
    public int getLilyAround() {
        return salmonPlayer.getLilyAround();
    }
    public boolean isPistonAround() {
        return salmonPlayer.isPistonAround();
    }
    public boolean isSlimeAround() {
        return salmonPlayer.isSlimeGround();
    }
    public boolean isSnowAround() {
        return salmonPlayer.isSnowAround();
    }
    public boolean isTouchingClimable() {
        return salmonPlayer.isTouchingClimbable();
    }
    public boolean isTouchingLava() {
        return salmonPlayer.isTouchingLava();
    }
    public boolean isTouchingLiquid() {
        return salmonPlayer.isTouchingLiquid();
    }
    public boolean isLastTouchingLiquid() {
        return salmonPlayer.isLastTouchingLiquid();
    }
    public boolean isTouchingSlab() {
        return salmonPlayer.isTouchingSlab();
    }
    public boolean isTouchingStair() {
        return salmonPlayer.isTouchingStair();
    }
    public boolean isTouchingWater() {
        return salmonPlayer.isTouchingWater();
    }

    /* ----- Others ----- */
    public boolean isJumping() { return salmonPlayer.isJumping(); }
    public boolean isJumpUpwards() {
        return salmonPlayer.isJumpUpwards();
    }
    public boolean isLastJumpUpwards() {
        return salmonPlayer.isLastJumpUpwards();
    }
    public boolean isCanJump() {
        return salmonPlayer.isCanJump();
    }
    public boolean isLastCanJump() {
        return salmonPlayer.isLastCanJump();
    }
    public Entity getEntityAttacked(){
        return salmonPlayer.getEntityAttacked();
    }

    public SalmonMoveEvent(Player player, Location newLoc) {
        salmonPlayer = SalmonAC.getInstance().getPlayerManager().getPlayer( player );

        /* ----- Tick Update ----- */
        salmonPlayer.attackTick++;
        salmonPlayer.damageTick++;
        salmonPlayer.guiClickedTick++;
        salmonPlayer.itemChangeTick++;
        salmonPlayer.placeBlockTick++;
        salmonPlayer.respawnTick++;
        salmonPlayer.setBackTick++;
        salmonPlayer.swingTick++;
        salmonPlayer.teleportTick++;
        salmonPlayer.velocityTick++;

        if( getAttackTick() > 2 ) {
            salmonPlayer.entityAttacked = null;
        }

        if( player.getFoodLevel() <= 6 ) {
            salmonPlayer.hungryTick++;
        }else {
            salmonPlayer.hungryTick = 0;
        }

        if( getJumpTick() > 0 ) {
            salmonPlayer.jumpTick--;
        }

        salmonPlayer.lastCanJump = salmonPlayer.canJump;

        if( getJumpTick() == 0 ) {
            salmonPlayer.canJump = true;
        }

        /* ----- Location ----- */
        this.player = player;
        this.to = newLoc;
        this.from = salmonPlayer.getLastLocation();
        if( from == null ) {
            salmonPlayer.updateLastLocation(newLoc);
            return;
        }

        /* ----- Motion ----- */
        deltaX = to.getX() - from.getX();
        deltaY = to.getY() - from.getY();
        deltaZ = to.getZ() - from.getZ();
        deltaXZ = Math.hypot(deltaX,deltaZ);
        if(deltaXZ == 0) {
            salmonPlayer.moveTick = 0;
        }else {
            salmonPlayer.moveTick++;
        }

        salmonPlayer.deltaX = deltaX;
        salmonPlayer.deltaY = deltaY;
        salmonPlayer.deltaZ = deltaZ;

        lastDeltaX = salmonPlayer.lastDeltaX;
        lastDeltaY = salmonPlayer.lastDeltaY;
        lastDeltaZ = salmonPlayer.lastDeltaZ;
        lastDeltaXZ = Math.hypot(lastDeltaX,lastDeltaZ);

        deltaXZAccel = deltaXZ - lastDeltaXZ;
        if( Double.isInfinite(deltaXZAccel) || Double.isNaN(deltaXZAccel) ) deltaXZAccel = 0;
        salmonPlayer.deltaXZAccel = deltaXZAccel;
        lastDeltaXZAccel = salmonPlayer.lastDeltaXZAccel;

        deltaYAccel = deltaY - lastDeltaY;
        if( Double.isInfinite(deltaYAccel) || Double.isNaN(deltaYAccel) ) deltaXZAccel = 0;
        salmonPlayer.deltaYAccel = deltaYAccel;
        lastDeltaYAccel = salmonPlayer.lastDeltaYAccel;

        /* ----- Rotation ----- */
        deltaYaw = to.getYaw() - from.getYaw();
        deltaPitch = to.getPitch() - from.getPitch();

        salmonPlayer.deltaYaw = deltaYaw;
        salmonPlayer.deltaPitch = deltaPitch;

        lastDeltaYaw = salmonPlayer.lastDeltaYaw;
        lastDeltaPitch = salmonPlayer.lastDeltaPitch;

        deltaYawAccel = getDeltaYaw() / getLastDeltaYaw();
        if( Double.isInfinite(deltaYawAccel) || Double.isNaN(deltaYawAccel) ) deltaYawAccel = 0;
        salmonPlayer.deltaYawAccel = deltaYawAccel;
        lastDeltaYawAccel = salmonPlayer.lastDeltaYawAccel;

        deltaPitchAccel = getDeltaPitch() / getLastDeltaPitch();
        if( Double.isInfinite(deltaPitchAccel) || Double.isNaN(deltaPitchAccel) ) deltaPitchAccel = 0;
        salmonPlayer.deltaPitchAccel = deltaPitchAccel;
        lastDeltaPitchAccel = salmonPlayer.lastDeltaPitchAccel;

        /* ----- Ground ----- */
        salmonPlayer.lastMathGround = salmonPlayer.mathGround;

        if( to.getY() % (1/64D) == 0 ) {
            salmonPlayer.mathGround = true;
        }else {
            salmonPlayer.mathGround = false;
        }

        salmonPlayer.lastClientGround = salmonPlayer.isClientGround();

        if( this.player.isOnGround() ) {
            salmonPlayer.clientGround = true;
            salmonPlayer.clientAirTick = 0;
        }else {
            salmonPlayer.clientGround = false;
            salmonPlayer.clientAirTick++;
        }

        /* ----- Cuboid ----- */
        CustomLocation location = new CustomLocation(to.getX(), to.getY(), to.getZ(), to.getYaw(), to.getPitch());
        final Cuboid aboveHeadBox = (new Cuboid(location)).expand(0.3125D, 0.0D, 0.3125D).move(0.0D, 2.0D, 0.0D);
        final Cuboid aboveHeadCuboid = (new Cuboid(location)).expand(0.3125D, 0.07D, 0.3125D).move(0.0D, 2.0D, 0.0D);
        final Cuboid horizontalBox = new Cuboid(location).expand(0.3125D, 0.0D, 0.3125D);
        final Cuboid lilyBox = new Cuboid(location).expand(1.0D, 0.0D, 1.0D);
        final Cuboid nearBox = new Cuboid(location).expand(0.3125D, 0.55D, 0.3125D);
        final Cuboid underCuboid = (new Cuboid(location)).expand(0.3125D, 0.07D, 0.3125D).move(0.0D, -0.5D, 0.0D);

        World world = this.player.getWorld();
        salmonPlayer.collidingVerticallyUp = !aboveHeadCuboid.checkBlocks(world, material -> (material == Material.AIR)) &&
                !aboveHeadCuboid.checkBlocks(world, material -> (material == Material.WATER)) &&
                !aboveHeadCuboid.checkBlocks(world, material -> (material == Material.STATIONARY_WATER)) &&
                !aboveHeadCuboid.checkBlocks(world, material -> (material == Material.LAVA)) &&
                !aboveHeadCuboid.checkBlocks(world, material -> (material == Material.STATIONARY_LAVA));

        salmonPlayer.collidingHorizontally = !horizontalBox.checkBlocks(world, material -> (material == Material.AIR)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.WATER)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.STATIONARY_WATER)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.LAVA)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.STATIONARY_LAVA)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.WEB)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.SAPLING)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.GRASS)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.LONG_GRASS)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.DEAD_BUSH)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.YELLOW_FLOWER)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.RED_ROSE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.BROWN_MUSHROOM)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.RED_MUSHROOM)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.TORCH)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.WATER_LILY)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.CARPET)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.DOUBLE_PLANT)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.PAINTING)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.SIGN)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.LEVER)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.WOOD_PLATE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.STONE_PLATE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.IRON_PLATE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.GOLD_PLATE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_ON)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_OFF)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.STONE_BUTTON)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.REDSTONE)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.RAILS)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.ACTIVATOR_RAIL)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.DETECTOR_RAIL)) &&
                !horizontalBox.checkBlocks(world, material -> (material == Material.POWERED_RAIL));

        salmonPlayer.fuzzyCollidingHorizontally = !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.AIR)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.WATER)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.STATIONARY_WATER)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.LAVA)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.STATIONARY_LAVA)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.WEB))/* &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.SAPLING)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.GRASS)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.LONG_GRASS)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.DEAD_BUSH)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.YELLOW_FLOWER)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.RED_ROSE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.BROWN_MUSHROOM)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.RED_MUSHROOM)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.TORCH)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.WATER_LILY)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.CARPET)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.DOUBLE_PLANT)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.PAINTING)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.SIGN)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.LEVER)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.WOOD_PLATE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.STONE_PLATE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.IRON_PLATE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.GOLD_PLATE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_ON)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_OFF)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.STONE_BUTTON)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.REDSTONE)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.RAILS)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.ACTIVATOR_RAIL)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.DETECTOR_RAIL)) &&
                !horizontalBox.expand(0.7875D, 0.0D, 0.7875D).move(0.0D,0.5D,0.0D).checkBlocks(world, material -> (material == Material.POWERED_RAIL))*/;

        salmonPlayer.touchingClimbable = horizontalBox.checkBlocks(world, material -> (material == Material.LADDER)) ||
                horizontalBox.checkBlocks(world, material -> (material == Material.VINE));

        double d = 0.001D, e = -0.4000000059604645D, f = -0.10000000149011612D;
        final Cuboid waterBox = new Cuboid(location).expand(0.3125D, 0.55D, 0.3125D).expand(0.0D, e, 0.0D).contract(d, d, d);
        final Cuboid waterCuboid = (new Cuboid(location)).expand(0.3125D, 0.07D, 0.3125D).move(0.0D, -0.5D, 0.0D).expand(0.0D, e, 0.0D).contract(d, d, d);
        final Cuboid lavaBox = new Cuboid(location).expand(0.3125D, 0.55D, 0.3125D).expand(f, e, f);
        final Cuboid lavaCuboid = (new Cuboid(location)).expand(0.3125D, 0.07D, 0.3125D).move(0.0D, -0.5D, 0.0D).expand(f, e, f);

        salmonPlayer.lastTouchingLiquid = salmonPlayer.touchingLiquid;

        salmonPlayer.touchingLiquid = (waterBox.count(world, Material.WATER) > 0 ||
                waterBox.count(world, Material.STATIONARY_WATER) > 0 ||
                waterCuboid.count(world, Material.WATER) > 0 ||
                waterCuboid.count(world, Material.STATIONARY_WATER) > 0 ||
                lavaBox.count(world, Material.LAVA) > 0 ||
                lavaBox.count(world, Material.STATIONARY_LAVA) > 0 ||
                lavaCuboid.count(world, Material.LAVA) > 0 ||
                lavaCuboid.count(world, Material.STATIONARY_LAVA) > 0);

        salmonPlayer.touchingWater = (waterBox.count(world, Material.WATER) > 0 ||
                waterBox.count(world, Material.STATIONARY_WATER) > 0 ||
                waterCuboid.count(world, Material.WATER) > 0 ||
                waterCuboid.count(world, Material.STATIONARY_WATER) > 0);
                /*(waterBox.checkBlocks(world, material -> (material == Material.WATER)) ||
                        waterBox.checkBlocks(world, material -> (material == Material.STATIONARY_WATER)) ||
                        waterCuboid.checkBlocks(world, material -> (material == Material.WATER)) ||
                        waterCuboid.checkBlocks(world, material -> (material == Material.STATIONARY_WATER)));*/

        salmonPlayer.touchingLava = (lavaBox.count(world, Material.LAVA) > 0 ||
                lavaBox.count(world, Material.STATIONARY_LAVA) > 0 ||
                lavaCuboid.count(world, Material.LAVA) > 0 ||
                lavaCuboid.count(world, Material.STATIONARY_LAVA) > 0);

        salmonPlayer.pistonAround = (nearBox.count(world, Material.PISTON_BASE) > 0 ||
                nearBox.count(world, Material.PISTON_MOVING_PIECE) > 0 ||
                nearBox.count(world, Material.PISTON_EXTENSION) > 0 ||
                nearBox.count(world, Material.PISTON_STICKY_BASE) > 0 ||
                underCuboid.count(world, Material.PISTON_BASE) > 0 ||
                underCuboid.count(world, Material.PISTON_MOVING_PIECE) > 0 ||
                underCuboid.count(world, Material.PISTON_EXTENSION) > 0 ||
                underCuboid.count(world, Material.PISTON_STICKY_BASE) > 0);

        salmonPlayer.slimeGround = (nearBox.count(world, Material.SLIME_BLOCK) > 0 ||
                underCuboid.count(world, Material.SLIME_BLOCK) > 0);

        salmonPlayer.snowAround = (nearBox.count(world, Material.SNOW_BLOCK) > 0 ||
                underCuboid.count(world, Material.SNOW_BLOCK) > 0 ||
                nearBox.count(world, Material.SNOW) > 0 ||
                underCuboid.count(world, Material.SNOW) > 0);

        salmonPlayer.pistonAround = (nearBox.count(world, Material.PISTON_BASE) > 0 ||
                nearBox.count(world, Material.PISTON_MOVING_PIECE) > 0 ||
                nearBox.count(world, Material.PISTON_EXTENSION) > 0 ||
                nearBox.count(world, Material.PISTON_STICKY_BASE) > 0 ||
                underCuboid.count(world, Material.PISTON_BASE) > 0 ||
                underCuboid.count(world, Material.PISTON_MOVING_PIECE) > 0 ||
                underCuboid.count(world, Material.PISTON_EXTENSION) > 0 ||
                underCuboid.count(world, Material.PISTON_STICKY_BASE) > 0);

        salmonPlayer.touchingSlab = (underCuboid.count(world, Material.STONE_SLAB2) > 0 ||
                underCuboid.count(world, Material.DOUBLE_STONE_SLAB2) > 0 ||
                underCuboid.count(world, Material.STEP) > 0 ||
                underCuboid.count(world, Material.DOUBLE_STEP) > 0 ||
                underCuboid.count(world, Material.WOOD_STEP) > 0 ||
                underCuboid.count(world, Material.WOOD_DOUBLE_STEP) > 0);

        salmonPlayer.touchingStair = (underCuboid.count(world, Material.ACACIA_STAIRS) > 0 ||
                underCuboid.count(world, Material.BIRCH_WOOD_STAIRS) > 0 ||
                underCuboid.count(world, Material.BRICK_STAIRS) > 0 ||
                underCuboid.count(world, Material.COBBLESTONE_STAIRS) > 0 ||
                underCuboid.count(world, Material.DARK_OAK_STAIRS) > 0 ||
                underCuboid.count(world, Material.JUNGLE_WOOD_STAIRS) > 0 ||
                underCuboid.count(world, Material.NETHER_BRICK_STAIRS) > 0 ||
                underCuboid.count(world, Material.QUARTZ_STAIRS) > 0 ||
                underCuboid.count(world, Material.RED_SANDSTONE_STAIRS) > 0 ||
                underCuboid.count(world, Material.SANDSTONE_STAIRS) > 0 ||
                underCuboid.count(world, Material.SMOOTH_STAIRS) > 0 ||
                underCuboid.count(world, Material.SPRUCE_WOOD_STAIRS) > 0 ||
                underCuboid.count(world, Material.WOOD_STAIRS) > 0);

        salmonPlayer.carpetAround = lilyBox.count(world, Material.CARPET);
        salmonPlayer.lilyAround = lilyBox.count(world, Material.WATER_LILY);

        salmonPlayer.lastLastServerGround = salmonPlayer.isLastServerGround();
        salmonPlayer.lastServerGround = salmonPlayer.isServerGround();

        if( (!nearBox.checkBlocks(world, material -> material == Material.AIR) &&
                (salmonPlayer.isMathGround() &&
                        !underCuboid.checkBlocks(world, material -> material == Material.WATER) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.STATIONARY_WATER) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.LAVA) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.STATIONARY_LAVA) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.WEB) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.SAPLING)) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.LONG_GRASS) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.DEAD_BUSH) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.YELLOW_FLOWER) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.RED_ROSE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.BROWN_MUSHROOM) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.RED_MUSHROOM) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.TORCH) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.DOUBLE_PLANT) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.PAINTING) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.SIGN) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.LEVER) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.WOOD_PLATE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.STONE_PLATE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.IRON_PLATE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.GOLD_PLATE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.REDSTONE_TORCH_ON) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.REDSTONE_TORCH_OFF) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.STONE_BUTTON) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.REDSTONE) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.RAILS) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.ACTIVATOR_RAIL) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.DETECTOR_RAIL) &&
                        !underCuboid.checkBlocks(world, material -> material == Material.POWERED_RAIL)) ||
                (getCarpetAround() != 0 && salmonPlayer.isMathGround()) ||
                (getLilyAround() != 0 && salmonPlayer.isMathGround()) ) {
            salmonPlayer.canJump = true;
            salmonPlayer.fallDistance = 0;
            salmonPlayer.jumping = false;
            salmonPlayer.lastFallDamage = 0;
            salmonPlayer.serverAirTick = 0;
            salmonPlayer.serverGround = true;
        }else {
            salmonPlayer.serverGround = false;
            salmonPlayer.serverAirTick++;
        }

        if( nearBox.checkBlocks(world, material -> material == Material.SLIME_BLOCK) ) {
            salmonPlayer.slimeGround = true;
        }

        salmonPlayer.lastFuzzyServerGround = salmonPlayer.isFuzzyServerGround();

        double g = 0.1875D;
        salmonPlayer.littleFuzzyServerGround = !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.AIR) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.WATER) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.STATIONARY_WATER) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.LAVA) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.STATIONARY_LAVA) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.WEB)/* &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.SAPLING)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.GRASS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.LONG_GRASS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DEAD_BUSH)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.YELLOW_FLOWER)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RED_ROSE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.BROWN_MUSHROOM)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RED_MUSHROOM)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.TORCH)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.WATER_LILY)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.CARPET)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DOUBLE_PLANT)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.PAINTING)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.SIGN)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.LEVER)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.WOOD_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.STONE_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.IRON_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.GOLD_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_ON)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_OFF)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.STONE_BUTTON)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RAILS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.ACTIVATOR_RAIL)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DETECTOR_RAIL)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.POWERED_RAIL))*/;

        g = 0.7875D;
        salmonPlayer.fuzzyServerGround = !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.AIR) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.WATER) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.STATIONARY_WATER) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.LAVA) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.STATIONARY_LAVA) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> material == Material.WEB)/* &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.SAPLING)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.GRASS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.LONG_GRASS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DEAD_BUSH)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.YELLOW_FLOWER)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RED_ROSE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.BROWN_MUSHROOM)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RED_MUSHROOM)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.TORCH)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.WATER_LILY)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.CARPET)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DOUBLE_PLANT)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.PAINTING)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.SIGN)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.LEVER)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.WOOD_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.STONE_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.IRON_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.GOLD_PLATE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_ON)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_OFF)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.STONE_BUTTON)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.REDSTONE)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.RAILS)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.ACTIVATOR_RAIL)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.DETECTOR_RAIL)) &&
                !nearBox.expand(g, 0.0D, g).checkBlocks(world, material -> (material == Material.POWERED_RAIL))*/;

        salmonPlayer.superFuzzyServerGround = !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.AIR) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.WATER) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.STATIONARY_WATER) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.LAVA) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.STATIONARY_LAVA) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> material == Material.WEB)/* &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.SAPLING)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.GRASS)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.LONG_GRASS)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.DEAD_BUSH)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.YELLOW_FLOWER)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.RED_ROSE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.BROWN_MUSHROOM)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.RED_MUSHROOM)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.TORCH)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.WATER_LILY)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.CARPET)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.DOUBLE_PLANT)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.PAINTING)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.SIGN)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.LEVER)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.WOOD_PLATE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.STONE_PLATE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.IRON_PLATE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.GOLD_PLATE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_ON)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.REDSTONE_TORCH_OFF)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.STONE_BUTTON)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.REDSTONE)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.RAILS)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.ACTIVATOR_RAIL)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.DETECTOR_RAIL)) &&
                !nearBox.expand(g, 0.45D, g).checkBlocks(world, material -> (material == Material.POWERED_RAIL))*/;

        salmonPlayer.lastJumpUpwards = isJumpUpwards();

        if( Math.abs(deltaY - (0.41999998688697815 + PlayerUtil.getAmplifier(player, PotionEffectType.JUMP) * 0.1f)) < 0.00001 ) {
            salmonPlayer.jumpUpwards = true;
        } else {
            salmonPlayer.jumpUpwards = false;
        }

        if( Math.abs(deltaY - (0.41999998688697815 + PlayerUtil.getAmplifier(player, PotionEffectType.JUMP) * 0.1f)) < 0.00001 && isLastServerGround() ) {
            salmonPlayer.jumpTick = 10 + PlayerUtil.getAmplifier(player, PotionEffectType.JUMP) * 2;
            salmonPlayer.jumping = true;
            salmonPlayer.canJump = false;
        }

        if ( isInLava() || (isTouchingLava() &&
                (player.getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.LAVA ||
                player.getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_LAVA)) ) {
            salmonPlayer.lavaTick++;
            salmonPlayer.fallDistance = 0;
        } else {
            salmonPlayer.lavaTick = 0;
        }

        if ( isInWater() || (isTouchingWater() && getLilyAround() == 0 &&
                (player.getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.WATER ||
                player.getLocation().add(0.0d, 0.4d, 0.0d).getBlock().getType() == Material.STATIONARY_WATER)) ) {
            salmonPlayer.waterTick++;
            salmonPlayer.fallDistance = 0;
        } else {
            salmonPlayer.waterTick = 0;
        }

        if ( underCuboid.count(world, Material.ICE) > 0 || nearBox.count(world, Material.PACKED_ICE) > 0 ) {
            salmonPlayer.iceTick = 0;
        } else {
            salmonPlayer.iceTick++;
        }

        if ( underCuboid.count(world, Material.SLIME_BLOCK) > 0 ) {
            salmonPlayer.slimeTick = 0;
        } else {
            salmonPlayer.slimeTick++;
        }

        if ( nearBox.count(world, Material.WEB) > 0 || aboveHeadBox.count(world, Material.WEB) > 0 ||
                aboveHeadCuboid.checkBlocks(world, material -> (material == Material.WEB)) ) {
            salmonPlayer.webTick = 0;
            salmonPlayer.fallDistance = 0;
        } else {
            salmonPlayer.webTick++;
        }

        if( isCollidingHorizontally() ) {
            salmonPlayer.collidingHorizontallyTick = 0;
        } else {
            salmonPlayer.collidingHorizontallyTick++;
        }

        if( isCollidingVerticallyUp() ) {
            salmonPlayer.collidingVerticallyUpTick = 0;
        } else {
            salmonPlayer.collidingVerticallyUpTick++;
        }

        if( isOnLadder() ) {
            salmonPlayer.climbTick = 0;
        } else {
            salmonPlayer.climbTick++;
        }

        if( isOnLadder() ) {
            salmonPlayer.fallDistance = 0;
        }

        if( salmonPlayer.lastDeltaY <= 0 ) {
            salmonPlayer.fallDistance += Math.abs(salmonPlayer.lastDeltaY);
        } else {
            salmonPlayer.fallDistance = 0;
        }

        if( getDeltaXZ() != 0 ) {
            salmonPlayer.lastPacketTime = salmonPlayer.getPacketTime();
            salmonPlayer.packetTime = System.currentTimeMillis();
            salmonPlayer.timerSpeed.onTick();
        }

        /* ----- Velocity ----- */
        salmonPlayer.lastVelocityX = salmonPlayer.getVelocityX();
        salmonPlayer.lastVelocityY = salmonPlayer.getVelocityY();
        salmonPlayer.lastVelocityZ = salmonPlayer.getVelocityZ();
        salmonPlayer.lastVelocityH = salmonPlayer.getVelocityH();

        if( salmonPlayer.getVelocityTick() > 0 ) {
            if( salmonPlayer.velocityY > 0 ) {
                salmonPlayer.velocityY = (salmonPlayer.velocityY - 0.08D) * 0.9800000190734863D;
            }else {
                salmonPlayer.velocityY = 0;
            }

        }if( Math.abs(getDeltaY() - 0.42F) < 0.01) {
            salmonPlayer.velocityY = 0;
        }

        if( getDeltaY() < 0.005 ) {
            salmonPlayer.velocityY = 0;
        }

        if(player.hasPotionEffect(PotionEffectType.SPEED)) {
            int amplifier = PlayerUtil.getAmplifier(player, PotionEffectType.SPEED);
            salmonPlayer.velocityH = (salmonPlayer.velocityH * Math.pow(0.9, amplifier)) - 0.01;
        }

        if( !isClientGround() && isLastClientGround() ) {
            salmonPlayer.velocityH = salmonPlayer.velocityH - 0.13;
        } else {
            salmonPlayer.velocityH = salmonPlayer.velocityH * 0.91 - 0.13;
        }

        salmonPlayer.velocityH = Math.max( salmonPlayer.velocityH, 0 );

        double expectedJump = 0.42F + ( PlayerUtil.getAmplifier( player, PotionEffectType.JUMP ) * 0.1F );

        if( getDeltaY() == expectedJump || salmonPlayer.velocityH < 0.005 ) {
            salmonPlayer.velocityH = 0;
        }else if( getAttackTick() == 0 ) {
            salmonPlayer.velocityH *= 0.6F;
        }

        /* ----- Update ----- */
        salmonPlayer.updateLastLocation( newLoc );
        salmonPlayer.lastDeltaX = deltaX;
        salmonPlayer.lastDeltaY = deltaY;
        salmonPlayer.lastDeltaZ = deltaZ;
        salmonPlayer.lastDeltaXZAccel = deltaXZAccel;
        salmonPlayer.lastDeltaYAccel = deltaYAccel;

        salmonPlayer.lastDeltaYaw = deltaYaw;
        salmonPlayer.lastDeltaPitch = deltaPitch;
        salmonPlayer.lastDeltaYawAccel = deltaYawAccel;
        salmonPlayer.lastDeltaPitchAccel = deltaPitchAccel;
    }

    public boolean isBoatAround(){
        for(Entity e : player.getWorld().getEntities()){
            double distance = e.getLocation().distance( player.getLocation() );
            if( distance <= 1.5 && e.getName().toLowerCase().contains( "boat" ) && !(e instanceof Player) ){
                return true;
            }
        }
        return false;
    }

    public boolean isInBlock() {
        Material b = player.getLocation().clone().getBlock().getType();
        return (b != Material.AIR &&
                b != Material.WATER && b != Material.STATIONARY_WATER &&
                b != Material.LAVA && b != Material.STATIONARY_LAVA &&
                b != Material.WEB && b != Material.LADDER && b != Material.VINE);
    }

    public boolean isInLiquid() {
        Material b = player.getEyeLocation().clone().getBlock().getType();
        return (b == Material.WATER || b == Material.STATIONARY_WATER ||
                b == Material.LAVA || b == Material.STATIONARY_LAVA);
    }

    public boolean isInWater() {
        Material b = player.getEyeLocation().clone().getBlock().getType();
        return (b == Material.WATER || b == Material.STATIONARY_WATER);
    }

    public boolean isInLava() {
        Material b = player.getEyeLocation().clone().getBlock().getType();
        return (b == Material.LAVA || b == Material.STATIONARY_LAVA);
    }

    public boolean isOnLadder() {
        Material b = player.getLocation().clone().subtract(0.0D, 0.1D, 0.0D).getBlock().getType();
        return (b == Material.LADDER || b == Material.VINE);
    }

}
