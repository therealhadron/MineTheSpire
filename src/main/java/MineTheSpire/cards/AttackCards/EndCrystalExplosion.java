package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class EndCrystalExplosion extends BaseCard{
    public static final String ID = makeID(EndCrystalExplosion.class.getSimpleName());

    private static final int DAMAGE = 30;
    private static final int UPG_DAMAGE = 10;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL, 1);

    public EndCrystalExplosion(){
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCreature mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        addToBot(new DamageAction(mo, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new DamageAction(p, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EndCrystalExplosion();
    }

    @Override
    public void upgrade(){
        if (this.upgraded){
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE);
        }
    }
}

