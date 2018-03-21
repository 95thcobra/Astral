package plugin.npcs

import com.asteria.game.character.player.Player
import com.asteria.game.location.Position;
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.NpcSecondClickPlugin
import com.asteria.game.shop.Shop

@PluginSignature(NpcSecondClickPlugin.class)
final class NpcSecondClick implements PluginListener<NpcSecondClickPlugin> {

    @Override
    void execute(Player player, NpcSecondClickPlugin context) {
        Position position = context.npc.position.copy()
        switch (context.npc.id) {
			case 520:
			Shop.SHOPS.get("General Store").openShop player
			break
        }
    }
}