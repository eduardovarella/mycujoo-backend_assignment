package com.evarella.mycujoo.service;

import com.evarella.mycujoo.model.Subject;
import com.evarella.mycujoo.utils.HttpUtilsException;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Eduardo on 22/08/2018.
 */
public class LoadSubjectListUnitTests {

    @Test
    public final void emptyArrayReturned() throws Exception, HttpUtilsException {

        SQLCommandService service = spy(SQLCommandService.class);
        doReturn("[]").when(service).loadSubjectListFromAPI();
        List<Subject> subjects = service.loadSubjectsList();
        Assert.assertEquals(0, subjects.size());
    }

    @Test
    public final void validSubjects() throws Exception, HttpUtilsException {

        SQLCommandService service = spy(SQLCommandService.class);
        doReturn("[\"S1\",\"S2\",\"S3\"]").when(service).loadSubjectListFromAPI();

        List<Subject> subjects = service.loadSubjectsList();
        Assert.assertEquals(3, subjects.size());
        Assert.assertEquals("S1", subjects.get(0).getName());
        Assert.assertEquals("S2", subjects.get(1).getName());
        Assert.assertEquals("S3", subjects.get(2).getName());
    }
}
