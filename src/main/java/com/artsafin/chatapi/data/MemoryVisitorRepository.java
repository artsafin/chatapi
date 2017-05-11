package com.artsafin.chatapi.data;

import com.artsafin.chatapi.domain.repository.VisitorRepository;
import com.artsafin.chatapi.domain.visitor.PersistentVisitor;
import com.artsafin.chatapi.domain.visitor.Visitor;
import com.artsafin.chatapi.domain.visitor.VisitorNotFoundException;
import com.artsafin.chatapi.domain.visitor.contact.Contact;
import com.artsafin.chatapi.util.LookupSpec;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;

public class MemoryVisitorRepository implements VisitorRepository {
    private final HashMap<Visitor.Id, PersistentVisitor> data = new HashMap<>();

    {
        PersistentVisitor[] arr = new PersistentVisitor[]{
                new PersistentVisitor(new Visitor.Id("11c03042-e69b-4616-b20a-934cc3eefe3d"), "Alice", "US", "en", new HashSet<Contact>() {{
                    add(new Contact("alice@example.com", Contact.Type.EMAIL));
                }}, null),
                new PersistentVisitor(new Visitor.Id("2e9e9002-f9a6-44a2-8a21-5d1d78360a5f"), "Bob", "RU", "ru", new HashSet<Contact>() {{
                    add(new Contact("bob@example.com", Contact.Type.EMAIL));
                }}, null),
                new PersistentVisitor(new Visitor.Id("3e9e9002-f9a6-44a2-8a21-5d1d78360a5f"), "Eve", "CY", "en", new HashSet<Contact>() {{
                    add(new Contact("eva@example.com", Contact.Type.EMAIL));
                }}, null),
        };
        for (PersistentVisitor pa : arr) {
            data.put(pa.getId(), pa);
        }
    }

    @Override
    public void add(PersistentVisitor visitor) {
        data.put(visitor.getId(), visitor);
    }

    @Override
    public @Nullable PersistentVisitor findOneBy(LookupSpec<Visitor> visitorSpec) {
        return data.get(new Visitor.Id("3e9e9002-f9a6-44a2-8a21-5d1d78360a5f"));
    }

    @Override
    public PersistentVisitor findById(Visitor.Id visitorId) {
        return data.get(visitorId);
    }

    @Override
    public PersistentVisitor requireById(Visitor.Id visitorId) throws VisitorNotFoundException {
        PersistentVisitor res = findById(visitorId);
        if (res != null) {
            return res;
        }
        throw new VisitorNotFoundException(visitorId);
    }
}
