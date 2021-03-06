package net.citizensnpcs.api.ai.flocking;

import java.util.Collection;

import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class CohesionBehavior implements FlockBehavior {
    private final double weight;

    public CohesionBehavior(double weight) {
        this.weight = weight;
    }

    @Override
    public Vector getVector(NPC npc, Collection<NPC> nearby) {
        Location dummy = new Location(null, 0, 0, 0);
        Vector positions = new Vector(0, 0, 0);
        for (NPC neighbor : nearby) {
            if (!neighbor.isSpawned())
                continue;
            positions = positions.add(neighbor.getEntity().getLocation(dummy).toVector());
        }
        Vector center = positions.multiply((double) 1 / nearby.size());
        return npc.getEntity().getLocation(dummy).toVector().subtract(center).multiply(weight);
    }

}
