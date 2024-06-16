package net.ezogaming;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;

public class ModInventories {

    public static final ScreenHandlerType<FriendScreenHandler> FRIEND;

    private ModInventories() throws IllegalAccessException {throw new IllegalAccessException();}

    static {
        FRIEND = new ExtendedScreenHandlerType<>(FriendScreenHandler::new);
    }
}
