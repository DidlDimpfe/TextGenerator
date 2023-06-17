package de.philw.textgenerator.utils;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FastBlockUpdate {

    private final JavaPlugin javaPlugin;
    private int blocksPerTick;

    private Map<Location, BlockData> blocks = new LinkedHashMap<>();
    private FastBlockUpdateTask fastBlockUpdateTask = null;

    public FastBlockUpdate(JavaPlugin javaPlugin, int blocksPerTick) {
        this.javaPlugin = javaPlugin;
        this.blocksPerTick = blocksPerTick;
    }

    public int getBlocksPerTick() {
        return blocksPerTick;
    }

    public void setBlocksPerTick(int blocksPerTick) {
        this.blocksPerTick = blocksPerTick;
    }

    public Map<Location, BlockData> getBlocks() {
        return blocks;
    }

    public void setBlocks(Map<Location, BlockData> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Location location, BlockData blockData) {
        getBlocks().put(location, blockData);
    }

    public void run() {
        fastBlockUpdateTask = new FastBlockUpdateTask(getBlocksPerTick(), getBlocks());
        fastBlockUpdateTask.start();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isComplete()) {
                    fastBlockUpdateTask = null;
                }
            }
        }.runTaskTimer(javaPlugin, 10, 10);
    }

    public boolean isRunning(){
        return fastBlockUpdateTask != null;
    }

    public boolean isComplete() {
        if (fastBlockUpdateTask == null) {
            return false;
        } else {
            return fastBlockUpdateTask.isComplete();
        }
    }

    public void cancel() {
        fastBlockUpdateTask.cancel();
    }

    public class FastBlockUpdateTask {

        Map<Location, BlockData> blocksRemaining = new LinkedHashMap<>();
        private final List<BukkitRunnable> tasks = new ArrayList<>();

        private final int blocksPerTicks;

        private boolean complete;

        FastBlockUpdateTask(int blocksPerSecond, Map<Location, BlockData> blocks) {
            this.blocksPerTicks = blocksPerSecond;
            this.blocksRemaining.putAll(blocks);
        }

        public void start() {
            for (int i = 0; i < (blocksRemaining.size() / blocksPerTicks) + 1; i++) {
                tasks.add(new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int blocks = 0; blocks < blocksPerTicks; blocks++) {
                            if (cancelled) {
                                for (BukkitRunnable runnable : tasks) runnable.cancel();
                                return;
                            }
                            if (!blocksRemaining.entrySet().iterator().hasNext()) break;
                            Map.Entry<Location, BlockData> entry = blocksRemaining.entrySet().iterator().next();
                            Location key = entry.getKey();
                            key.getBlock().setBlockData(entry.getValue());
                            blocksRemaining.remove(key);
                        }
                        if (blocksRemaining.size() < 1) {
                            setComplete(true);
                            for (BukkitRunnable runnable : tasks) runnable.cancel();
                        }
                    }
                });
            }
            for (int i = 0; i < tasks.size(); i++) {
                BukkitRunnable runnable = tasks.get(i);
                runnable.runTaskLater(javaPlugin, (i + 1));
            }
        }

        private boolean cancelled = false;

        public void cancel() {
            cancelled = true;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }
    }

}