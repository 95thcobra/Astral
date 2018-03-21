package com.asteria.utility;

import com.asteria.game.location.Position;
import com.asteria.utility.clip.Rangable;
import com.asteria.utility.clip.Region;

public class ClipUtility {

	public static boolean checkClip(int x, int y, int x2, int y2, int height, boolean melee) {
		if (melee) {
			return Region.canMove(x, y, x2, y2, height, 1, 1);
		} else {
			return Rangable.canMove(x, y, x2, y2, height, 1, 1);
		}
	}
	
	public static boolean checkClip(Position start, Position end, boolean melee) {
		int x = start.getX();
		int y = start.getY();
		int x2 = end.getX();
		int y2 = end.getY();
		return checkClip(x, y, x2, y2, start.getZ(), melee);
	}
	
}
