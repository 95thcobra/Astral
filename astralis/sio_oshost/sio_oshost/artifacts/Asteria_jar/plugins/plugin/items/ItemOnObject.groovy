package plugin.items

import com.asteria.game.character.player.Player
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ItemOnObjectPlugin

@PluginSignature(ItemOnObjectPlugin.class)
final class ItemOnObject implements PluginListener<ItemOnObjectPlugin> {

    @Override
    void execute(Player player, ItemOnObjectPlugin context) {
        switch (context.id) {

        }
    }
}
