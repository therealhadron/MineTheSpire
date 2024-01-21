package MineTheSpire.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import MineTheSpire.powers.AxePower;
import MineTheSpire.powers.PickaxePower;
import MineTheSpire.ui.EquipmentSlots;
import MineTheSpire.util.CardStats;

public abstract class AbstractMiningCard extends BaseCard{
    public AbstractMiningCard(String ID, CardStats info, int w, int s, int i, int d){
        super(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor);
        setCustomVar("Wo", w);
        setCustomVar("St", s);
        setCustomVar("Ir", i);
        setCustomVar("Di", d);

        wood = w;
        stone = s;
        iron = i;
        diamond = d;

        setVarCalculation("St", (m, baseStone)->{
            AbstractPower power = AbstractDungeon.player.getPower(PickaxePower.POWER_ID);
            if (EquipmentSlots.getEquipment() != null){
                if (power != null){
                    stone = baseStone + EquipmentSlots.getEquipment().stone;
                    return stone;
                }
            }
            stone = baseStone;
            return stone;
        });
        setVarCalculation("Ir", (m, baseIron)->{
            AbstractPower power = AbstractDungeon.player.getPower(PickaxePower.POWER_ID);
            if (EquipmentSlots.getEquipment() != null){
                if (power != null){
                    iron = baseIron + EquipmentSlots.getEquipment().iron;
                    return iron;
                }
            }
            iron = baseIron;
            return iron;
        });
        setVarCalculation("Di", (m, baseDiamond)->{
            AbstractPower power = AbstractDungeon.player.getPower(PickaxePower.POWER_ID);
            if (EquipmentSlots.getEquipment() != null){
                if (power != null){
                    diamond = baseDiamond + EquipmentSlots.getEquipment().diamond;
                    return diamond;
                }
            }
            diamond = baseDiamond;
            return diamond;
        });
        setVarCalculation("Wo", (m, baseWood)->{
            AbstractPower power = AbstractDungeon.player.getPower(AxePower.POWER_ID);
            if (EquipmentSlots.getEquipment() != null){
                if (power != null){
                    wood = baseWood + EquipmentSlots.getEquipment().wood;
                    return wood;
                }
            }
            wood = baseWood;
            return wood;
        });
    }
}