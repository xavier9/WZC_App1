package com.ehb.xavier.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import static com.ehb.xavier.api.OfyService.ofy;

/**
 * Created by xavier on 10/02/2015.
 */
@Api(name = "menuinsertEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.xavier.ehb.com", ownerName = "api.xavier.ehb.com", packagePath=""))
public class MenuInsertEndpoint {

// Make sure to add this endpoint to your web.xml file if this is a web application.

    public MenuInsertEndpoint() {

    }

    /**
     * Return a collection of quotes
     *
     * @param count The number of quotes
     * @return a list of Quotes
     */
    @ApiMethod(name = "list")
    public CollectionResponse<MenuInsert> listMenuInsert(@Nullable @Named("cursor") String cursorString,
                                                       @Nullable @Named("count") Integer count) {

        Query<MenuInsert> query = ofy().load().type(MenuInsert.class);

        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<MenuInsert> records = new ArrayList<MenuInsert>();
        QueryResultIterator<MenuInsert> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

//Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<MenuInsert>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /**
     * This inserts a new <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertMenuKeuze")
    public MenuInsert insertMenuInsert(MenuInsert quote) throws ConflictException {
//If if is not null, then check if it exists. If yes, throw an Exception
//that it is already present
        if (quote.getID() != null) {
            if (findRecordMenuInsert(quote.getID()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
//Since our @Id field is a Long, Objectify will generate a unique value for us
//when we use put
        ofy().save().entity(quote).now();

        return quote;
    }

    /**
     * This updates an existing <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateMenuKeuze")
    public MenuInsert updateMenuInsert(MenuInsert quote)throws NotFoundException {
        if (findRecordMenuInsert(quote.getID()) == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().save().entity(quote).now();
        return quote;
    }

    /**
     * This deletes an existing <code>Quote</code> object.
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeMenuKeuze")
    public void removeMenuInsert(@Named("id") Long id) throws NotFoundException {
        MenuInsert record = findRecordMenuInsert(id);
        if(record == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    @ApiMethod(name="findMenuKeuze")
    public MenuInsert findRecordMenuInsert(@Named("id") Long id) {
        return ofy().load().type(MenuInsert.class).id(id).now();
//or return ofy().load().type(Quote.class).filter("id",id).first.now();
    }

}

