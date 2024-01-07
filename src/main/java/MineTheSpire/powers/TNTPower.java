package MineTheSpire.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import MineTheSpire.MinecraftMod;
import basemod.interfaces.CloneablePowerInterface;

public class TNTPower extends BasePower implements CloneablePowerInterface {

    public static final String POWER_ID = MinecraftMod.makeID(TNTPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int turns;
    private int damage;
    private static int TNTPowerOffset;

    public TNTPower(AbstractCreature owner, int turns, int damage) {
        super(POWER_ID, TYPE, TURN_BASED, owner, turns);
        this.ID = POWER_ID + TNTPowerOffset;
        this.damage = damage;
        this.turns = turns;
        ++TNTPowerOffset;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.damage, true), DamageType.THORNS, AttackEffect.FIRE));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new TNTPower(this.owner, this.turns, this.damage), this.turns));
                CardCrawlGame.sound.play(MinecraftMod.makeID("TNT_EXPLOSION"));
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw(){
        //Speical VFX and SFX of TNT Priming?
        if (this.amount == 1){
            CardCrawlGame.sound.play(MinecraftMod.makeID("TNT_PRIMING"));
        }
    }

    public void updateDescription() {

    }

    @Override
    public AbstractPower makeCopy() {
        return new TNTPower(owner, turns, damage);
    }
}
