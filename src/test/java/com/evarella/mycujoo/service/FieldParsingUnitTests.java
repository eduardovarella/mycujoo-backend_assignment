package com.evarella.mycujoo.service;

import com.evarella.mycujoo.model.SchemaDefinition;
import com.evarella.mycujoo.model.Subject;
import com.evarella.mycujoo.utils.HttpUtilsException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class FieldParsingUnitTests {

    @Test
    public final void fieldWithStringTypeAndNotNullable() throws Exception, HttpUtilsException {

        SQLCommandService service = spy(SQLCommandService.class);
        Subject subject = new Subject("S1");
        doReturn("{\"subject\": \"S1\",\"version\": 2,\"id\": 121,\"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"s1_name\\\",\\\"fields\\\":[{\\\"name\\\":\\\"f1\\\",\\\"type\\\":\\\"string\\\"}]}\"}").when(service).loadSchemaDefinitionFromAPI(subject);
        SchemaDefinition schemaDefinition = service.getSchemaDefinitionFromSubject(subject);
        Assert.assertEquals("S1", schemaDefinition.getSubject());
        Assert.assertEquals(2, schemaDefinition.getVersion());
        Assert.assertEquals(121, schemaDefinition.getId());
        Assert.assertEquals(1, schemaDefinition.getSchema().getFields().size());
        Assert.assertEquals("f1", schemaDefinition.getSchema().getFields().get(0).getName());
        Assert.assertEquals("string", schemaDefinition.getSchema().getFields().get(0).getType());
        Assert.assertEquals(false, schemaDefinition.getSchema().getFields().get(0).isNullable());
    }

    @Test
    public final void fieldWithArrayType() throws Exception, HttpUtilsException {

        SQLCommandService service = spy(SQLCommandService.class);
        Subject subject = new Subject("S1");
        doReturn("{\"subject\": \"S1\",\"version\": 2,\"id\": 121,\"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"s1_name\\\",\\\"fields\\\":[{\\\"name\\\":\\\"f1\\\",\\\"type\\\":[\\\"null\\\",\\\"int\\\"]}]}\"}").when(service).loadSchemaDefinitionFromAPI(subject);
        SchemaDefinition schemaDefinition = service.getSchemaDefinitionFromSubject(subject);
        Assert.assertEquals("S1", schemaDefinition.getSubject());
        Assert.assertEquals(2, schemaDefinition.getVersion());
        Assert.assertEquals(121, schemaDefinition.getId());
        Assert.assertEquals(1, schemaDefinition.getSchema().getFields().size());
        Assert.assertEquals("f1", schemaDefinition.getSchema().getFields().get(0).getName());
        Assert.assertEquals("int", schemaDefinition.getSchema().getFields().get(0).getType());
        Assert.assertEquals(true, schemaDefinition.getSchema().getFields().get(0).isNullable());
    }

    @Test
    public final void fieldWithRecordType() throws Exception, HttpUtilsException {

        SQLCommandService service = spy(SQLCommandService.class);
        Subject subject = new Subject("S1");
        doReturn("{\"subject\": \"S1\",\"version\": 2,\"id\": 121,\"schema\": \"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"s1_name\\\",\\\"fields\\\":[{\\\"name\\\":\\\"f1\\\",\\\"type\\\":{\\\"xxx\\\":\\\"yyy\\\"}}]}\"}").when(service).loadSchemaDefinitionFromAPI(subject);
        SchemaDefinition schemaDefinition = service.getSchemaDefinitionFromSubject(subject);
        Assert.assertEquals("S1", schemaDefinition.getSubject());
        Assert.assertEquals(2, schemaDefinition.getVersion());
        Assert.assertEquals(121, schemaDefinition.getId());
        Assert.assertEquals(1, schemaDefinition.getSchema().getFields().size());
        Assert.assertEquals("f1", schemaDefinition.getSchema().getFields().get(0).getName());
        Assert.assertEquals("string", schemaDefinition.getSchema().getFields().get(0).getType());
        Assert.assertEquals(false, schemaDefinition.getSchema().getFields().get(0).isNullable());
    }
}
