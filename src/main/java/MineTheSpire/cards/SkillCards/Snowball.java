package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Snowball extends BaseCard{
    public static final String ID = makeID(Snowball.class.getSimpleName());

    private static final int WEAK = 1;
    private static final int UPG_WEAK = 1;
    private static final int COST = 1;
    private static final int UPG_COST = 0;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, COST);

    public Snowball(){
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Snowball();
    }

    @Override
    public void upgrade(){
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(UPG_WEAK);
            this.upgradeBaseCost(UPG_COST);
        }
    }
}

