package l2p.gameserver.network.serverpackets;

import l2p.gameserver.model.Player;
import l2p.gameserver.model.items.ManufactureItem;

import java.util.List;

public class RecipeShopSellList extends L2GameServerPacket {
    private int objId, curMp, maxMp;
    private long adena;
    private List<ManufactureItem> createList;

    public RecipeShopSellList(Player buyer, Player manufacturer) {
        objId = manufacturer.getObjectId();
        curMp = (int) manufacturer.getCurrentMp();
        maxMp = manufacturer.getMaxMp();
        adena = buyer.getAdena();
        createList = manufacturer.getCreateList();
    }

    @Override
    protected final void writeImpl() {
        writeC(0xdf);
        writeD(objId);
        writeD(curMp);//Creator's MP
        writeD(maxMp);//Creator's MP
        writeQ(adena);
        writeD(createList.size());
        for (ManufactureItem mi : createList) {
            writeD(mi.getRecipeId());
            writeD(0x00); //unknown
            writeQ(mi.getCost());
        }
    }
}