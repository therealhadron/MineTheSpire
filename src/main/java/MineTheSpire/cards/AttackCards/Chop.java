package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.cards.BaseCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.Inventory;

public class Chop extends BaseCard{
    public static final String ID = makeID(Chop.class.getSimpleName());

    private static final int AMOUNT = 2;
    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, COST);

    public Chop(){
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        Inventory.addWoodAmount(this.baseMagicNumber);
        System.out.println("Wood: " + Inventory.getWoodAmount());
        System.out.println("Stone: " + Inventory.getStoneAmount());
        System.out.println("Iron: " + Inventory.getIronAmount());
        System.out.println("Diamond: " + Inventory.getDiamondAmount());
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chop();
    }
}

