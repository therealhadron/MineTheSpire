package MineTheSpire.patches;

import java.util.Dictionary;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;

import MineTheSpire.cards.EquipmentCards.EquipmentTool;

import com.evacipated.cardcrawl.modthespire.lib.*;

public class GridCardSelectScreenPatch {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class darkenCard{
        public static void Postfix(AbstractCard __instance, SpriteBatch sb){
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID){
                    if (__instance instanceof EquipmentTool){
                        Dictionary<String, Integer> recipeCost = ((EquipmentTool)__instance).getRecipeCost();
                        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "w: " + recipeCost.get("wood"), (__instance.current_x - 100.0F) * Settings.scale, (__instance.current_y - AbstractCard.IMG_HEIGHT/2) * Settings.scale, Color.CYAN);
                        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "s: " + recipeCost.get("stone"), (__instance.current_x - 25.0F) * Settings.scale, (__instance.current_y - AbstractCard.IMG_HEIGHT/2) * Settings.scale, Color.CYAN);
                        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "i: " + recipeCost.get("iron"), (__instance.current_x + 25.0F) * Settings.scale, (__instance.current_y - AbstractCard.IMG_HEIGHT/2) * Settings.scale, Color.CYAN);
                        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "d: " + recipeCost.get("diamond"), (__instance.current_x + 100.0F) * Settings.scale, (__instance.current_y - AbstractCard.IMG_HEIGHT/2) * Settings.scale, Color.CYAN);
                        if (!EquipmentTool.hasEnoughResources(recipeCost)){
                            __instance.darken(true);
                        }
                    }
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
