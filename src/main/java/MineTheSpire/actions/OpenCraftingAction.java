package MineTheSpire.actions;

import java.util.Dictionary;
import java.util.Hashtable;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import MineTheSpire.cards.EquipmentCards.DiamondPickaxe;
import MineTheSpire.cards.EquipmentCards.EquipmentTool;
import MineTheSpire.cards.EquipmentCards.IronPickaxe;
import MineTheSpire.cards.EquipmentCards.StonePickaxe;
import MineTheSpire.cards.EquipmentCards.WoodenAxe;
import MineTheSpire.cards.EquipmentCards.WoodenPickaxe;
import MineTheSpire.ui.Inventory;

public class OpenCraftingAction extends AbstractGameAction {
   private AbstractCard c;

   public OpenCraftingAction() {
      this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
   }

   @Override
   public void update() {
      if (this.duration == this.startDuration) {
         CardGroup cg = new CardGroup(CardGroupType.UNSPECIFIED);
         cg.addToTop(new WoodenPickaxe());
         cg.addToTop(new StonePickaxe());
         cg.addToTop(new IronPickaxe());
         cg.addToTop(new DiamondPickaxe());
         cg.addToTop(new WoodenAxe());
         AbstractDungeon.gridSelectScreen.open(cg, 1, "What would you like to craft?", false, false, true, true);
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            if (hasEnoughResources(((EquipmentTool)c).getRecipeCost())){
               addToBot(new AddCardToDeckAction(c));
               addToBot(new MakeTempCardInHandAction(c, true, true));
            } else {
               System.out.println("NOT ENOUGH RESOURCES");
            }
         }
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         AbstractDungeon.player.hand.refreshHandLayout();
         this.isDone = true;
      }
   }

   private boolean hasEnoughResources(Dictionary<String, Integer> recipe){
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
      Inventory.useWoodAmount(woodCost);
      Inventory.useStoneAmount(stoneCost);
      Inventory.useIronAmount(ironCost);
      Inventory.useDiamondAmount(diamondCost);
      return true;
   }
}
