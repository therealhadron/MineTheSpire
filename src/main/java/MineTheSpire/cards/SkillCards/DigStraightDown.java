package MineTheSpire.cards.SkillCards;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.actions.MineAction;
import MineTheSpire.cards.AbstractMiningCard;
import MineTheSpire.cards.StatusCurseCards.Lava;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class DigStraightDown extends AbstractMiningCard{
    public static final String ID = makeID(DigStraightDown.class.getSimpleName());

    private static final int STONE = 1;
    private static final int IRON = 0;
    private static final int DIAMOND = 0;
    private static final int COST = 0;
    private static final int DURABILITY_CHANGE = -1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, COST);

    public DigStraightDown(){
        super(ID, info, STONE, IRON, DIAMOND);
        this.cardsToPreview = new Lava();
        stone = baseStone = STONE;
        baseDurability = DURABILITY_CHANGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MineAction(baseStone, baseIron, baseDiamond, null, baseDurability));
        if (AbstractDungeon.cardRandomRng.random(0, 1) == 1){
            addToBot(new MakeTempCardInDrawPileAction(new Lava(), 1, true, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DigStraightDown();
    }

    @Override
    public void upgrade(){

    }
}

