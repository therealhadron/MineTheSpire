package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Sprint extends BaseCard{
    public static final String ID = makeID(Sprint.class.getSimpleName());

    private static final int DRAW = 3;
    private static final int WEAK = 1;
    private static final int COST = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, COST);

    public Sprint(){
        super(ID, info);
        setCustomVar("DRAW", DRAW);
        setCustomVar("WEAK", WEAK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(AbstractDungeon.player, DRAW));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, WEAK, false), WEAK, true, AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sprint();
    }

    @Override
    public void upgrade(){

    }
}

