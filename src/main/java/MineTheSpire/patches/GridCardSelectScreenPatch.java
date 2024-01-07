package MineTheSpire.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.evacipated.cardcrawl.modthespire.lib.*;
import javassist.CtBehavior;

public class GridCardSelectScreenPatch {
    @SpirePatch(clz = GridCardSelectScreen.class, method = "render")
    public static class darkenCard{
        @SpireInsertPatch(locator = Locator.class, localvars = {"hoveredCard"})
        public static void Insert(GridCardSelectScreen __instance, SpriteBatch sb, AbstractCard hoveredCard){
            Texture foobar = new Texture("mineTheSpireMod/images/ui/ui_test.png");
            sb.setColor(Color.WHITE);
            sb.draw(foobar, 300, 300);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "SUP YO", 300, 800, Color.CYAN);
        }
    }

    private static class Locator extends SpireInsertLocator {
		@Override
		public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
			Matcher finalMatcher = new Matcher.FieldAccessMatcher(GridCardSelectScreen.class, "hoveredCard");
			return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
		}
	}
}
