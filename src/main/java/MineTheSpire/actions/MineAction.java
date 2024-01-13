package MineTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import MineTheSpire.cards.EquipmentCards.EquipmentTool;
import MineTheSpire.powers.PickaxePower;
import MineTheSpire.ui.EquipmentSlots;
import MineTheSpire.ui.Inventory;

public class MineAction extends AbstractGameAction {

    private int stone = 0;
    private int iron = 0;
    private int diamond = 0;
    private int durability = 0;
    private EquipmentTool tool = null;

    public MineAction(int stone, int iron, int diamond, EquipmentTool tool, int durability){
        this.stone = stone;
        this.iron = iron;
        this.diamond = diamond;
        this.durability = durability;
        this.tool = tool;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(PickaxePower.POWER_ID)){
            this.stone += EquipmentSlots.getEquipment().stone;
            this.iron += EquipmentSlots.getEquipment().iron;
            this.diamond += EquipmentSlots.getEquipment().diamond;
            EquipmentSlots.useDurability(durability);
        }
        Inventory.addStoneAmount(this.stone);
        Inventory.addIronAmount(this.iron);
        Inventory.addDiamondAmount(this.diamond);
        isDone = true;
    }
    
}