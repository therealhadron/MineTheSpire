package MineTheSpire.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import MineTheSpire.MinecraftMod;

public class UIPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "combatUpdate")
	public static class UIUpdatePatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractPlayer __instance) {
			MinecraftMod.inventory.update();
            MinecraftMod.equipmentslots.update();
		}
	}

	@SpirePatch(clz = EnergyPanel.class, method = "renderOrb")
	public static class UIRenderPatch {
		@SpirePostfixPatch
		public static void Postfix(EnergyPanel __instance, SpriteBatch sb) {
			MinecraftMod.inventory.render(sb);
			MinecraftMod.equipmentslots.render(sb);
		}
	}
}