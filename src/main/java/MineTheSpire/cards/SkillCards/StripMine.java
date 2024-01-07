package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class StripMine extends BaseCard{
    public static final String ID = makeID(StripMine.class.getSimpleName());

    private static final int AMOUNT = 2;
    private static final int COST = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, COST);

    public StripMine(){
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.addStoneAmount(baseMagicNumber);
        Inventory.addIronAmount(baseMagicNumber);
        System.out.println("Wood: " + Inventory.getWoodAmount());
        System.out.println("Stone: " + Inventory.getStoneAmount());
        System.out.println("Iron: " + Inventory.getIronAmount());
        System.out.println("Diamond: " + Inventory.getDiamondAmount());
    }

    @Override
    public AbstractCard makeCopy() {
        return new StripMine();
    }

    @Override
    public void upgrade(){

    }
}

