package MineTheSpire.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import MineTheSpire.cards.EquipmentCards.EquipmentTool;

import com.evacipated.cardcrawl.modthespire.lib.*;
import javassist.CtBehavior;

public class GridCardSelectScreenPatch {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class darkenCard{
        // @SpireInsertPatch(locator = Locator.class)
        public static void Postfix(AbstractCard __instance, SpriteBatch sb){
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID){
                // for (AbstractCard c : AbstractDungeon.player.hand.group){
                //     if (c.uuid == __instance.uuid){
                    if (__instance instanceof EquipmentTool){
                        if (!EquipmentTool.hasEnoughResources(((EquipmentTool)__instance).getRecipeCost())){
                            __instance.darken(true);
                        }
                    }
                //         break;
                //     }
                // }
            } else {
                __instance.lighten(true);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "darken")
    public static class darkenCardSetting{
        public static void Postfix(AbstractCard __instance, boolean immediate, Color ___tintColor, float ___darkTimer){
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID){
                ___tintColor.a = 0.75F;
                ___darkTimer = 0.0F;
            }
        }
    }

    // private static class Locator extends SpireInsertLocator {
	// 	@Override
	// 	public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
	// 		Matcher finalMatcher = new Matcher.FieldAccessMatcher(GridCardSelectScreen.class, "targetGroup");
	// 		return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
	// 	}
	// }
}
