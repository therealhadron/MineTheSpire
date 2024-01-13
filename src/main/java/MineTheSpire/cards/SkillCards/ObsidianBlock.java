package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class ObsidianBlock extends BaseCard{
    public static final String ID = makeID(ObsidianBlock.class.getSimpleName());
    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, 0);

    private static final int BLOCK = 15;

    public ObsidianBlock(){
        super(ID, info);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, BLOCK));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ObsidianBlock();
    }
}