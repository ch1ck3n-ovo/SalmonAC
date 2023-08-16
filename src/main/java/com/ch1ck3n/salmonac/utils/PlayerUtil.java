package com.ch1ck3n.salmonac.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PlayerUtil {
    public static float[] getAngle(Entity entity, Player damager) {
        float yaw, pitch;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        double d0 = entityPos.getX() - damagerPos.getX();
        double d1 = entityPos.getY() - damagerPos.getY();
        double d2 = entityPos.getZ() - damagerPos.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
        yaw = f;
        pitch = f1;
        return new float[] {yaw, pitch};
    }
    public static float[] getYawMinAndMax(Entity entity, Player damager) {
        float minYaw = 360, maxYaw = 0;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        float[] offset = {-0.5f, 0.5f};
        float[] yoffset = {0.0f, 1.8f};
        for(float x : offset) {
            for(float z : offset) {
                double d0 = entityPos.getX() - damagerPos.getX() + x;
                double d2 = entityPos.getZ() - damagerPos.getZ() + z;
                double d3 = (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                double d4 = d3 % 360 + (d3 < 0 ? 360 : 0);
                minYaw = Math.min((float) d4, minYaw);
                maxYaw = Math.max((float) d4, maxYaw);
            }
        }
        return new float[] {minYaw, maxYaw};
    }
    public static float[] getPitchMinAndMax(Entity entity, Player damager) {
        float minPitch = 360, maxPitch = 0;
        Location damagerPos = damager.getEyeLocation();
        Location entityPos = entity.getLocation();
        float[] offset = {-0.5f, 0.5f};
        float[] yoffset = {-0.125f, 1.95f};
        for(float x : offset) {
            for(float y : yoffset) {
                double d0 = entityPos.getX() - damagerPos.getX() + x;
                double d1 = entityPos.getY() - damagerPos.getY() + y;
                double d2 = entityPos.getZ() - damagerPos.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d4 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
                minPitch = Math.min((float) d4, minPitch);
                maxPitch = Math.max((float) d4, maxPitch);
            }
        }
        for(float z : offset) {
            for(float y : yoffset) {
                double d0 = entityPos.getX() - damagerPos.getX();
                double d1 = entityPos.getY() - damagerPos.getY() + y;
                double d2 = entityPos.getZ() - damagerPos.getZ() + z;
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d4 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
                minPitch = Math.min((float) d4, minPitch);
                maxPitch = Math.max((float) d4, maxPitch);
            }
        }
        return new float[] {minPitch, maxPitch};
    }
    public static int getAmplifier(Player player, PotionEffectType effectType) {
        if(!player.hasPotionEffect(effectType)) {
            return 0;
        }
        for(PotionEffect effect : player.getActivePotionEffects()) {
            if(effect.getType().getName().equals(effectType.getName())) {
                return effect.getAmplifier() + 1;
            }
        }
        return 0;
    }

    public static int getEnchantmentLevel(Player player, Enchantment enchantment) {
        if( !player.getItemInHand().getEnchantments().containsKey(enchantment) ) {
            return 0;
        }
        return player.getItemInHand().getEnchantmentLevel(enchantment);
    }

    public static HashMap<Material, Integer> weaponDamageList = new HashMap<>();

    public static int getWeaponDamage(Material material) {
        if( !weaponDamageList.containsKey(material) ) return 1;
        return weaponDamageList.get(material) + 1;
    }

    public static void weaponDamageListSetup() {
        weaponDamageList.put(Material.WOOD_SWORD, 4);
        weaponDamageList.put(Material.STONE_SWORD, 5);
        weaponDamageList.put(Material.IRON_SWORD, 6);
        weaponDamageList.put(Material.GOLD_SWORD, 4);
        weaponDamageList.put(Material.DIAMOND_SWORD, 7);

        weaponDamageList.put(Material.WOOD_AXE, 3);
        weaponDamageList.put(Material.STONE_AXE, 4);
        weaponDamageList.put(Material.IRON_AXE, 5);
        weaponDamageList.put(Material.GOLD_AXE, 3);
        weaponDamageList.put(Material.DIAMOND_AXE, 6);

        weaponDamageList.put(Material.WOOD_PICKAXE, 2);
        weaponDamageList.put(Material.STONE_PICKAXE, 3);
        weaponDamageList.put(Material.IRON_PICKAXE, 4);
        weaponDamageList.put(Material.GOLD_PICKAXE, 2);
        weaponDamageList.put(Material.DIAMOND_PICKAXE, 5);

        weaponDamageList.put(Material.WOOD_SPADE, 1);
        weaponDamageList.put(Material.STONE_SPADE, 2);
        weaponDamageList.put(Material.IRON_SPADE, 3);
        weaponDamageList.put(Material.GOLD_SPADE, 1);
        weaponDamageList.put(Material.DIAMOND_SPADE, 4);
    }

    /*public static boolean isMovingBackward(double deltaX, double deltaZ, float yaw) {
        return deltaX > 0D && deltaZ < 0D && yaw > 0F && yaw < 90F
                || deltaX > 0D && deltaZ > 0D && yaw > 90F && yaw < 180F
                || deltaX < 0D && deltaZ > 0D && yaw > 180F && yaw < 270F
                || deltaX < 0D && deltaZ < 0D && yaw > 270F && yaw < 360F;
    }*/
}
