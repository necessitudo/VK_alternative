package ru.necessitudo.app.vk_alternative.common.utils;

import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ru.necessitudo.app.vk_alternative.model.Owner;
import ru.necessitudo.app.vk_alternative.model.WallItem;
import ru.necessitudo.app.vk_alternative.model.attachment.ApiAttachment;
import ru.necessitudo.app.vk_alternative.model.view.BaseViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.AudioAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.DocAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.DocImageAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.ImageAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.LinkAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.LinkExternalViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.PageAttachmentViewModel;
import ru.necessitudo.app.vk_alternative.model.view.attachment.VideoAttachmentViewModel;
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

    public static List<BaseViewModel> getAttachmentVhItems(List<ApiAttachment> attachments) {

        List<BaseViewModel> attachmentVhItems = new ArrayList<>();
        for (ApiAttachment attachment : attachments) {

            switch (attachment.getType()) {
                case VKAttachments.TYPE_PHOTO:
                    attachmentVhItems.add(new ImageAttachmentViewModel(attachment.getPhoto()));
                    break;

                case VKAttachments.TYPE_AUDIO:
                    attachmentVhItems.add(new AudioAttachmentViewModel(attachment.getAudio()));
                    break;

                case VKAttachments.TYPE_VIDEO:
                    attachmentVhItems.add(new VideoAttachmentViewModel(attachment.getVideo()));
                    break;

                case VKAttachments.TYPE_DOC:
                    if (attachment.getDoc().getPreview() != null) {
                        attachmentVhItems.add(new DocImageAttachmentViewModel(attachment.getDoc()));
                    } else {
                        attachmentVhItems.add(new DocAttachmentViewModel(attachment.getDoc()));
                    }
                    break;

                case VKAttachments.TYPE_LINK:
                    if (attachment.getLink().getIsExternal() == 1) {
                        attachmentVhItems.add(new LinkExternalViewModel(attachment.getLink()));
                    } else {
                        attachmentVhItems.add(new LinkAttachmentViewModel(attachment.getLink()));
                    }
                    break;

                case "page":
                    attachmentVhItems.add(new PageAttachmentViewModel(attachment.getPage()));
                    break;

                default:
                    throw new NoSuchElementException("Attachment type " + attachment.getType() + " is not supported.");
            }
        }
        return attachmentVhItems;
    }
}
