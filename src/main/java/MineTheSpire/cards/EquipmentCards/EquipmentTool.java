package MineTheSpire.cards.EquipmentCards;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.ui.Inventory;
import MineTheSpire.util.CardStats;

public abstract class EquipmentTool extends BaseCard{

    public EquipmentTool(String ID, CardStats info) {
        super(ID, info);
    }

    public EquipmentTool(String ID, CardStats info, int baseWood, int baseStone, int baseIron, int baseDiamond, int baseDurability) {
        super(ID, info, baseWood, baseStone, baseIron, baseDiamond, baseDurability);
    }

    public void setDurability(int durability){
        Iterator<AbstractCard> var1 = AbstractDungeon.player.masterDeck.group.iterator();

        AbstractCard c;
        while(var1.hasNext()){
            c = (AbstractCard)var1.next();
            if (c.uuid.equals(this.uuid)){
                c.baseMagicNumber = durability;
            }
        }

        this.baseMagicNumber = durability;
        this.initializeDescription();
    }

    public Dictionary<String, Integer> getRecipeCost(){
        Dictionary<String, Integer> recipe = new Hashtable<>();
        recipe.put("woodCost", woodCost);
        recipe.put("stoneCost", stoneCost);
        recipe.put("ironCost", ironCost);
        recipe.put("diamondCost", diamondCost);
        return recipe;
    }

    public static boolean hasEnoughResources(Dictionary<String, Integer> recipe){
      int woodCost = recipe.get("woodCost");
      int stoneCost = recipe.get("stoneCost");
      int ironCost = recipe.get("ironCost");
      int diamondCost = recipe.get("diamondCost");
      if (woodCost > Inventory.getWoodAmount()){
         return false;
      }
      if (stoneCost > Inventory.getStoneAmount()){
         return false;
      }
      if (ironCost > Inventory.getIronAmount()){
         return false;
      }
      if (diamondCost > Inventory.getDiamondAmount()){
         return false;
      }
      return true;
   }
}

