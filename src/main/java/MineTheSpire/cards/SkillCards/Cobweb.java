package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Cobweb extends BaseCard{
    public static final String ID = makeID(Cobweb.class.getSimpleName());

    private static final int COST = 2;
    private static final int UPG_COST = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, COST);

    public Cobweb(){
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new SlowPower(m, 0)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cobweb();
    }

    @Override
    public void upgrade(){
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(UPG_COST);
        }
    }
}

