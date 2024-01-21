package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class CriticalStrike extends BaseCard{
    public static final String ID = makeID(CriticalStrike.class.getSimpleName());

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int VULNERABLE = 2;
    private static final int COST = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, COST);

    public CriticalStrike(){
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.baseMagicNumber = this.magicNumber = VULNERABLE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.baseMagicNumber, true)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CriticalStrike();
    }

    @Override
    public void upgrade(){
        if (this.upgraded){
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE);
        }
    }
}

