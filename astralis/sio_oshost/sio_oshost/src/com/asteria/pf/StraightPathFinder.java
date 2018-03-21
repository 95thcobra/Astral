package com.asteria.pf;

import com.asteria.game.character.CharacterNode;
import com.asteria.game.location.Position;
import com.asteria.utility.ClipUtility;

/**
 *
 */
public class StraightPathFinder implements PathFinder {

    @Override
    public Path findPath(CharacterNode characterNode, Position end, boolean clipped) {
        Path p = new Path();
        Position current = characterNode.getPosition().clone();
        Position last = current.clone();
        int w = end.getX() - current.getX();
        int h = end.getY() - current.getY();
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0)
            dx1 = -1;
        else if (w > 0)
            dx1 = 1;
        if (h < 0)
            dy1 = -1;
        else if (h > 0)
            dy1 = 1;
        if (w < 0)
            dx2 = -1;
        else if (w > 0)
            dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0)
                dy2 = -1;
            else if (h > 0)
                dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            numerator += shortest;
            if (!last.equals(current) && clipped && !ClipUtility.checkClip(current, last, true))
                return p;
            last = current.clone();
            p.getPoints().add(new Point(current.getX(), current.getY()));
            if (!(numerator < longest)) {
                numerator -= longest;
                current.setX(current.getX() + dx1);
                current.setY(current.getY() + dy1);
            } else {
                current.setX(current.getX() + dx2);
                current.setY(current.getY() + dy2);
            }
        }
        return p;
    }
}
