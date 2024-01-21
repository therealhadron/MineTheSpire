package MineTheSpire.actions;

import java.util.Dictionary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import MineTheSpire.ui.Inventory;

public class UseInventoryAction extends AbstractGameAction {
    private int wood;
    private int stone;
    private int iron;
    private int diamond;

    public UseInventoryAction(Dictionary<String, Integer> recipe) {
        this.wood = recipe.get("wood");
        this.stone = recipe.get("stone");
        this.iron = recipe.get("iron");
        this.diamond = recipe.get("diamond");
    }

    public UseInventoryAction(int wood, int stone, int iron, int diamond){
        this.wood = wood;
        this.stone = stone;
        this.iron = iron;
        this.diamond = diamond;
    }

    @Override
    public void update() {
        Inventory.useWoodAmount(wood);
        Inventory.useStoneAmount(stone);
        Inventory.useIronAmount(iron);
        Inventory.useDiamondAmount(diamond);
        isDone = true;
    }
}
