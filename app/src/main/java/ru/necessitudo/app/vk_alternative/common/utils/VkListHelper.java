package ru.necessitudo.app.vk_alternative.common.utils;

import java.util.List;

import ru.necessitudo.app.vk_alternative.model.Owner;
import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.rest.model.response.ItemsWIthSendersResponse;

/**
 * Created by olegdubrovin on 20/12/17.
 */

public class VkListHelper {

    public static List<WallItem> getWallList(ItemsWIthSendersResponse<WallItem> responce){
        List<WallItem> wallItems = responce.items;

        for (WallItem wallItem:wallItems){
            Owner sender = responce.getSender(wallItem.getFromId());
            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());

            wallItem.setAttachmentsString(Utils.convertAttachmentsToFontIcons(wallItem.getAttachments()));

            if (wallItem.haveSharedRepost()){
                Owner responceSender = responce.getSender(wallItem.getSharedRepost().getFromId());
                wallItem.getSharedRepost().setSenderName(sender.getFullName());
                wallItem.getSharedRepost().setSenderPhoto(sender.getPhoto());

                wallItem.getSharedRepost().setAttachmentsString(Utils.convertAttachmentsToFontIcons(
                        wallItem.getSharedRepost().getAttachments()
                ));


            }
        }

        return wallItems;

    }
}
