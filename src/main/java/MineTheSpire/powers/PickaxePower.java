package MineTheSpire.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import MineTheSpire.MinecraftMod;

public class PickaxePower extends BasePower{
    public static final String POWER_ID = MinecraftMod.makeID(PickaxePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = NeutralPowertypePatch.NEUTRAL;
    private static final boolean TURN_BASED = false;
    
    private int stone_bonus;
    private int iron_bonus;
    private int diamond_bonus;

    public PickaxePower(AbstractCreature owner, int stone_bonus, int iron_bonus, int diamond_bonus) {
        super(POWER_ID, TYPE, TURN_BASED, owner, stone_bonus);
        this.owner = owner;
        this.stone_bonus = stone_bonus;
        this.iron_bonus = iron_bonus;
        this.diamond_bonus = diamond_bonus;
        this.amount = 0;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.stone_bonus + DESCRIPTIONS[1] + DESCRIPTIONS[2] + this.iron_bonus + DESCRIPTIONS[3] + DESCRIPTIONS[4] + this.diamond_bonus + DESCRIPTIONS[5];
    }
}