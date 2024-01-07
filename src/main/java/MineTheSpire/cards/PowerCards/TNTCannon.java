package MineTheSpire.cards.PowerCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.powers.TNTPower;

public class TNTCannon extends BaseCard{
    public static final String ID = makeID(TNTCannon.class.getSimpleName());

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.POWER, CardRarity.RARE, CardTarget.ALL_ENEMY, 1);

    private int damage = 20;
    private int turns = 2;

    public TNTCannon(){
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TNTPower(p, turns, this.damage), turns));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TNTCannon();
    }

    @Override
    public void upgrade(){

    }
}

