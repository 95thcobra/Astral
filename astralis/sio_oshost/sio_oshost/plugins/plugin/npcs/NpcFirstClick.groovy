package plugin.npcs

import com.asteria.game.character.player.Rights
import plugin.minigames.fightcaves.FightCavesHandler

import com.asteria.game.character.player.Player
import com.asteria.game.character.player.dialogue.*
import com.asteria.game.item.Item
import com.asteria.game.location.Position
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.NpcFirstClickPlugin

@PluginSignature(NpcFirstClickPlugin.class)
final class NpcFirstClick implements PluginListener<NpcFirstClickPlugin> {

    @Override
    void execute(Player player, NpcFirstClickPlugin context) {
        int id = context.npc.id
        Position position = context.npc.position.copy()
        if (position == player.position) {
            // TODO Get a good position instead of cheap haxing this
            player.movementQueue.follow(context.npc)
            player.faceCharacter(context.npc)
        }
        if (player.getRights().equals(Rights.DEVELOPER)) {
            player.messages.sendMessage("Npc click id : " + id + " Position : " + position)
        }
        switch (id) {
            case 5314:
                player.dialogueChain.append new NpcDialogue(id, "Would you like some money?")
                player.getDialogueChain().append(new OptionDialogue({
                    t ->
                        if (t == OptionType.FIRST_OPTION) {
                            player.dialogueChain.append new GiveItemDialogue(new Item(995, 1000),
                                    "You receive 1000 gold coins.")
                            player.dialogueChain.advance()
                        } else if (t == OptionType.SECOND_OPTION) {
                            player.messages.sendCloseWindows()
                        }
                }, "Yes please!", "No thanks.")); player.dialogueChain.advance()
                player.dialogueChain.advance()
                player.messages.sendMessage("Wizard click 1")
                break
            case 3444:
                player.dialogueChain.append new NpcDialogue(id, "Hello, this is the new dialogue system. It",
                        "should be easier to use.")
                player.dialogueChain.append new PlayerDialogue("Cool, I'm loving it already!")
                player.dialogueChain.append new NpcDialogue(id, "Would you like some money?")
                player.dialogueChain.advance()
                break
            case 34424:
                player.dialogueChain.append new NpcDialogue(id, "Greetings, would you like to be taken to the", "fight pits minigame?")
                player.getDialogueChain().append(new OptionDialogue({
                    t ->
                        if (t == OptionType.FIRST_OPTION) {
                            player.move FightCavesHandler.DEATH_POSITION
                        } else if (t == OptionType.SECOND_OPTION) {
                            player.messages.sendCloseWindows()
                        }
                }, "Yes, take me!", "I'd rather not."));
                player.dialogueChain.advance()
                break
            default:
                player.dialogueChain.append new NpcDialogue(id, "Great day to be out! Right?")
                player.dialogueChain.append new PlayerDialogue("Yeah totally.")
                break
        }
    }
}
