package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class QuarryMine extends BaseCard{
    public static final String ID = makeID(QuarryMine.class.getSimpleName());

    private static final int AMOUNT = 2;
    private static final int COST = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, COST);

    public QuarryMine(){
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.addStoneAmount(baseMagicNumber);
        Inventory.addIronAmount(baseMagicNumber);
        Inventory.addDiamondAmount(baseMagicNumber);
        System.out.println("Wood: " + Inventory.getWoodAmount());
        System.out.println("Stone: " + Inventory.getStoneAmount());
        System.out.println("Iron: " + Inventory.getIronAmount());
        System.out.println("Diamond: " + Inventory.getDiamondAmount());
    }

    @Override
    public AbstractCard makeCopy() {
        return new QuarryMine();
    }

    @Override
    public void upgrade(){

    }
}

