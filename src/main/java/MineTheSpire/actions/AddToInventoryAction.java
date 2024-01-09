package MineTheSpire.actions;

import java.util.Dictionary;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import MineTheSpire.ui.Inventory;

public class AddToInventoryAction extends AbstractGameAction {
    private int wood;
    private int stone;
    private int iron;
    private int diamond;

    public AddToInventoryAction(Dictionary<String, Integer> recipe) {
        this.wood = recipe.get("wood");
        this.stone = recipe.get("stone");
        this.iron = recipe.get("iron");
        this.diamond = recipe.get("diamond");
    }

    @Override
    public void update() {
        Inventory.addWoodAmount(wood);
        Inventory.addStoneAmount(stone);
        Inventory.addIronAmount(iron);
        Inventory.addDiamondAmount(diamond);
        isDone = true;
    }
}
