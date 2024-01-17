package MineTheSpire.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import MineTheSpire.MinecraftMod;

public class SwordPower extends BasePower{
    public static final String POWER_ID = MinecraftMod.makeID(PickaxePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = NeutralPowertypePatch.NEUTRAL;
    private static final boolean TURN_BASED = false;

    private int strengthBonus = 0;
    private AbstractPlayer p = AbstractDungeon.player;

    public SwordPower(AbstractCreature owner, int strengthBonus) {
        super(POWER_ID, TYPE, TURN_BASED, owner, strengthBonus);
        this.owner = owner;
        this.strengthBonus = strengthBonus;
        this.amount = 0;
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthBonus)));
    }

    @Override
    public void onRemove(){
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -strengthBonus)));
    }

    public void updateDescription() {
        this.description = "Test";
    }
}