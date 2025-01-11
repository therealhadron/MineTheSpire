package MineTheSpire.cards.StatusCurseCards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.cards.SkillCards.ObsidianBlock;
import MineTheSpire.cards.SkillCards.WaterBucket;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Lava extends BaseCard{
    public static final String ID = makeID(Lava.class.getSimpleName());
    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, -2);

    private static final int DAMAGE =  2;

    public Lava(){
        super(ID, info);
        setDamage(DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractCard makeCopy() {
        return new Lava();
    }

    @Override
    public void triggerWhenDrawn(){
        ArrayList<AbstractCard> notLavaCards = new ArrayList<AbstractCard>();
        int randomCard;

        for (AbstractCard c : AbstractDungeon.player.hand.group){
            if (c.cardID == WaterBucket.ID){
                addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                addToBot(new MakeTempCardInHandAction(new ObsidianBlock()));
                addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
                return;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group){
            if (c.cardID != ID && c.cardID != ObsidianBlock.class.getSimpleName() && c.cardID != Bedrock.class.getSimpleName()){
                notLavaCards.add(c);
            }
        }
        if (notLavaCards.isEmpty()) {
            return;
        }
        randomCard = AbstractDungeon.cardRandomRng.random(0,notLavaCards.size() - 1);
        addToBot(new ExhaustSpecificCardAction(notLavaCards.get(randomCard), AbstractDungeon.player.hand));
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard(){
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, DAMAGE, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }
}