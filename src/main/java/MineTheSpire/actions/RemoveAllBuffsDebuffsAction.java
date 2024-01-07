package MineTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

import java.util.Iterator;

public class RemoveAllBuffsDebuffsAction extends AbstractGameAction {
   private AbstractCreature c;

   public RemoveAllBuffsDebuffsAction(AbstractCreature c) {
      this.c = c;
      this.duration = 0.5F;
   }

   public void update() {
      Iterator<AbstractPower> var1 = this.c.powers.iterator();

      while (var1.hasNext()){
         AbstractPower p = var1.next();
         if (p.type == PowerType.BUFF || p.type == PowerType.DEBUFF){
            this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
         }
      }

      isDone = true;
   }
}
