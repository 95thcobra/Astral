package plugin.objects

import plugin.minigames.fightcaves.FightCavesHandler

import com.asteria.game.character.Animation
import com.asteria.game.character.player.Player
import com.asteria.game.character.player.content.Spellbook
import com.asteria.game.character.player.content.ViewingOrb
import com.asteria.game.character.player.skill.Skills
import com.asteria.game.location.Position
import com.asteria.game.object.ObjectNode;
import com.asteria.game.plugin.PluginListener
import com.asteria.game.plugin.PluginSignature
import com.asteria.game.plugin.context.ObjectFirstClickPlugin
import com.asteria.game.object.ObjectNodeManager

@PluginSignature(ObjectFirstClickPlugin.class)
final class ObjectFirstClick implements PluginListener<ObjectFirstClickPlugin> {
    @Override
    void execute(Player player, ObjectFirstClickPlugin context) {
        if (player.rights.DEVELOPER) {
            player.messages.sendMessage "First: " + context.size()
            System.out.println("[Object first click] : " + context.id() + " ~ " + context.position)
        }

        ObjectNode node_obj = null
        if (ObjectNodeManager.getObject(context.getPosition()).isPresent())
            node_obj = ObjectNodeManager.getObject(context.getPosition()).get().copy()

        switch (context.id) {

            case 2644:
                int slot1 = player.getInventory().searchSlot(1779)
                int slot2 = player.getInventory().searchSlot(1737)
                int item = slot1 > slot2 && slot1 != -1 && slot2 != -1 || slot1 == -1 && slot2 != -1 ? 1737 : 1779
                if (slot1 == -1 && slot2 == -1) return
                Crafting.openSpinning(player, item)
                break

            case 9391:
                player.viewingOrb = new ViewingOrb(player, new Position(2398, 5150),
                        new Position(2384, 5157), new Position(2409, 5158), new Position(2388, 5138),
                        new Position(2411, 5137))
                player.viewingOrb.open()
                break
            case 9368:
                if (player.position.y <= 5167 && FightCavesHandler.players.remove(player)) {
                    player.move new Position(2399, 5169)
                    player.combatBuilder.reset()
                    FightCavesHandler.awaiting.add player
                    player.messages.sendMessage "You forfeit the fight pits minigame!"
                    player.messages.sendWalkable 2804
                    FightCavesHandler.display player
                    player.messages.sendContextMenu(1, "null")
                    FightCavesHandler.end false
                }
                break
            case 9369:
                if (FightCavesHandler.awaiting.contains(player)) {
                    FightCavesHandler.awaiting.remove player
                    player.messages.sendMessage "You exit the fight pits minigame waiting room!"
                    player.move new Position(2399, 5177)
                    player.messages.sendWalkable(-1)
                } else if (!FightCavesHandler.awaiting.contains(player)) {
                    int minutes = FightCavesHandler.GAME_CYCLE_MINUTES - FightCavesHandler.gameCounter
                    FightCavesHandler.awaiting.add player
                    player.messages.sendMessage "You enter the fight pits minigame waiting room!"
                    player.move new Position(2399, 5175)
                    player.messages.sendWalkable 2804
                    FightCavesHandler.display player
                    FightCavesHandler.awaiting.each { FightCavesHandler.display it }
                }
                break
            case 3193:
            case 2213:
            case 2213:
            case 2214:
            case 3045:
            case 5276:
            case 6084:
            case 11758:
                player.bank.open()
                break
            case 409:
                int level = player.skills[Skills.PRAYER].realLevel
                if (player.skills[Skills.PRAYER].level < level) {
                    player.animation new Animation(645)
                    player.skills[Skills.PRAYER].setLevel(level, true)
                    player.messages.sendMessage "You recharge your prayer points."
                    Skills.refresh(player, Skills.PRAYER)
                } else {
                    player.messages.sendMessage "You already have full prayer points."
                }
                break
            case 6552:
                if (player.spellbook == Spellbook.ANCIENT) {
                    Spellbook.convert(player, Spellbook.NORMAL)
                } else if (player.spellbook == Spellbook.NORMAL) {
                    Spellbook.convert(player, Spellbook.ANCIENT)
                }
                break
        }
    }
}
