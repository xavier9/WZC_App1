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
 * Created by xavier on 24/01/2015.
 */
@Api(name = "contactEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.xavier.ehb.com", ownerName = "api.xavier.ehb.com", packagePath=""))
public class ContactEndpoint {

// Make sure to add this endpoint to your web.xml file if this is a web application.

    public ContactEndpoint() {

    }

    /**
     * Return a collection of quotes
     *
     * @param count The number of quotes
     * @return a list of Quotes
     */

    @ApiMethod(name = "list")
    public CollectionResponse<Contact> listEvent(@Nullable @Named("cursor") String cursorString,
                                               @Nullable @Named("count") Integer count) {

        Query<Contact> query = ofy().load().type(Contact.class);

        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<Contact> records = new ArrayList<Contact>();
        QueryResultIterator<Contact> iterator = query.iterator();
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
        return CollectionResponse.<Contact>builder().setItems(records).setNextPageToken(cursorString).build();
    }
    /**
     * This inserts a new <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertQuote")
    public Contact insertQuote(Contact quote) throws ConflictException {
//If if is not null, then check if it exists. If yes, throw an Exception
//that it is already present
        if (quote.getId() != null) {
            if (findRecord(quote.getId()) != null) {
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
    @ApiMethod(name = "updateQuote")
    public Contact updateQuote(Contact quote)throws NotFoundException {
        if (findRecord(quote.getId()) == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().save().entity(quote).now();
        return quote;
    }

    /**
     * This deletes an existing <code>Quote</code> object.
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeQuote")
    public void removeQuote(@Named("id") Long id) throws NotFoundException {
        Contact record = findRecord(id);
        if(record == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    @ApiMethod(name="findContact")
    public Contact findRecord(@Named("id") Long id) {
        return ofy().load().type(Contact.class).id(id).now();
//or return ofy().load().type(Quote.class).filter("id",id).first.now();
    }

}
