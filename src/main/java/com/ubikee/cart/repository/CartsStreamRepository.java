package com.ubikee.cart.repository;

import com.ubikee.cart.Cart;
import java.util.Optional;

/**
 *
 * @author jeroldan
 */
public class CartsStreamRepository implements CartsRepository {

    @Override
    public Optional<Cart> cart(String cartID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Cart cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        /**
         *
        
        public void Save
        (IAggregate aggregate, Guid commitId
        , Action<IDictionary<string, object>> updateHeaders
        
            )
{
    var commitHeaders = new Dictionary<string, object>
    
            {
                {CommitIdHeader, commitId
                },
                {AggregateClrTypeHeader, aggregate.GetType().AssemblyQualifiedName
                }
            };
            updateHeaders(commitHeaders);

            var streamName = _aggregateIdToStreamName(aggregate.GetType(), aggregate.Id);
            var newEvents = aggregate.GetUncommittedEvents().Cast < object > ().ToList();
            var originalVersion = aggregate.Version - newEvents.Count;
            var expectedVersion = originalVersion == 0 ? ExpectedVersion.NoStream : originalVersion;
            var eventsToSave = newEvents.Select(e =  > ToEventData(Guid.NewGuid(), e, commitHeaders)).ToList();

            if (eventsToSave.Count < WritePageSize) {
                _eventStoreConnection.AppendToStream(streamName, expectedVersion, eventsToSave);
            } else {
                var transaction = _eventStoreConnection.StartTransaction(streamName, expectedVersion);

                var position = 0;
                while (position < eventsToSave.Count) {
                    var pageEvents = eventsToSave.Skip(position).Take(WritePageSize);
                    transaction.Write(pageEvents);
                    position += WritePageSize;
                }

                transaction.Commit();
            }

            aggregate.ClearUncommittedEvents();
        }



    private static EventData ToEventData(Guid eventId, object evnt, IDictionary<string, object> headers) {
        var data = Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(evnt, SerializerSettings));

        var eventHeaders = new Dictionary<string, object>(headers) {
            {
                EventClrTypeHeader, evnt.GetType().AssemblyQualifiedName
            }
        };
        var metadata = Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(eventHeaders, SerializerSettings));
        var typeName = evnt.GetType().Name;

        return new EventData(eventId, typeName, true, data, metadata);
        * 
        **/
    }

}
