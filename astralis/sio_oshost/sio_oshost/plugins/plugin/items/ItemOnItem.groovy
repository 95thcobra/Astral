package plugin.items

import com.asteria.game.character.player.Player
import com.asteria.game.item.ItemDefinition
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ItemOnItemPlugin

@PluginSignature(ItemOnItemPlugin.class)
final class ItemOnItem implements PluginListener<ItemOnItemPlugin> {

    @Override
    void execute(Player player, ItemOnItemPlugin context) {
        if (context.getItemUsed().id == 5733 && player.getUsername().equalsIgnoreCase("snowman")) {
            player.messages.sendMessage("[Item] (ID:" + context.itemOn.id + ") " + ItemDefinition.DEFINITIONS[context.getItemOn().id].getName() + "  (General_Price: " + ItemDefinition.DEFINITIONS[context.getItemOn().id].getGeneralPrice() + " )")
        }
    }
}
