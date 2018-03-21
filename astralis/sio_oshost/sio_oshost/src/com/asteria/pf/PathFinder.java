package com.asteria.pf;

import com.asteria.game.character.CharacterNode;
import com.asteria.game.location.Position;

/**
 *
 */
public interface PathFinder {
    
    public Path findPath(CharacterNode characterNode, Position end, boolean clipped);
}
