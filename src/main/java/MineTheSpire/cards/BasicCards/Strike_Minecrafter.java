package MineTheSpire.cards.BasicCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;

public class Strike_Minecrafter extends BaseCard{
    public static final String ID = makeID(Strike_Minecrafter.class.getSimpleName());
    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, 1);

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public Strike_Minecrafter(){
        super(ID, info);

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE); // In order to count as a strike for perfected strike
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_Minecrafter();
    }
}

