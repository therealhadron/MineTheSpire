package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class SweepingBlade extends BaseCard{
    public static final String ID = makeID(SweepingBlade.class.getSimpleName());

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY, 1);

    public SweepingBlade(){
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, DAMAGE, this.damageTypeForTurn, AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SweepingBlade();
    }

    @Override
    public void upgrade(){
        if (this.upgraded){
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE);
        }
    }
}

