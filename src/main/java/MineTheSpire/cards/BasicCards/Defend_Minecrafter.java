package MineTheSpire.cards.BasicCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Defend_Minecrafter extends BaseCard {
    public static final String ID = makeID(Defend_Minecrafter.class.getSimpleName());
    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 1);

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Defend_Minecrafter() {
        super(ID, info);

        tags.add(CardTags.STARTER_DEFEND);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, BLOCK));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend_Minecrafter();
    }
}
