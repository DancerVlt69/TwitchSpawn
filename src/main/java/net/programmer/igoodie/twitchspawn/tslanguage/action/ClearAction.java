package net.programmer.igoodie.twitchspawn.tslanguage.action;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.programmer.igoodie.twitchspawn.tslanguage.EventArguments;
import net.programmer.igoodie.twitchspawn.tslanguage.parser.TSLParser;
import net.programmer.igoodie.twitchspawn.tslanguage.parser.TSLSyntaxError;

import java.util.List;

public class ClearAction extends ItemSelectiveAction {

    public ClearAction(List<String> words) throws TSLSyntaxError {
        this.message = TSLParser.parseMessage(words);
        List<String> actionWords = actionPart(words);

        long countFrom = actionWords.stream()
                .filter(word -> word.equalsIgnoreCase("FROM")).count();

        if (countFrom == 0) parseSingleWord(actionWords);
        else if (countFrom == 1) parseFrom(actionWords);
        else throw new TSLSyntaxError("At most 1 FROM statement expected, found %d instead.", countFrom);
    }

    @Override
    protected void performAction(ServerPlayerEntity player, EventArguments args) {
        if (selectionType == SelectionType.WITH_INDEX) {
            getInventory(player, inventoryType).set(inventoryIndex, ItemStack.EMPTY);

        } else if (selectionType == SelectionType.EVERYTHING) {
            player.inventory.clear();

        } else if (selectionType == SelectionType.RANDOM) {
            InventorySlot randomSlot = randomInventorySlot(player);
            if (randomSlot != null) {
                randomSlot.pullOut();
            }

        } else if (selectionType == SelectionType.ONLY_HELD_ITEM) {
            int selectedHotbarIndex = player.inventory.currentItem;
            player.inventory.mainInventory.set(selectedHotbarIndex, ItemStack.EMPTY);
        }

        CommandSource commandSource = player.getCommandSource()
                .withPermissionLevel(9999).withFeedbackDisabled();
        player.getServer().getCommandManager().handleCommand(commandSource,
                "/playsound minecraft:entity.item.break master @p");
        player.getServer().getCommandManager().handleCommand(commandSource,
                "/particle minecraft:smoke ~ ~ ~ 2 2 2 0.1 400");
    }

}