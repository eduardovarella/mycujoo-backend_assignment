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
        doReturn(new String[0]).when(service).getSubjectNamesArray();
        List<Subject> subjects = service.loadSubjectsList();
        Assert.assertEquals(0, subjects.size());
    }

    @Test
    public final void validSubjects() throws Exception, HttpUtilsException {

        String[] subjectNames = {"S1", "S2", "S3"};
        SQLCommandService service = spy(SQLCommandService.class);
        doReturn(subjectNames).when(service).getSubjectNamesArray();

        List<Subject> subjects = service.loadSubjectsList();
        Assert.assertEquals(3, subjects.size());
        Assert.assertEquals(subjectNames[0], subjects.get(0).getName());
        Assert.assertEquals(subjectNames[1], subjects.get(1).getName());
        Assert.assertEquals(subjectNames[2], subjects.get(2).getName());
    }
}
