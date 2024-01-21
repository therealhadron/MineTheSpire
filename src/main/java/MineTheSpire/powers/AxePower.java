package MineTheSpire.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import MineTheSpire.MinecraftMod;
import MineTheSpire.ui.EquipmentSlots;

public class AxePower extends BasePower{
    public static final String POWER_ID = MinecraftMod.makeID(AxePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = NeutralPowertypePatch.NEUTRAL;
    private static final boolean TURN_BASED = false;
    
    private int wood_bonus;

    public AxePower(AbstractCreature owner, int wood_bonus) {
        super(POWER_ID, TYPE, TURN_BASED, owner, wood_bonus);
        this.owner = owner;
        this.wood_bonus = wood_bonus;
        this.amount = 0;
        this.updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature m) {
        addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, 1, true)));
        EquipmentSlots.useDurability(-1);//use one durability
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.wood_bonus + DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }
}