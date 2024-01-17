package MineTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import MineTheSpire.cards.EquipmentCards.EquipmentTool;
import MineTheSpire.ui.EquipmentSlots;

public class EquipAction extends AbstractGameAction {
    private EquipmentTool tool = null;

    public EquipAction(EquipmentTool tool){
        this.tool = tool;
    }

    @Override
    public void update() {
        EquipmentSlots.equipTool(this.tool);
        isDone = true;
    }
    
}