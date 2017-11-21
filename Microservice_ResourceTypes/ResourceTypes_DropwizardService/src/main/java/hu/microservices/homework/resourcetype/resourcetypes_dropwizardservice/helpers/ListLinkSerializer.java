
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Link;

public class ListLinkSerializer extends JsonSerializer<List<Link>>{

    @Override
    public void serialize(List<Link> links, JsonGenerator jg, SerializerProvider sp) 
            throws IOException, JsonProcessingException {
        jg.writeStartArray();
        for(Link l:links){
            jg.writeStartObject();
            jg.writeStringField("rel", l.getRel());
            jg.writeStringField("href", l.getUri().toString());
        jg.writeEndObject();
        }
        jg.writeEndArray();
    }   
}
