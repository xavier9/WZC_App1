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

@Api(name = "quoteEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.xavier.ehb.com", ownerName = "api.xavier.ehb.com", packagePath=""))
public class QuoteEndpoint {
    public static List<Quote> quotes = new ArrayList<Quote>();
// Make sure to add this endpoint to your web.xml file if this is a web application.

    public QuoteEndpoint() {

    }
    @ApiMethod(name="lists")
    public List<Quote> getQuotes() {
        Query<Quote> query = ofy().load().type(Quote.class);
        quotes = new ArrayList<Quote>();
        QueryResultIterator<Quote> iterator = query.iterator();
        while (iterator.hasNext()) {
            quotes.add(iterator.next());
        }
        return quotes;
    }
    /**
     * Return a collection of quotes
     *
     * @param count The number of quotes
     * @return a list of Quotes
     */
    @ApiMethod(name = "list")
    public CollectionResponse<Quote> listQuote(@Nullable @Named("cursor") String cursorString,
                                               @Nullable @Named("count") Integer count) {

        Query<Quote> query = ofy().load().type(Quote.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        quotes = new ArrayList<Quote>();
        QueryResultIterator<Quote> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            quotes.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }

        return CollectionResponse.<Quote>builder().setItems(quotes).setNextPageToken(cursorString).build();
    }



//Find

    /**
     * This inserts a new <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertQuote")
    public Quote insertQuote(Quote quote) throws ConflictException {
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
        quotes.add(quote);
        return quote;
    }

    /**
     * This updates an existing <code>Quote</code> object.
     * @param quote The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateQuote")
    public Quote updateQuote(Quote quote)throws NotFoundException {
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
        Quote record = findRecord(id);
        if(record == null) {
            throw new NotFoundException("Quote Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    //Private method to retrieve a <code>Quote</code> record
    private Quote findRecord(Long id) {
        return ofy().load().type(Quote.class).id(id).now();
//or return ofy().load().type(Quote.class).filter("id",id).first.now();
    }

}