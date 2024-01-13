package MineTheSpire.cards.EquipmentCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.patches.CustomTags;
import MineTheSpire.ui.EquipmentSlots;

public class WoodenSword extends EquipmentTool{
    public static final String ID = makeID(WoodenSword.class.getSimpleName());

    private static final int COST = 1;
    private static final int DURABILITY = 2;

    private final int WOOD_COST = 0;
    private final int STONE_COST = 1;
    private final int IRON_COST = 0;
    private final int DIAMOND_COST = 0;

    private final static int WOOD = 1;
    private final static int STONE = 0;
    private final static int IRON = 0;
    private final static int DIAMOND = 0;

    private final int STRENGTH = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.POWER, CardRarity.SPECIAL, CardTarget.NONE, COST);

    public WoodenSword(){
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
        tags.add(CustomTags.WOOD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        EquipmentSlots.equipTool(this);
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, baseMagicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new WoodenSword();
    }

    @Override
    public void upgrade(){

    }
}