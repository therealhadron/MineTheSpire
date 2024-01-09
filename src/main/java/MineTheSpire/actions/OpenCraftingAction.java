package MineTheSpire.actions;

import java.util.Dictionary;
import java.util.Hashtable;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
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
   private CardGroup cg = new CardGroup(CardGroupType.UNSPECIFIED);

   public OpenCraftingAction() {
      this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
         cg.addToTop(new WoodenPickaxe());
         cg.addToTop(new StonePickaxe());
         cg.addToTop(new IronPickaxe());
         cg.addToTop(new DiamondPickaxe());
         cg.addToTop(new WoodenAxe());
   }

   @Override
   public void update() {
      if (this.duration == this.startDuration) {
         AbstractDungeon.gridSelectScreen.open(cg, 1, "What would you like to craft?", false, false, true, true);
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            Dictionary<String, Integer> recipe = ((EquipmentTool)c).getRecipeCost();
            if (EquipmentTool.hasEnoughResources(((EquipmentTool)c).getRecipeCost())){
               addToBot(new AddCardToDeckAction(c));
               addToBot(new MakeTempCardInHandAction(c, true, true));
               addToBot(new AddToInventoryAction(recipe));
            } else {
               this.addToBot(new TalkAction(true, "Not enough resources!", 1.0F, 2.0F));
            }
         }
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         AbstractDungeon.player.hand.refreshHandLayout();
         this.isDone = true;
      }
   }
}
