package logisticspipes.routing.order;

import java.util.List;

import logisticspipes.utils.item.ItemIdentifierStack;
import lombok.Getter;

public class ClientSideOrderInfo implements IOrderInfoProvider {

	@Getter
	private final ItemIdentifierStack asDisplayItem;
	@Getter
	private final boolean isFinished;
	@Getter
	private final ResourceType type;
	@Getter
	private final boolean inProgress;
	@Getter
	private final int routerId;
	@Getter
	private final boolean isWatched = false;
	@Getter
	private final List<Float> progresses;
	@Getter
	private final byte machineProgress;
	
	public ClientSideOrderInfo(ItemIdentifierStack item, boolean isFinished, ResourceType type, boolean inProgress, int routerId, List<Float> progresses, byte machineProgress) {
		this.asDisplayItem = item;
		this.isFinished = isFinished;
		this.type = type;
		this.inProgress = inProgress;
		this.routerId = routerId;
		this.progresses = progresses;
		this.machineProgress = machineProgress;
	}
	
	//Ignore this call
	@Override public void setWatched() {}
}
