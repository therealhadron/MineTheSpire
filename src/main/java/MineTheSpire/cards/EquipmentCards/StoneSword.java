package MineTheSpire.cards.EquipmentCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.util.CardStats;
import MineTheSpire.actions.EquipAction;
import MineTheSpire.cards.AbstractToolCard;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.patches.CustomTags;
import MineTheSpire.powers.SwordPower;

public class StoneSword extends AbstractToolCard{
    public static final String ID = makeID(StoneSword.class.getSimpleName());

    private static final int COST = 0;
    private static final int DURABILITY = 2;

    private final int WOOD_COST = 0;
    private final int STONE_COST = 1;
    private final int IRON_COST = 0;
    private final int DIAMOND_COST = 0;

    private final static int WOOD = 1;
    private final static int STONE = 0;
    private final static int IRON = 0;
    private final static int DIAMOND = 0;

    private final int STRENGTH = 4;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE, COST);

    public StoneSword(){
        super(ID, info, WOOD, STONE, IRON, DIAMOND, DURABILITY);
        setSelfRetain(true);
        this.baseDurability = DURABILITY;
        this.baseStone = this.stone = STONE;
        this.baseIron = this.iron = IRON;
        this.baseDiamond = this.diamond = DIAMOND;
        this.woodCost = WOOD_COST;
        this.stoneCost = STONE_COST;
        this.ironCost = IRON_COST;
        this.diamondCost = DIAMOND_COST;
        this.baseMagicNumber = STRENGTH;
        tags.add(CustomTags.SWORD);
        tags.add(CustomTags.STONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EquipAction(this));
        addToBot(new ApplyPowerAction(p, p, new SwordPower(p, this.baseMagicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StoneSword();
    }

    @Override
    public void upgrade(){

    }
}