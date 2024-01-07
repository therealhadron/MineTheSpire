package MineTheSpire.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import MineTheSpire.actions.OpenCraftingAction;
import MineTheSpire.cards.EquipmentCards.WoodenPickaxe;
import MineTheSpire.cards.SkillCards.Mine;
import MineTheSpire.character.Minecrafter;
import basemod.abstracts.CustomSavable;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

// import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

public class Inventory implements CustomSavable<Map<String, Integer>>{

    private static Map<String, Integer> playerInventory = new HashMap<>();
    private Texture inventory = null;

    private Hitbox hitbox;
    private boolean init = false;

    CardGroup cg = new CardGroup(CardGroupType.UNSPECIFIED);

    public Inventory(){
        loadImage();
        reset_inventory();
        hitbox = new Hitbox(600, 200);
        cg.addToTop(new WoodenPickaxe());
        cg.addToTop(new Mine());
    }

    public void loadImage(){
        inventory = new Texture("mineTheSpireMod/images/ui/Inventory.png");
    }

    public void update(){
        if (!init){
            hitbox.move(300, 800);
            init = true;
        }
        hitbox.update();

        if(InputHelper.justClickedLeft && hitbox.hovered){
            AbstractDungeon.actionManager.addToTop(new OpenCraftingAction());
        }
    }

    public void render(SpriteBatch sb){
        if (AbstractDungeon.getCurrMapNode() != null
            && AbstractDungeon.getCurrRoom() != null
            && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
            && AbstractDungeon.player.chosenClass == Minecrafter.Enums.MINECRAFTER // Temp need to fix for prismatic shard
            && !AbstractDungeon.player.isDead
        ){
            sb.setColor(Color.WHITE);
            sb.draw(inventory, 300, 800);

            if (hitbox.hovered){
                TipHelper.renderGenericTip((float) InputHelper.mX + 50.0F * Settings.scale, (float) InputHelper.mY, "This is a Tooltip", "Wow such tooltips");
            }

            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Woods: " + getWoodAmount(), 300, 800, Color.CYAN);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Stones: " + getStoneAmount(), 500, 800, Color.CYAN);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Irons: " + getIronAmount(), 700, 800, Color.CYAN);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "Diamonds: " + getDiamondAmount(), 900, 800, Color.CYAN);
            hitbox.render(sb);
        }
    }

    public static void reset_inventory(){
        playerInventory.put("wood", 0);
        playerInventory.put("stone", 0);
        playerInventory.put("iron", 0);
        playerInventory.put("diamond", 0);
    }

    public static int getWoodAmount(){
        return playerInventory.get("wood");
    }

    public static int getStoneAmount(){
        return playerInventory.get("stone");
    }
    
    public static int getIronAmount(){
        return playerInventory.get("iron");
    }

    public static int getDiamondAmount(){
        return playerInventory.get("diamond");
    }

    public static void addWoodAmount(int n){
        playerInventory.put("wood", getWoodAmount() + n);
    }

    public static void addStoneAmount(int n){
        playerInventory.put("stone", getStoneAmount() + n);
    }

    public static void addIronAmount(int n){
        playerInventory.put("iron", getIronAmount() + n);
    }

    public static void addDiamondAmount(int n){
        playerInventory.put("diamond", getDiamondAmount() + n);
    }

    public static void useWoodAmount(int n){
        playerInventory.put("wood", getWoodAmount() - n);
    }

    public static void useStoneAmount(int n){
        playerInventory.put("stone", getStoneAmount() - n);
    }

    public static void useIronAmount(int n){
        playerInventory.put("iron", getIronAmount() - n);
    }

    public static void useDiamondAmount(int n){
        playerInventory.put("diamond", getDiamondAmount() - n);
    }

    @Override
    public void onLoad(Map<String, Integer> dictionary) {
        playerInventory = dictionary;
    }

    @Override
    public Map<String, Integer> onSave() {
        return playerInventory;
    }
}