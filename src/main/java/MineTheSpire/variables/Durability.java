package MineTheSpire.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import MineTheSpire.cards.AbstractToolCard;
import basemod.abstracts.DynamicVariable;

public class Durability extends DynamicVariable {
    @Override
    public String key() {
        return "MineTheSpire:Dur";
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractToolCard){
            return ((AbstractToolCard)card).baseDurability;
        }
        return 0;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractToolCard){
            return ((AbstractToolCard)card).durability;
        }
        return 0;
    }

    @Override
    public boolean isModified(AbstractCard arg0) {
        return false;
    }

    @Override
    public boolean upgraded(AbstractCard arg0) {
        return false;
    }
}
