package MineTheSpire.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MineTheSpire.cards.AbstractToolCard;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.powers.AxePower;
import MineTheSpire.powers.PickaxePower;
import MineTheSpire.powers.SwordPower;

import java.util.Iterator;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class EquipmentSlots{
    private Texture equipmentSlot = null;

    private Hitbox hitbox;
    private boolean init = false;

    private static String toolName = "None";
    private static int toolDurability = 0;

    private static AbstractToolCard toolCard;

    public EquipmentSlots(){
        loadImage();
        hitbox = new Hitbox(118, 120);
    }

    public void loadImage(){
        equipmentSlot = new Texture("mineTheSpireMod/images/ui/EquipmentSlot1.png");
    }

    public void update(){
        if (!init){
            hitbox.move(259, 360);
            init = true;
        }

        hitbox.update();

        if(InputHelper.justClickedLeft && hitbox.hovered && toolName != "None" && !AbstractDungeon.actionManager.hasControl && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            unequipTool();
        }
    }

    public void render(SpriteBatch sb){
        if (AbstractDungeon.getCurrMapNode() != null
            && AbstractDungeon.getCurrRoom() != null
            && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
            && AbstractDungeon.player.chosenClass == Minecrafter.Enums.MINECRAFTER
            && !AbstractDungeon.player.isDead
        ){
            sb.setColor(Color.WHITE);
            sb.draw(equipmentSlot, 200, 300);

            if (hitbox.hovered){
                TipHelper.renderGenericTip((float) InputHelper.mX + 50.0F * Settings.scale, (float) InputHelper.mY, "This is a Tooltip", "I'm the equipment slot!");
            }

            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Equipment: " + toolName, 259, 360, Color.GOLD);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Durability: " + toolDurability, 259, 260, Color.GOLD);
            hitbox.render(sb);
        }
    }

    public static void equipTool(AbstractToolCard c){
        if (toolCard !=null && AbstractDungeon.player.energy.energy >= 1){
            unequipTool();
        }
        toolCard = c;
        toolName = toolCard.cardID;
        toolDurability = toolCard.baseDurability;
        toolCard.setDurability(toolDurability);
    }

    public static void unequipTool(){
        AbstractPlayer p = AbstractDungeon.player;

        if (EnergyPanel.getCurrentEnergy() == 0){
            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Not enough energy to unequip!", 1.0F, 2.0F));
        } else {
            p.energy.use(1);
            if (p.hand.group.size() == 10){
                p.createHandIsFullDialog();
                p.discardPile.moveToDiscardPile(toolCard);
            } else {
                p.hand.moveToHand(toolCard);
            }
            toolCard.setDurability(toolDurability);
            resetEquipmentSlots();
        }
    }

    public static void useDurability(int durabilityChange){
        if (toolName != "None"){
            toolDurability = toolDurability + durabilityChange;
            toolCard.setDurability(toolDurability);
            if (toolDurability == 0){
                removeCardFromDeck(toolCard);
                resetEquipmentSlots();
            }
        }
    }

    public static void removeCardFromDeck(AbstractCard c){
        Iterator<AbstractCard> var1 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var1.hasNext()){
            c = (AbstractCard)var1.next();
            if (c.uuid.equals(toolCard.uuid)){
                AbstractDungeon.player.masterDeck.removeCard(c);
                break;
            }
        }
    }

    public static void resetEquipmentSlots(){
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, PickaxePower.POWER_ID));
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, SwordPower.POWER_ID));
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, AxePower.POWER_ID));
        toolCard = null;
        toolDurability = 0;
        toolName = "None";
    }

    public static AbstractToolCard getEquipment(){
        return toolCard;
    }

    public static int getToolDurability(){
        return toolDurability;
    }

    // @Override
    // public void onLoad(Map<String, Integer> dictionary) {
    //     playerEquipmentSlots = dictionary;
    // }

    // @Override
    // public Map<String, Integer> onSave() {
    //     return playerEquipmentSlots;
    // }
}