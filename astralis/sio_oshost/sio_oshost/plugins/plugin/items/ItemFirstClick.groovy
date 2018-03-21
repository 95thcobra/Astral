package plugin.items

import com.asteria.game.character.player.Player
import com.asteria.game.item.impl.TeleportTablet
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ItemFirstClickPlugin

@PluginSignature(ItemFirstClickPlugin.class)
final class ItemFirstClick implements PluginListener<ItemFirstClickPlugin> {

    @Override
    void execute(Player player, ItemFirstClickPlugin context) {
        if (context.getItem().getDefinition().getEquipmentSlot() != -1)
            player.getEquipment().equipItem(context.slot)
        if (TeleportTablet.operate(player, context.item.id)){
            return
        }
        switch (context.item.id) {
            case 4155:
                player.getSlayer().checkTask()
                break
        }
    }
}
