package logisticspipes.network.packets.orderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import logisticspipes.network.IReadListObject;
import logisticspipes.network.IWriteListObject;
import logisticspipes.network.LPDataInputStream;
import logisticspipes.network.LPDataOutputStream;
import logisticspipes.network.abstractpackets.ModernPacket;
import logisticspipes.proxy.MainProxy;
import logisticspipes.request.resources.IResource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.entity.player.EntityPlayer;

@Accessors(chain = true)
public class MissingItems extends ModernPacket {

    @Getter
    @Setter
    private Collection<IResource> items = new ArrayList<>();

    @Setter
    @Getter
    private boolean flag;

    public MissingItems(int id) {
        super(id);
    }

    @Override
    public ModernPacket template() {
        return new MissingItems(getId());
    }

    @Override
    public void processPacket(EntityPlayer player) {
        MainProxy.proxy.processMissingItemsPacket(this, player);
    }

    @Override
    public void writeData(LPDataOutputStream data) throws IOException {
        data.writeCollection(items, (data1, object) -> data1.writeIResource(object));
        data.writeBoolean(isFlag());
    }

    @Override
    public void readData(LPDataInputStream data) throws IOException {
        items = data.readList(data1 -> data1.readIResource());
        setFlag(data.readBoolean());
    }
}
