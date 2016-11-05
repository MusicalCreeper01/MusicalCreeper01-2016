package org.devathon.contest2016;

import java.util.*;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Events implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        GivePlayerHelpBook(event.getPlayer());
    }


    public void GivePlayerHelpBook(Player player){
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);

        BookMeta meta = (BookMeta)book.getItemMeta();

        meta.setAuthor("Server");
        meta.setTitle("Machine FAQ"); // < 16 chars

        ArrayList<String> bookPages = new ArrayList<>();

        bookPages.add("This is page 1!");
        bookPages.add("This is page 2!");
        bookPages.add("This is page 3!");

        meta.setPages(bookPages);
        book.setItemMeta(meta);
        player.getInventory().addItem(book);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Block block = event.getBlock();
        if(event.getLines()[0].equalsIgnoreCase(DevathonPlugin.INSTANCE.SignHead)){
            if(block.getType() == Material.WALL_SIGN){
                Sign s = (Sign)block.getState().getData();
                Block attachedBlock = block.getRelative(s.getAttachedFace());

                if(attachedBlock.getType() == DevathonPlugin.INSTANCE.MachineBlock){
                    event.getPlayer().sendMessage("Successfully created a machine! " + block.getType().toString());
                } else {
                    event.getPlayer().sendMessage(attachedBlock.getType().name() + " is not a valid machine block! Please use " + DevathonPlugin.INSTANCE.MachineBlock.);
                }
            } else{
                event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Machine sign must be placed on side of block! ");
            }
        }
        System.out.println(event.getLines()[0]);
    }
}