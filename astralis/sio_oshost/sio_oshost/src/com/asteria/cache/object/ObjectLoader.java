package com.asteria.cache.object;

import java.io.IOException;
import java.util.logging.Logger;

import com.asteria.cache.Cache;
import com.asteria.cache.InvalidCacheException;
import com.asteria.cache.index.impl.MapIndex;
import com.asteria.cache.index.impl.StandardIndex;
import com.asteria.cache.map.LandscapeListener;
import com.asteria.cache.map.LandscapeParser;
import com.asteria.cache.obj.ObjectDefinitionListener;
import com.asteria.cache.region.Regions;
import com.asteria.game.location.Position;
import com.asteria.game.World;

/**
 * Manages all of the in-game objects.
 *
 * @author Graham Edgecombe
 */
public class ObjectLoader implements LandscapeListener, ObjectDefinitionListener {

    /**
     * Logger instance.
     */
    private static final Logger logger = Logger.getLogger(ObjectLoader.class.getName());

    /**
     * The number of definitions loaded.
     */
    private int definitionCount = 0;

    /**
     * The count of objects loaded.
     */
    private int objectCount = 0;

    /**
     * Loads the objects in the map.
     *
     * @throws java.io.IOException                 if an I/O error occurs.
     * @throws com.rs2.cache.InvalidCacheException if the cache is invalid.
     */
    public void load() throws IOException, InvalidCacheException {
        Cache cache = Cache.getSingleton();
        try {
            logger.info("Loading definitions...");
            @SuppressWarnings("unused")
            StandardIndex[] defIndices = cache.getIndexTable().getObjectDefinitionIndices();
            //new ObjectDefinitionParser(cache, defIndices, this).parse();
            //logger.info("Loaded " + definitionCount + " object definitions.");
            MapIndex[] mapIndices = cache.getIndexTable().getMapIndices();
            for (MapIndex index : mapIndices) {
                new LandscapeParser(cache, index.getIdentifier(), this).parse();
            }
            logger.info("Loaded " + mapIndices.length + " map files.");
            logger.info("Loaded " + objectCount + " objects.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cache.close();
        }

    }

    @Override
    public void objectParsed(CacheObject obj) {
        objectCount++;
        World.getRegionManager().getRegionByLocation(obj.getLocation()).getGameObjects().add(obj);
    }

    @Override
    public void objectDefinitionParsed(GameObjectData def) {
        definitionCount++;
        GameObjectData.addDefinition(def);
    }

    public static CacheObject object(int x, int y, int z) {
        final Position loc = new Position(x, y, z);
        Regions r = World.getRegionManager().getRegionByLocation(loc);
        for (CacheObject go : r.getGameObjects()) {
            if (go.getLocation().equals(loc)) {
                return go;
            }
        }
        return null;
    }

    public static CacheObject object(int id, int x, int y, int z) {
        final Position loc = new Position(x, y, z);
        Regions r = World.getRegionManager().getRegionByLocation(loc);
        for (CacheObject go : r.getGameObjects()) {
            if (go.getDef().getId() == id && go.getLocation().equals(loc)) {
                return go;
            }
        }
        return null;
    }

    public static CacheObject object(String name, int x, int y, int z) {
        final Position loc = new Position(x, y, z);
        Regions r = World.getRegionManager().getRegionByLocation(loc);
        for (CacheObject go : r.getGameObjects()) {
            final String objectName = GameObjectData.forId(go.getDef().getId()) != null ? GameObjectData.forId(go.getDef().getId()).getName().toLowerCase() : "";
            if (objectName.contains(name.toLowerCase()) && go.getLocation().equals(loc)) {
                return go;
            }
        }
        return null;
    }

}
