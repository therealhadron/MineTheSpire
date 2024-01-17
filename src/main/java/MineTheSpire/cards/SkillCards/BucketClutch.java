package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class BucketClutch extends BaseCard{
    public static final String ID = makeID(BucketClutch.class.getSimpleName());
    
    private static final int COST = 2;
    private static final boolean RETAIN = false;
    private static final boolean UPG_RETAIN = true;
    private static final boolean EXHAUST = true;
    private static final int IRON_AMT = 2;
    private static final int INTANGIBLE = 1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, COST);

    public BucketClutch(){
        super(ID, info);
        setSelfRetain(RETAIN);
        setExhaust(EXHAUST);
        setCustomVar("IRON", IRON_AMT);
        this.magicNumber = this.baseMagicNumber = INTANGIBLE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.useIronAmount(IRON_AMT);
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, INTANGIBLE), INTANGIBLE));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
           return false;
        } else if (Inventory.getIronAmount() < IRON_AMT) {
           this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
           return false;
        } else {
           return canUse;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BucketClutch();
    }

    @Override
    public void upgrade(){
        if (!upgraded){
            upgradeName();
            setSelfRetain(UPG_RETAIN);
            this.upgradesDescription = true;
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn(){
        // this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, INTANGIBLE), INTANGIBLE));
    }
}

