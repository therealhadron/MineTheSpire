package MineTheSpire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import MineTheSpire.cards.AbstractToolCard;
import MineTheSpire.powers.AxePower;
import MineTheSpire.ui.EquipmentSlots;
import MineTheSpire.ui.Inventory;

public class ChopAction extends AbstractGameAction {

    private int wood = 0;
    private int durability = 0;
    private AbstractToolCard tool = null;
    // private int vulnerable;

    public ChopAction(int wood, AbstractToolCard tool, int durability){
        this.wood = wood;
        this.durability = durability;
        this.tool = tool;
        // this.vulnerable = vulnerable;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(AxePower.POWER_ID)){
            this.wood += EquipmentSlots.getEquipment().wood;
            EquipmentSlots.useDurability(durability);
        }
        Inventory.addWoodAmount(this.wood);
        isDone = true;
    }
    
}