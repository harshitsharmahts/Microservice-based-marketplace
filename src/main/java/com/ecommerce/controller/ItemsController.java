package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 *
 * <p>
 *     Controller to handle all the REST requests made to item-service
 *     All the item related REST Api calls will be handled by this controller class.
 *     This class will be responsible for interacting the database.
 * </p>
 * @Author Harshit Sharma
 */

@RestController
@RequestMapping("/item")
public class ItemsController {

    private final ItemsService service;

    @Autowired
    public ItemsController(@Qualifier("itemsServiceImpl") ItemsService service) {
        this.service = service;
    }


    /**
     * <p>
     *     Gets the item by it's ID, the item that has the same itemId as provided will be returned.
     * </p>
     *
     * @param itemId the unique id of the Item.
     * @return the Item, whose id matched with the passed id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Items> getItem(@PathVariable("id") String itemId) {
        Items i = service.getItem(itemId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    /**
     * <p>
     * Paginate items stored in the database, and return the list of [size] items.
     * </p>
     *
     * @param page the page number (starting from 0).
     * @param size the size or number of items per page.
     * @return
     */
    @GetMapping("/{page}/{size}")
    public ResponseEntity<List<Items>> getAllItems(@PathVariable Integer page,
                                                   @PathVariable Integer size){
        List<Items> list = service.getAllItems(page,size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     *
     * <p>
     *     Adds the new Item (or item information) into the database.
     * </p>
     *
     * @param item the item that needs to be inserted.
     *             Item ID need not to be supplied/passed,
     *             It'll be generated automatically.
     * @return the newly item that has been inserted.
     * @see {@link ItemsService#addNewItem(Items)}
     */
    @PostMapping("/")
    public ResponseEntity<Items> addNewItem(@RequestBody Items item) {
        Items i = service.addNewItem(item);
        return new ResponseEntity<>(i, HttpStatus.OK);

    }

    /**
     *
     * <p>
     *     Updates the item's information.
     * </p>
     *
     * @param item the item's information bind into the {@link Items} object.
     *             the object must contain the item ID.
     * @return the updates item information.
     * @see {@link ItemsService#updateItem(Items)}
     */
    @PutMapping("/")
    public ResponseEntity<Items> updateItem(@RequestBody Items item) {
        Items i = service.updateItem(item);
        return new ResponseEntity<>(i, HttpStatus.CREATED);
    }

    /**
     *
     * <p>
     *     Deletes the item from the database.
     * </p>
     *
     * @param itemId the itemId of the item that needs to be deleted.
     * @return Void.
     *
     * @see {@link ItemsService#deleteItem(String)}
     */
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteItem(String itemId) {
        service.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/check")
    public String sayHello() {
        return "Item DB-Service \t\t-\t\t [UP]";
    }
}
