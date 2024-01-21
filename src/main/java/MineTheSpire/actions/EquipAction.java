package MineTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import MineTheSpire.cards.AbstractToolCard;
import MineTheSpire.ui.EquipmentSlots;

public class EquipAction extends AbstractGameAction {
    private AbstractToolCard tool = null;

    public EquipAction(AbstractToolCard tool){
        this.tool = tool;
    }

    @Override
    public void update() {
        EquipmentSlots.equipTool(this.tool);
        isDone = true;
    }
    
}