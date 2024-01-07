package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class PunchATree extends BaseCard{
    public static final String ID = makeID(Mine.class.getSimpleName());

    private static final int COST = 0;

    private final int WOOD = 1;
    private static final int WEAK = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, COST);

    public PunchATree(){
        super(ID, info);
        this.baseWood = WOOD;
        this.magicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.addWoodAmount(this.baseWood);
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Mine();
    }

    @Override
    public void upgrade(){

    }
}

