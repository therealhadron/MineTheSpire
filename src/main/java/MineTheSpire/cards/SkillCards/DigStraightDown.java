package MineTheSpire.cards.SkillCards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.cards.StatusCurseCards.Lava;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class DigStraightDown extends BaseCard{
    public static final String ID = makeID(DigStraightDown.class.getSimpleName());

    private static final int STONE_AMOUNT = 1;
    private static final int COST = 0;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, COST);

    public DigStraightDown(){
        super(ID, info);
        setCustomVar("STONE", STONE_AMOUNT);
        this.cardsToPreview = new Lava();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Inventory.addStoneAmount(STONE_AMOUNT);
        System.out.println("Wood: " + Inventory.getWoodAmount());
        System.out.println("Stone: " + Inventory.getStoneAmount());
        System.out.println("Iron: " + Inventory.getIronAmount());
        System.out.println("Diamond: " + Inventory.getDiamondAmount());
        if (AbstractDungeon.cardRandomRng.random(0, 1) == 1){
            this.addToBot(new MakeTempCardInDrawPileAction(new Lava(), 1, true, true));
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

