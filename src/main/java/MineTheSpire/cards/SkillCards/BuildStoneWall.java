package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class BuildStoneWall extends BaseCard{
    public static final String ID = makeID(BuildStoneWall.class.getSimpleName());

    private static final int BLOCK = 8;
    private static final int COST = 1;
    private static final int STONE_AMT = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, COST);

    public BuildStoneWall(){
        super(ID, info);
        setBlock(BLOCK);
        setCustomVar("STONE", STONE_AMT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.useStoneAmount(STONE_AMT);
        addToBot(new GainBlockAction(p, p, BLOCK));
    }
    
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
           return false;
        } else if (Inventory.getStoneAmount() < STONE_AMT) {
           this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
           return false;
        } else {
           return canUse;
        }
     }

    @Override
    public AbstractCard makeCopy() {
        return new BuildStoneWall();
    }

    @Override
    public void upgrade(){

    }
}

