package MineTheSpire.cards.AttackCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import MineTheSpire.actions.ChopAction;
import MineTheSpire.cards.AbstractMiningCard;
import MineTheSpire.util.CardStats;
import MineTheSpire.character.Minecrafter;
import MineTheSpire.ui.EquipmentSlots;

public class Chop extends AbstractMiningCard{
    public static final String ID = makeID(Chop.class.getSimpleName());

    private static final int WOOD = 2;
    private static final int STONE = 2;
    private static final int IRON = 2;
    private static final int DIAMOND = 2;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;

    private static final int DURABILITY_CHANGE = -1;

    private static final CardStats info = new CardStats(Minecrafter.Enums.CARD_COLOR, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, COST);

    public Chop(){
        super(ID, info, WOOD, STONE, IRON, DIAMOND);
        this.baseWood = this.wood = WOOD;
        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChopAction(wood, EquipmentSlots.getEquipment(), DURABILITY_CHANGE));
        addToBot(new DamageAction(m, new DamageInfo(p, DAMAGE, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chop();
    }
}

