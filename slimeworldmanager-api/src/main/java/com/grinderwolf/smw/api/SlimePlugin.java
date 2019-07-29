package com.grinderwolf.smw.api;

import com.grinderwolf.smw.api.exceptions.CorruptedWorldException;
import com.grinderwolf.smw.api.exceptions.NewerFormatException;
import com.grinderwolf.smw.api.exceptions.UnknownWorldException;
import com.grinderwolf.smw.api.exceptions.WorldInUseException;
import com.grinderwolf.smw.api.loaders.SlimeLoader;
import com.grinderwolf.smw.api.world.SlimeWorld;

import java.io.IOException;

/**
 * Main class of the SMW API. From here, you can load
 * worlds and add them to the server's world list, and
 * also add your own implementations of the {@link SlimeLoader}
 * interface, to load and store worlds from other data sources.
 */
public interface SlimePlugin {

    /**
     * Loads a world using a specificied {@link SlimeLoader}.
     * This world can then be added to the server's world
     * list by using the {@link #generateWorld(SlimeWorld)} method.
     *
     * @param loader {@link SlimeLoader} used to retrieve the world.
     * @param worldName Name of the world.
     * @param properties Properties of the world contained within a {@link SlimeWorld.SlimeProperties} object.
     *
     * @return A {@link SlimeWorld}, which is the in-memory representation of the world.
     *
     * @throws UnknownWorldException if the world cannot be found.
     * @throws IOException if the world cannot be obtained from the speficied data source.
     * @throws CorruptedWorldException if the world retrieved cannot be parsed into a {@link SlimeWorld} object.
     * @throws NewerFormatException if the world uses a newer version of the SRF.
     * @throws WorldInUseException if the world is already being used on another server when trying to open it without read-only mode enabled.
     */
    public SlimeWorld loadWorld(SlimeLoader loader, String worldName, SlimeWorld.SlimeProperties properties)
            throws UnknownWorldException, IOException, CorruptedWorldException, NewerFormatException, WorldInUseException;

    /**
     * Generates a Minecraft World from a {@link SlimeWorld} and
     * adds it to the server's world list.
     *
     * @param world {@link SlimeWorld} world to be added to the server's world list
     */
    public void generateWorld(SlimeWorld world);

    /**
     * Returns the {@link SlimeLoader} that is able to
     * read and store worlds from a specified data source.
     *
     * @param dataSource {@link String} containing the data source
     *
     * @return The {@link SlimeLoader} capable of reading and writing to the data source.
     */
    public SlimeLoader getLoader(String dataSource);

    /**
     * Registers a custom {@link SlimeLoader}. This loader can
     * then be used by Slime World Manager to load and store worlds.
     *
     * @param dataSource the data source this loader is capable of reading and writing to.
     * @param loader the {@link SlimeLoader} that is going to be registered.
     */
    public void registerLoader(String dataSource, SlimeLoader loader);
}
