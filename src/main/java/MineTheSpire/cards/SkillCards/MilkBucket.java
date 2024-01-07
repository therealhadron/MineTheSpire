package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.actions.RemoveAllBuffsDebuffsAction;
import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class MilkBucket extends BaseCard{
    public static final String ID = makeID(MilkBucket.class.getSimpleName());

    private static final int AMOUNT = 2;
    private static final int COST = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, COST);

    public MilkBucket(){
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveAllBuffsDebuffsAction(p));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MilkBucket();
    }

    @Override
    public void upgrade(){

    }
}

