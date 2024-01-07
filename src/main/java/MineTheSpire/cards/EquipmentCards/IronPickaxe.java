package MineTheSpire.cards.EquipmentCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.patches.CustomTags;
import MineTheSpire.powers.PickaxePower;
import MineTheSpire.ui.EquipmentSlots;

public class IronPickaxe extends EquipmentTool{
    public static final String ID = makeID(IronPickaxe.class.getSimpleName());

    private static final int COST = 1;
    private final int DURABILITY = 6;

    private final int WOOD_COST = 0;
    private final int STONE_COST = 1;
    private final int IRON_COST = 0;
    private final int DIAMOND_COST = 0;

    private final static int WOOD = 0;
    private final static int STONE = 5;
    private final static int IRON = 3;
    private final static int DIAMOND = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, COST);

    public IronPickaxe(){
        super(ID, info, WOOD, STONE, IRON, DIAMOND);
        setSelfRetain(true);
        this.baseMagicNumber = DURABILITY;
        this.baseStone = this.stone = STONE;
        this.baseIron = this.iron = IRON;
        this.baseDiamond = this.diamond = DIAMOND;
        this.woodCost = WOOD_COST;
        this.stoneCost = STONE_COST;
        this.ironCost = IRON_COST;
        this.diamondCost = DIAMOND_COST;
        tags.add(CustomTags.PICKAXE);
        tags.add(CustomTags.IRON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        EquipmentSlots.equipTool(this);
        addToBot(new ApplyPowerAction(p, p, new PickaxePower(p, baseStone, baseIron, baseDiamond)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new IronPickaxe();
    }

    @Override
    public void upgrade(){

    }
}