package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class WaterBucket extends BaseCard{
    public static final String ID = makeID(WaterBucket.class.getSimpleName());
    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);
    
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public WaterBucket(){
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setSelfRetain(true);
        this.cardsToPreview = new ObsidianBlock();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, BLOCK));
    }

    @Override
    public AbstractCard makeCopy() {
        return new WaterBucket();
    }

    @Override
    public void upgrade(){

    }
}

