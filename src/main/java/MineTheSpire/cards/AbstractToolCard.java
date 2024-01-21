package MineTheSpire.cards;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MineTheSpire.ui.EquipmentSlots;
import MineTheSpire.ui.Inventory;
import MineTheSpire.util.CardStats;

public abstract class AbstractToolCard extends BaseCard {

    public AbstractToolCard(String ID, CardStats info) {
        super(ID, info);
    }

    public AbstractToolCard(String ID, CardStats info, int baseWood, int baseStone, int baseIron, int baseDiamond, int baseDurability) {
        super(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
        setCustomVar("Wo", baseWood);
        setCustomVar("St", baseStone);
        setCustomVar("Ir", baseIron);
        setCustomVar("Di", baseDiamond);
    }

    public void setDurability(int durability) {
        Iterator<AbstractCard> var1 = AbstractDungeon.player.masterDeck.group.iterator();

        AbstractCard c;
        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c.uuid.equals(this.uuid)) {
                ((AbstractToolCard)c).baseDurability = durability;
                break;
            }
        }
        this.baseDurability = durability;
    }

    public Dictionary<String, Integer> getRecipeCost() {
        Dictionary<String, Integer> recipe = new Hashtable<>();
        recipe.put("wood", woodCost);
        recipe.put("stone", stoneCost);
        recipe.put("iron", ironCost);
        recipe.put("diamond", diamondCost);
        return recipe;
    }

    public static boolean hasEnoughResources(Dictionary<String, Integer> recipe) {
        int woodCost = recipe.get("wood");
        int stoneCost = recipe.get("stone");
        int ironCost = recipe.get("iron");
        int diamondCost = recipe.get("diamond");
        if (woodCost > Inventory.getWoodAmount()) {
            return false;
        }
        if (stoneCost > Inventory.getStoneAmount()) {
            return false;
        }
        if (ironCost > Inventory.getIronAmount()) {
            return false;
        }
        if (diamondCost > Inventory.getDiamondAmount()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else if (EquipmentSlots.getEquipment() != null && EnergyPanel.getCurrentEnergy() == 0) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return canUse;
        }
    }
}