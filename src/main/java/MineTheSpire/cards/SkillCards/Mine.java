package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import MineTheSpire.actions.MineAction;
import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.patches.CustomTags;
import MineTheSpire.powers.PickaxePower;
import MineTheSpire.ui.EquipmentSlots;

public class Mine extends BaseCard{
    public static final String ID = makeID(Mine.class.getSimpleName());

    private final int STONE = 1;
    private final int IRON = 0;
    private final int DIAMOND = 0;
    private static final int COST = 1;
    private static final int DURABILITY_CHANGE = -1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, COST);

    public Mine(){
        super(ID, info);
        baseStone = stone = STONE;
        baseIron = iron = IRON;
        baseDiamond = diamond = DIAMOND;
        setCustomVar("St", baseStone);
        setCustomVar("Ir", baseIron);
        setCustomVar("Di", baseDiamond);
        setExhaust(true);
        tags.add(CustomTags.MINE);
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
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MineAction(stone, iron, diamond, EquipmentSlots.getEquipment(), DURABILITY_CHANGE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Mine();
    }

    @Override
    public void upgrade(){

    }
}

