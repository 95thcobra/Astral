package plugin.items

import com.asteria.game.character.player.Player
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ItemFirstClickPlugin

@PluginSignature(ItemFirstClickPlugin.class)
final class ItemFirstClick implements PluginListener<ItemFirstClickPlugin> {

    @Override
    void execute(Player player, ItemFirstClickPlugin context) {
		
		if(Herblore.clean_herb(player, context.getItem().getId(), context.getSlot()))
			return;
		
		if (context.getItem().getDefinition().getEquipmentSlot() != -1)
		player.getEquipment().equipItem(context.slot);
			
        switch (context.item.id) {
			case 4155:
			player.getSlayer().checkTask()
			break
        }
    }
}
