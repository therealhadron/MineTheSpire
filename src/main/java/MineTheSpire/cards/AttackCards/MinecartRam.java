package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import MineTheSpire.actions.UseInventoryAction;
import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class MinecartRam extends BaseCard{
    public static final String ID = makeID(MinecartRam.class.getSimpleName());

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int COST = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, COST);

    public MinecartRam(){
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseInventoryAction(0,1,0,0));
        for (AbstractCreature mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
        addToBot(new DamageAction(mo, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (Inventory.getStoneAmount() < 1){
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        } else {
            return true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MinecartRam();
    }

    @Override
    public void upgrade(){
        if (this.upgraded){
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE);
        }
    }
}

