package virtuoel.pehkui.mixin.client.compat114;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin
{
	@Dynamic
	@Shadow
	MinecraftClient field_4050; // UNMAPPED_FIELD
	
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.RENDER_OVERLAYS, at = @At(value = "CONSTANT", args = "floatValue=0.1F"))
	private float pehkui$renderOverlays$offset(float value)
	{
		final float scale = ScaleUtils.getEyeHeightScale(field_4050.player);
		
		return scale != 1.0F ? value * scale : value;
	}
}
