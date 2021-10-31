package virtuoel.pehkui.mixin.compat115plus.compat116minus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(value = LivingEntity.class, priority = 1050)
public abstract class LivingEntityMixin
{
	@ModifyArg(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
	private Entity dropXpModifyEntity(Entity entity)
	{
		ScaleUtils.setScaleOfDrop(entity, (Entity) (Object) this);
		
		return entity;
	}
}
