package logisticspipes.asm.wrapper;

import logisticspipes.LPConstants;
import logisticspipes.LogisticsPipes;
import logisticspipes.proxy.MainProxy;
import logisticspipes.utils.string.ChatColor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractWrapper {
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected WrapperState	state	= WrapperState.Enabled;
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Throwable		reason;
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private String			modId;
	
	public void handleException(Throwable e) {
		if(!isEnabled()) {
			if(LPConstants.DEBUG) {
				e.printStackTrace();
			}
			return;
		}
		e.printStackTrace();
		this.state = WrapperState.Exception;
		this.reason = e;
		String message = "Disabled " + getName() + getTypeName() + (modId != null ? (" for Mod: " + modId) : "") + ". Cause was an Exception";
		LogisticsPipes.log.fatal(message);
		MainProxy.proxy.sendBroadCast(ChatColor.RED + message);
	}

	public void reEnable() {
		if(this.state != WrapperState.Exception) return;
		this.state = WrapperState.Enabled;
		this.reason = null;
	}
	
	protected final boolean isEnabled() {
		return state == WrapperState.Enabled;
	}
	
	protected final boolean canTryAnyway() {
		return state != WrapperState.ModMissing;
	}
	
	public abstract String getName();
	
	public abstract String getTypeName();

}
