package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.world.World;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LlamaSpitEntity.class)
public class LlamaSpitEntityMixin
{
	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/passive/LlamaEntity;)V")
	private void pehkui$construct(World world, LlamaEntity owner, CallbackInfo info)
	{
		ScaleUtils.setScaleOfProjectile((Entity) (Object) this, owner);
	}
	
	@ModifyExpressionValue(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/passive/LlamaEntity;)V", at = @At(value = "CONSTANT", args = "doubleValue=0.10000000149011612D"))
	private double pehkui$construct$eyeOffset(double value, World world, LlamaEntity owner)
	{
		final float scale = ScaleUtils.getEyeHeightScale(owner);
		
		return scale != 1.0F ? value * scale : value;
	}
	
	@ModifyExpressionValue(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/passive/LlamaEntity;)V", at = @At(value = "CONSTANT", args = "floatValue=1.0F"))
	private float pehkui$construct$widthOffset(float value, World world, LlamaEntity owner)
	{
		final float scale = ScaleUtils.getBoundingBoxWidthScale(owner);
		
		return scale != 1.0F ? value * scale : value;
	}
}
