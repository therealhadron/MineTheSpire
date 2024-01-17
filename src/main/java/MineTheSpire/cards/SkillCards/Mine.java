package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.actions.MineAction;
import MineTheSpire.cards.AbstractMiningCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.EquipmentSlots;

public class Mine extends AbstractMiningCard{
    public static final String ID = makeID(Mine.class.getSimpleName());

    private static final int STONE = 1;
    private static final int IRON = 0;
    private static final int DIAMOND = 0;
    private static final int COST = 1;
    private static final int DURABILITY_CHANGE = -1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, COST);

    public Mine(){
        super(ID, info, STONE, IRON, DIAMOND);
        baseStone = stone = STONE;
        baseIron = iron = IRON;
        baseDiamond = diamond = DIAMOND;
        setExhaust(true);
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