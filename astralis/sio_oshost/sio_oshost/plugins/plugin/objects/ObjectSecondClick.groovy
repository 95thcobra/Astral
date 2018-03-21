package plugin.objects

import com.asteria.game.character.player.Player
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ObjectSecondClickPlugin
import com.asteria.game.object.ObjectNodeManager
import com.asteria.game.object.ObjectNode;

@PluginSignature(ObjectSecondClickPlugin.class)
final class ObjectSecondClick implements PluginListener<ObjectSecondClickPlugin> {

    @Override
    void execute(Player player, ObjectSecondClickPlugin context) {
        ObjectNode node_obj = null
        if (ObjectNodeManager.getObject(context.getPosition()).isPresent())
            node_obj = ObjectNodeManager.getObject(context.getPosition()).get().copy()
            player.messages.sendMessage("Unhandled Object : " + node_obj.getId())
    }

}

